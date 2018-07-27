package ru.catalog.book.controllers.models.DTO;

import ru.catalog.book.controllers.enums.ContentType;
import ru.catalog.book.controllers.models.Catalog;

public class BookDTO {
    private Long id;
    private ContentType contentType = ContentType.BOOK;
    private String name;
    private Long catalogID;
    private String autor;
    private String createDate;
    private Catalog catalog;

    public BookDTO() {
    }

    public BookDTO(Long id, ContentType contentType, String name, Long catalogID, String autor, String createDate) {
        this.id = id;
        this.contentType = contentType;
        this.name = name;
        this.catalogID = catalogID;
        this.autor = autor;
        this.createDate = createDate;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(Long catalogID) {
        this.catalogID = catalogID;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
