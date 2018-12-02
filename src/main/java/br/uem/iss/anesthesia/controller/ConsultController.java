package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.controller.request.AppointmentRequest;
import br.uem.iss.anesthesia.model.business.SaveConsultBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.*;
import br.uem.iss.anesthesia.model.repository.AppointmentRepository;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/consult")
public class ConsultController extends AbstractController {

    @Autowired
    private AppointmentRepository consultRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    ProcessRepository processRepository;
    @Autowired
    private SaveConsultBusiness saveConsultBusiness;


    public ArrayList<String> timeConsult(){
        ArrayList<String> timeConsult = new ArrayList<>() ;
        timeConsult.add("08:00");
        timeConsult.add("09:00");
        timeConsult.add("10:00");
        timeConsult.add("11:00");
        timeConsult.add("13:00");
        timeConsult.add("14:00");
        timeConsult.add("15:00");
        timeConsult.add("16:00");
        return timeConsult;
    }


    @GetMapping
    public ModelAndView listConsult(@RequestParam(value = "filtro_number", required = false) String number, String codigo,
                                    String nome_paciente, String nome_medico)  {
        Iterable<AppointmentModel> consult;
            consult = consultRepository.findByActiveTrue();
        return new ConsultIndexView(consult, number,codigo,nome_paciente,nome_medico);
    }





    private ConsultFormView viewWithoutMessage(AppointmentModel consult ) {
        Iterable<ProcessModel> process = processRepository.findAll();
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setActive(appointmentRequest.isActive());
        //appointmentRequest.setDate(consult.getDate().toLocalDate());
        appointmentRequest.setProcess(consult.getProcess());

        return new ConsultFormView(appointmentRequest,process,timeConsult());
    }

    private ConsultFormView viewWithMessage(AppointmentRequest consult, String message) {
        Iterable<ProcessModel> process = processRepository.findAll();
        return new ConsultFormView(consult,process,timeConsult());
    }

    @GetMapping("/new")
    public ModelAndView newConsult() {
        return viewWithoutMessage(new AppointmentModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editConsult(@PathVariable Long id) {
        System.out.println("ENTROU NO Editteeeeeeeeee");
        AppointmentModel appointmentModel = new AppointmentModel();
        return viewWithoutMessage(consultRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView saveConsult(@Valid AppointmentRequest appointmentRequest) {
        System.out.println("ENTROU NO CONTROLE");
        try {
            //System.out.println("Hora: "+ appointmentRequest.getHour());
            //System.out.println("Data: "+ appointmentRequest.getDate().toString());

            AppointmentModel consult = new AppointmentModel();

            consult.setActive(true);
            consult.setDate(appointmentRequest.getDate().atTime(LocalTime.parse(appointmentRequest.getHour(), DateTimeFormatter.ofPattern("HH:mm"))));
            consult.setProcess(appointmentRequest.getProcess());
            consult.setId(appointmentRequest.getId());
            ArrayList<DoctorModel> doutores = new ArrayList<>();

            System.out.println(appointmentRequest.getDate().getDayOfWeek().name());

            boolean manha = false;

            boolean domingo = false;
            boolean segunda = false;
            boolean terca = false;
            boolean quarta = false;
            boolean quinta = false;
            boolean sexta = false;
            boolean sabado = false;
            // VERIFICA PARA DOMINGO DE MANHÃ
            /*System.out.println("teste"+appointmentRequest.getProcess().toString());
            if(appointmentRequest.getDate().getDayOfWeek().name() == "SUNDAY"){ //VERIFICA SE O DOUTAR ATENDE NO DOMINGO
                if(appointmentRequest.getProcess().getDoctor().isDomingoManha() == verificaManha(appointmentRequest)){
                    //VERIFICA SE O DOUTOR ATENTEDE MANHÃ
                    if(verificaprocedimentoNoMesmoHorarioEdoutor(consult) == true){
                        //VERIFICA SE JÁ TEM UMA CONSULTA MARCADA COM ESSE DOUTOR NO MESMO HORARIO
                        saveConsultBusiness.save(consult);
                    }else {System.out.println("Doutor já tem consulta marcada nesse horario e dia");}
                }else {System.out.println("Doutor não esse periodo");}
            }

            // VERIFICA PARA DOMINGO DE TARDE
            if(appointmentRequest.getDate().getDayOfWeek().name() == "SUNDAY"){ //VERIFICA SE O DOUTAR ATENDE NO DOMINGO
                if(appointmentRequest.getProcess().getDoctor().isDomingoTarde() == verificaManha(appointmentRequest)){
                    //VERIFICA SE O DOUTOR ATENTEDE MANHÃ
                    if(verificaprocedimentoNoMesmoHorarioEdoutor(consult) == true){
                        //VERIFICA SE JÁ TEM UMA CONSULTA MARCADA COM ESSE DOUTOR NO MESMO HORARIO
                        saveConsultBusiness.save(consult);
                    }else {System.out.println("Doutor já tem consulta marcada nesse horario e dia");}
                }else {System.out.println("Doutor não esse periodo");}
            }
            */



            saveConsultBusiness.save(consult);
            return listConsult(null,null,null,null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(appointmentRequest, e.getMessage());


        }
    }

    @GetMapping("/deleteConsult/{id}")
    public ModelAndView deleteConsult(@PathVariable Long id) {
        AppointmentModel consult = consultRepository.findById(id).get();
        consult.inactivate();
        try {
            saveConsultBusiness.save(consult);
        } catch (BusinessRuleException e) {
        }
        return new ConsultIndexView(consultRepository.findByActiveTrue(),null, null,null,null);
    }

    public boolean verificaManha( AppointmentRequest appointmentRequest){
        boolean retorno = false;
        if (appointmentRequest.getHour() == "08:00"){
            retorno = true;
        }else if (appointmentRequest.getHour() == "09:00"){
            retorno = true;
        }else if (appointmentRequest.getHour() == "10:00"){
            retorno = true;
        }else if (appointmentRequest.getHour() == "11:00"){
            retorno = true;
        }
        return retorno;
    }

    boolean verificaprocedimentoNoMesmoHorarioEdoutor(AppointmentModel consultaSalvo){
        boolean retorno = true;
        for (AppointmentModel consultas : consultRepository.findAll()) {
            if ( consultaSalvo.getProcess().getDoctor() == consultas.getProcess().getDoctor()
                    && consultaSalvo.getDate() == consultas.getDate()){
                retorno = false;
            }
        }
        return retorno;

    }

}

