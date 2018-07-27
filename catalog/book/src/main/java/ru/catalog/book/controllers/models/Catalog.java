package ru.catalog.book.controllers.models;

import ru.catalog.book.controllers.enums.ContentType;

import java.util.HashMap;

public class Catalog extends ContentObject{
    private HashMap<Long, ContentObject> contentMap;

    public Catalog(Long id, String name, Catalog catalog) {
        super(id, ContentType.CATALOG, name, catalog);
    }

    public Catalog(Long id, String name) {
        super(id, ContentType.CATALOG, name, null);
    }

    public HashMap<Long, ContentObject> getContentMap() {
        contentMap = contentMap!=null ? contentMap : new HashMap<Long, ContentObject>();
        return contentMap;
    }

    public void setContentMap(HashMap<Long, ContentObject> contentMap) {
        this.contentMap = contentMap;
    }

    public void addContentToList(ContentObject contentObject){
        contentObject.setCatalog(this);
        getContentMap().put(contentObject.getId(),contentObject);
    }
}
