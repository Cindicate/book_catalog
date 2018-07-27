package ru.catalog.book.controllers.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.catalog.book.controllers.enums.CatalogType;
import ru.catalog.book.controllers.enums.ContentType;
import ru.catalog.book.controllers.models.Book;
import ru.catalog.book.controllers.models.Catalog;
import ru.catalog.book.controllers.models.ContentObject;
import ru.catalog.book.controllers.models.DTO.BookDTO;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static ru.catalog.book.controllers.DateUtils.getDateFromString;

@Service
public class CatalogManager {

    @Autowired
    public IDSeq idSeq;

    private Catalog publicCatalog;
    private Catalog privateCatalog;

    private HashMap<Long, ContentObject> contentMap;

    @PostConstruct
    public void init() throws ParseException {
        contentMap = new HashMap<Long, ContentObject>();

        publicCatalog = new Catalog(idSeq.getNextID(), "Публичный каталог");
        privateCatalog = new Catalog(idSeq.getNextID(), "Закрытый каталог");
        addToContentJournal(publicCatalog);
        addToContentJournal(privateCatalog);

        /////////////////////////////
        // Заполнение демо данными
        Catalog history = new Catalog(idSeq.getNextID(), "Исторические");
        addContent(publicCatalog,history);
        addContent(history,new Book(idSeq.getNextID(), "Переход через Альпийские горы", "Илья Овербах", getDateFromString("22.11.1985")));
        addContent(history,new Book(idSeq.getNextID(), "Крылья крепнут в бою", "Александр суворов", getDateFromString("01.08.1995")));

        Catalog science = new Catalog(idSeq.getNextID(), "Научные");
        addContent(publicCatalog,science);
        addContent(science,new Book(idSeq.getNextID(), "Алгебра и геометрия", "Аврам Герц", getDateFromString("15.10.1976")));
        addContent(science,new Book(idSeq.getNextID(), "Искуственный интеллект", "Виктор Столбов", getDateFromString("23.05.1997")));

        Catalog fiction = new Catalog(idSeq.getNextID(), "Художественная литература");
        addContent(publicCatalog,fiction);
        addContent(fiction,new Book(idSeq.getNextID(), "Быстро в небо", "Джон Леннон", getDateFromString("03.02.1959")));
        addContent(fiction,new Book(idSeq.getNextID(), "Обратно на землю", "Полл Макартни", getDateFromString("04.02.1997")));

        Catalog fantasy = new Catalog(idSeq.getNextID(), "Фантастика");
        addContent(privateCatalog,fantasy);
        addContent(fantasy,new Book(idSeq.getNextID(), "Серверы на марсе", "Томас Кунн", getDateFromString("03.02.1936")));
        addContent(fantasy,new Book(idSeq.getNextID(), "Радиосвязь", "Александр Попов", getDateFromString("04.02.1905")));

        addContent(privateCatalog,new Book(idSeq.getNextID(), "Вне закона", "Александр Новиков", getDateFromString("04.02.1989")));
        /////////////////////////////
    }

    public void addContent(Catalog catalog, ContentObject contentObject){
        catalog.addContentToList(contentObject);
        addToContentJournal(contentObject);
    }

    public void addToContentJournal(ContentObject contentObject){
        getContentMap().put(contentObject.getId(),contentObject);
    }

    public ContentObject getContentByID(Long contentID) {
        contentMap.get(contentID);
        return getContentMap().get(contentID);
    }

    public HashMap<Long, ContentObject> getContentMap() {
        return contentMap;
    }

    public void setContentMap(HashMap<Long, ContentObject> contentMap) {
        this.contentMap = contentMap;
    }

    public Book saveBook(BookDTO bookDTO) throws ParseException {
        Long bookID = bookDTO.getId();
        Book book = null;
        if(bookID==null){
            book = createBookFromDTO(bookDTO);
        }else{
            book = getBookByID(bookID);
        }
        if(book!=null) {
            Catalog catalog = getCatalogByID(bookDTO.getCatalogID());
            removeFromContent(book);
            addContent(catalog, book);
            bookDTO.setCatalog(catalog);
            book.init(bookDTO);
        }
        return book;
    }

    private Book createBookFromDTO(BookDTO bookDTO) throws ParseException {
        bookDTO.setId(idSeq.getNextID());
        bookDTO.setCatalog(getCatalogByID(bookDTO.getCatalogID()));
        Book book = new Book(bookDTO);
        addContent(book.getCatalog(), book);
        return book;
    }

    public Catalog getCatalogByID(Long contentID) {
        return (Catalog) getContentByID(contentID);
    }

    private void removeFromContent(Book book) {
        Catalog catalog = getCatalogByID(book.getCatalog().getId());
        catalog.getContentMap().remove(book.getId());
    }

    public boolean removeBook(Long contentID) {
        Book book = getBookByID(contentID);
        if(book!=null){
            getContentMap().remove(book.getId());
            for (Map.Entry<Long, ContentObject> contentItem : getContentMap().entrySet()) {
                ContentObject contentObject = contentItem.getValue();
                if(ContentType.CATALOG.equals(contentObject.getContentType())
                        && ((Catalog)contentObject).getContentMap().containsKey(book.getId())){
                    ((Catalog)contentObject).getContentMap().remove(book.getId());
                    return true;
                }
            }
        }

        return false;
    }

    public Book getBookByID(Long contentID){
        return (Book) getContentByID(contentID);
    }

    public Catalog getPublicCatalog() {
        return publicCatalog;
    }

    public Catalog getPrivateCatalog() {
        return privateCatalog;
    }

    public Catalog getParentCatalog(CatalogType catalogType) {
        switch(catalogType){
            case private_catalog:
                return getPrivateCatalog();
        }
        return getPublicCatalog();
    }
}
