package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.controller.request.AppointmentRequest;
import br.uem.iss.anesthesia.model.business.SaveConsultBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.*;
import br.uem.iss.anesthesia.model.repository.AppointmentRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/consult")
public class ConsultController extends AbstractController {

    @Autowired
    private AppointmentRepository consultRepository;
    @Autowired
    ProcessRepository processRepository;

    @Autowired
    private SaveConsultBusiness saveConsultBusiness;





    @GetMapping
    public ModelAndView listConsult(@RequestParam(value = "filtro_number", required = false) String number, String codigo,
                                    String nome_paciente, String nome_medico)  {
        Iterable<AppointmentModel> consult;
            consult = consultRepository.findAll();
        return new ConsultIndexView(consult, number,codigo,nome_paciente,nome_medico);
    }





    private ConsultFormView viewWithoutMessage(AppointmentModel consult ) {
        Iterable<ProcessModel> process = processRepository.findAll();
        return new ConsultFormView(consult,process);
    }

    private ConsultFormView viewWithMessage(AppointmentRequest consult, String message) {

        Iterable<ProcessModel> process = processRepository.findAll();
        return new ConsultFormView(consult,process);
    }

    @GetMapping("/new")
    public ModelAndView newConsult() {
        return viewWithoutMessage(new AppointmentModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editConsult(@PathVariable Long id) {
        return viewWithoutMessage(consultRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView saveConsult(@Valid AppointmentRequest appointmentRequest) {
        System.out.println("ENTROU NO CONTROLE");
        try {
            AppointmentModel consult = new AppointmentModel();

            consult.setActive(appointmentRequest.isActive());
            consult.setDate(appointmentRequest.getDate().atTime(LocalTime.parse(appointmentRequest.getHour(), DateTimeFormatter.ofPattern("HH:mm"))));
            consult.setProcess(appointmentRequest.getProcess());
            consult.setId(appointmentRequest.getId());

            saveConsultBusiness.save(consult);
            return listConsult(null,null,null,null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(appointmentRequest, e.getMessage());
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
        return new ConsultIndexView(consultRepository.findAll(),null, null,null,null);
    }

}

