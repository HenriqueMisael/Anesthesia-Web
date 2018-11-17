package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveConsultBusiness;
import br.uem.iss.anesthesia.model.business.SaveExamBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.ConsultModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.ConsultRepository;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.view.*;
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
    private ProcessRepository processRepository;

    @Autowired
    private SaveConsultBusiness saveConsultBusiness;





    @GetMapping
    public ModelAndView listConsult(@RequestParam(value = "filtro_number", required = false) String number, String codigo,
                                    String nome_paciente, String nome_medico)  {
        Iterable<AppointmentModel> consult;
            consult = consultRepository.findAll();
        return new ConsultIndexView(consult, number,codigo,nome_paciente,nome_medico);
    }





    private ConsultFormView viewWithoutMessage(AppointmentModel consult) {
        return viewWithMessage(consult, null);
    }
    private ConsultFormView viewWithMessage(AppointmentModel consult, String message) { return new ConsultFormView(consult); }

    @GetMapping("/new")
    public ModelAndView newConsult() {
        return viewWithoutMessage(new AppointmentModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editConsult(@PathVariable Long id) {
        return viewWithoutMessage(consultRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView saveConsult(@Valid AppointmentModel consult) {
        try {
            saveConsultBusiness.save(consult);
            return listConsult(null,null,null,null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(consult, e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteConsult(@PathVariable Long id) {
        AppointmentModel consult = consultRepository.findById(id).get();
        consult.inactivate();
        try {
            saveConsultBusiness.save(consult);
        } catch (BusinessRuleException e) {
        }
        return new ConsultIndexView(consultRepository.findByActiveTrue(),null, null,null,null);
    }

}

