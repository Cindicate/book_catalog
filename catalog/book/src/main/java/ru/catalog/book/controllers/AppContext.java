package ru.catalog.book.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.catalog.book.controllers.managers.CatalogManager;
import ru.catalog.book.controllers.managers.IDSeq;

@Configuration
public class AppContext {

    @Bean
    public IDSeq idSeq() {
        return new IDSeq();
    }

    @Bean
    public CatalogManager catalogManager() {
        return new CatalogManager();
    }

}