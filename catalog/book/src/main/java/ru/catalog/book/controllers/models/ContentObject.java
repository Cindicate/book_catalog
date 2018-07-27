package ru.catalog.book.controllers.models;

import ru.catalog.book.controllers.enums.ContentType;

public abstract class ContentObject {
    public Long id;
    public ContentType contentType;
    public String name;
    public Catalog catalog;

    public ContentObject(Long id, ContentType contentType, String name, Catalog catalog) {
        this.id = id;
        this.contentType = contentType;
        this.name = name;
        this.catalog = catalog;
    }

    public ContentObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
