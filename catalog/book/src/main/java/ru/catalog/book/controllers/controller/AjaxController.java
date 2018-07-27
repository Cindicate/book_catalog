package ru.catalog.book.controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.catalog.book.controllers.YAlert;
import ru.catalog.book.controllers.enums.YAlertType;
import ru.catalog.book.controllers.managers.CatalogManager;
import ru.catalog.book.controllers.models.Book;
import ru.catalog.book.controllers.models.DTO.BookDTO;

import java.text.ParseException;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    public CatalogManager catalogManager;

    @PostMapping(value = "/savebook", produces = { APPLICATION_JSON_VALUE })
    @ResponseBody
    public ModelAndView saveBook(@ModelAttribute BookDTO bookDTO) throws ParseException {
        Book book = catalogManager.saveBook(bookDTO);
        YAlert yAlert = new YAlert(YAlertType.SUCCESS);
        ModelAndView mav = new ModelAndView("fragments/components/_alert", "alert", yAlert);
        if(book!=null) {
            yAlert.setText("Книга успешно сохранена");
        }else{
            yAlert.setyAlertType(YAlertType.DANGER);
            yAlert.setText("Не удалось сохранить книгу");
        }

        return mav;
    }

    @PostMapping(value = "/deletebook",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Object deleteBook(@RequestBody BookDTO bookDTO) {
        Long bookID = bookDTO.getId();
        String message ="Книга удалена";
        HashMap hashMap = new HashMap();
        hashMap.put("success",true);
        if(!catalogManager.removeBook(bookID)) {
            message = "Не удалось удалить книгу";
            hashMap.put("success",false);
        }
        hashMap.put("message",message);
        return hashMap;
    }
}
