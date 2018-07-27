
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.catalog.book.controllers.AppContext;
import ru.catalog.book.controllers.enums.CatalogType;
import ru.catalog.book.controllers.managers.CatalogManager;
import ru.catalog.book.controllers.managers.IDSeq;
import ru.catalog.book.controllers.models.Book;
import ru.catalog.book.controllers.models.Catalog;
import ru.catalog.book.controllers.models.DTO.BookDTO;

import java.text.ParseException;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppContext.class})
public class CatalogTest {

    @Autowired
    public IDSeq idSeq;

    @Autowired
    public CatalogManager catalogManager;

    @Test
    public void CreateCatalog() throws ParseException {
        Catalog parentCatalog = catalogManager.getParentCatalog(CatalogType.public_catalog);
        Assert.assertNotNull(parentCatalog);
        Assert.assertEquals(3, parentCatalog.getContentMap().size());

        //создадим 2 каталога и одну книгу в родительском каталоге
        Catalog catalog2 = new Catalog(idSeq.getNextID(), "Исторические2");
        Catalog catalog3 = new Catalog(idSeq.getNextID(), "Исторические3");
        catalogManager.addContent(parentCatalog,catalog2);
        catalogManager.addContent(parentCatalog,catalog3);
        Book book = new Book(idSeq.getNextID(), "Переход через Альпийские горы", "Илья Овербах", new Date());
        catalogManager.addContent(parentCatalog,book);
        Assert.assertEquals(6, parentCatalog.getContentMap().size());
        Assert.assertEquals(parentCatalog.getId(), book.getCatalog().getId());

        //получим транспорт книги
        BookDTO bookDTO = book.getDTO();
        Assert.assertTrue(bookDTO.getId().equals(book.getId()));
        Assert.assertTrue(bookDTO.getName().equals(book.getName()));
        Assert.assertTrue(bookDTO.getAutor().equals(book.getAutor()));

        //меняем транспорт (название и каталог)
        bookDTO.setName("Книга 2");
        Assert.assertFalse(bookDTO.getName().equals(book.getName()));

        bookDTO.setCatalogID(catalog3.getId());

        book = catalogManager.saveBook(bookDTO);
        Assert.assertTrue(book.getName().equals(bookDTO.getName()));

        Assert.assertEquals(catalog3.getId(), book.getCatalog().getId());

        //Удаляем книгу
        catalogManager.removeBook(book.getId());
        Assert.assertEquals(5, parentCatalog.getContentMap().size());
    }
}
