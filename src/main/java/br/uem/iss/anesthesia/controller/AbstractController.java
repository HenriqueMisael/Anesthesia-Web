package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.controller.binder.LocalDatePropertyEditorSupport;
import br.uem.iss.anesthesia.controller.binder.LocalDateTimePropertyEditorSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AbstractController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        registerLocalDateCustomEditor(binder);
        registerLocalDateTimeCustomEditor(binder);
    }

    private void registerLocalDateTimeCustomEditor(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimePropertyEditorSupport());
    }

    private void registerLocalDateCustomEditor(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditorSupport());
    }
}
