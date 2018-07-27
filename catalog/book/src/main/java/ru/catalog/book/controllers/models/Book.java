package ru.catalog.book.controllers.models;

import ru.catalog.book.controllers.enums.ContentType;
import ru.catalog.book.controllers.models.DTO.BookDTO;

import java.text.ParseException;
import java.util.Date;

import static ru.catalog.book.controllers.DateUtils.dateToStr;
import static ru.catalog.book.controllers.DateUtils.getDateFromString;


public class Book extends ContentObject{
    private String autor;
    private Date createDate;

    public Book(Long id, String name, String autor, Date createDate) {
        super(id, ContentType.BOOK, name, null);
        this.autor = autor;
        this.createDate = createDate;
    }

    public Book(BookDTO bookDTO) throws ParseException {
        super(bookDTO.getId(), ContentType.BOOK, bookDTO.getName(), bookDTO.getCatalog());
        this.autor = bookDTO.getAutor();
        this.createDate = getDateFromString(bookDTO.getCreateDate());
    }

    public Book() {
        super();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void init(BookDTO bookDTO) throws ParseException {
        setAutor(bookDTO.getAutor());
        setName(bookDTO.getName());
        setCreateDate(getDateFromString(bookDTO.getCreateDate()));
        setCatalog(bookDTO.getCatalog());
    }

    public BookDTO getDTO() {
        BookDTO bookDTO = new BookDTO(
                getId(),
                getContentType(),
                getName(),
                getCatalog().getId(),
                getAutor(),
                dateToStr(getCreateDate())
        );
        return bookDTO;
    }
}
