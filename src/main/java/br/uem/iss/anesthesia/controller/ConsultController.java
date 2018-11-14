package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveConsultBusiness;
import br.uem.iss.anesthesia.model.business.SaveExamBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.ConsultModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.repository.ConsultRepository;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import br.uem.iss.anesthesia.view.ConsultFormView;
import br.uem.iss.anesthesia.view.ConsultIndexView;
import br.uem.iss.anesthesia.view.ExamFormView;
import br.uem.iss.anesthesia.view.ExamIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/consult")
public class ConsultController {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private SaveConsultBusiness saveConsultBusiness;
}

/*
    @GetMapping
    public ModelAndView listConsult(@RequestParam(value = "filtro_number", required = false) String number, boolean ativo)  {
        Iterable<ConsultModel> consult;
        number    = ((number == null) ? "" : number);
        if (ativo) {
            consult = consultRepository.findAll();
        }else{
            consult = consultRepository.findAll();
        }
        return new ConsultIndexView(consult, number,true);
    }

    private ConsultFormView viewWithoutMessage(ConsultModel consult) {
        return viewWithMessage(consult, null);
    }
    private ConsultFormView viewWithMessage(ConsultModel consult, String message) { return new ConsultFormView(consult); }

    @GetMapping("/new")
    public ModelAndView newConsult() {
        return viewWithoutMessage(new ConsultModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editConsult(@PathVariable Long id) {
        return viewWithoutMessage(consultRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView saveConsult(@Valid ConsultModel consult) {
        try {
            saveConsultBusiness.save(consult);
            return listConsult(null,true);
        } catch (BusinessRuleException e) {
            return viewWithMessage(consult, e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteConsult(@PathVariable Long id) {
        ConsultModel consult = consultRepository.findById(id).get();
        consult.inactivate();
        try {
            saveConsultBusiness.save(consult);
        } catch (BusinessRuleException e) {
        }
        return new ConsultIndexView(consultRepository.findByActiveTrue(),null, true);
    }

}

