package ru.catalog.book.controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.catalog.book.controllers.enums.CatalogType;
import ru.catalog.book.controllers.enums.ContentType;
import ru.catalog.book.controllers.managers.CatalogManager;
import ru.catalog.book.controllers.models.Book;
import ru.catalog.book.controllers.models.Catalog;
import ru.catalog.book.controllers.models.DTO.BookDTO;


@Controller
@RequestMapping("/")
public class HomeCatalogController {

    @Autowired
    public CatalogManager catalogManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("default");
        Catalog parentCatalog = catalogManager.getParentCatalog(CatalogType.public_catalog);
        mav.addObject("catalogType",CatalogType.public_catalog);
        mav.addObject("parentCatalog",parentCatalog);
        return mav;
    }

    @RequestMapping(value = "/{catalogType}", method = RequestMethod.GET)
    public ModelAndView homeByCatalog(@PathVariable CatalogType catalogType) {
        ModelAndView mav = new ModelAndView("default");
        Catalog parentCatalog = catalogManager.getParentCatalog(catalogType);
        mav.addObject("catalogType",catalogType);
        mav.addObject("parentCatalog",parentCatalog);
        return mav;
    }

    @RequestMapping(value = "/book/{contentID}", method = RequestMethod.GET)
    public ModelAndView showBook(@PathVariable Long contentID) {
        Book book = (Book) catalogManager.getContentByID(contentID);
        ModelAndView mav = new ModelAndView("default");
        Catalog parentCatalog = (Catalog) catalogManager.getContentByID(book.getCatalog().getId());
        mav.addObject("parentCatalog",parentCatalog);
        mav.addObject("book",book.getDTO());
        return mav;
    }

    @RequestMapping(value = "/book/edit/{contentID}", method = RequestMethod.GET)
    public ModelAndView bookEditForm(@PathVariable Long contentID) {
        Book book = null;
        Catalog parentCatalog = null;
        if(contentID!=null){
            book = (Book) catalogManager.getContentByID(contentID);
            parentCatalog = (Catalog) catalogManager.getContentByID(book.getCatalog().getId());
        }else{
            book = new Book();
            parentCatalog = catalogManager.getPublicCatalog();
        }
        ModelAndView mav = new ModelAndView("default");

        mav.addObject("parentCatalog",parentCatalog);
        mav.addObject("book",book.getDTO());
        mav.addObject("catalogMap",catalogManager.getContentMap());
        mav.addObject("edit",true);
        return mav;
    }

    @RequestMapping(value = "/book/create/{catalogID}", method = RequestMethod.GET)
    public ModelAndView createBookForm(@PathVariable Long catalogID) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setCatalogID(catalogID);
        Catalog parentCatalog = (Catalog) catalogManager.getCatalogByID(catalogID);

        ModelAndView mav = new ModelAndView("default");

        mav.addObject("parentCatalog",parentCatalog);
        mav.addObject("book",bookDTO);
        mav.addObject("catalogMap",catalogManager.getContentMap());
        mav.addObject("edit",true);
        return mav;
    }

    @RequestMapping(value = "/catalog/{contentID}", method = RequestMethod.GET)
    public ModelAndView showCatalog(@PathVariable Long contentID) {
        Catalog parentCatalog = (Catalog) catalogManager.getContentByID(contentID);
        ModelAndView mav = new ModelAndView("default");
        mav.addObject("parentCatalog",parentCatalog);
        return mav;
    }
}
