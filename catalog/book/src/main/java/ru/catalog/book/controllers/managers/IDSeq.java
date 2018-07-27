package ru.catalog.book.controllers.managers;

import org.springframework.stereotype.Service;


@Service
public class IDSeq {
    private Long contentID = 1L;

    public Long getNextID(){
        return contentID++;
    }
}
