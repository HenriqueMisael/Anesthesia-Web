package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveExamBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import br.uem.iss.anesthesia.view.ExamFormView;
import br.uem.iss.anesthesia.view.ExamIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SaveExamBusiness saveExamBusiness;

    @GetMapping
    public ModelAndView listExam(@RequestParam(value = "filtro_name", required = false) String name, boolean ativo)  {
        Iterable<ExamModel> exam;
        name    = ((name == null) ? "" : name);
        if (ativo) {
            exam = examRepository.findByActiveTrue();
        }else{
            exam = examRepository.findByActiveTrue();
        }
        return new ExamIndexView(exam, name,true);
    }

    @GetMapping("/new")
    public ModelAndView newExam() {
        return viewWithoutMessage(new ExamModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editExam(@PathVariable Long id) {
        return viewWithoutMessage(examRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView saveExam(@Valid ExamModel exam) {
        boolean jejumValido = false;
        boolean nomevalido = false;
        boolean descricaovalido = false;
        try{
            if(exam.getJejumTime() < 0){
                System.out.println("Numero ivalido");
            }else{
                jejumValido = true;
            }
        }catch (Exception e){
            System.out.println("Numero ivalido");
            jejumValido = false;
        }

        try{
            if(exam.getName().length() < 1 ){
                System.out.println("nome ivalido");
                nomevalido = false;
            }else{
                nomevalido = true;
            }

            String letras = exam.getName();
            for (int i = 0; i < letras.length(); i++) {
                if (Character.isDigit(letras.charAt(i))==true)
                {
                    System.out.println("Nome invalido");
                    nomevalido = false;
                    break;
                }else {nomevalido = true;}
            }

        }catch (Exception e){
            System.out.println("Numero ivalido");
            nomevalido = false;
        }

        try{
            if(exam.getDescription().length() < 1 ){
                System.out.println("Descricao ivalida");
                descricaovalido = false;

            }else{
                descricaovalido = true;
            }
        }catch (Exception e){
            System.out.println("Descricao ivalida");
            descricaovalido = false;
        }



        try {

            exam.active = true;
            saveExamBusiness.save(exam);
            return listExam(null,true);
        } catch (BusinessRuleException e) {
            return viewWithMessage(exam, e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteExam(@PathVariable Long id) {
        ExamModel exam = examRepository.findById(id).get();
        exam.inactivate();
        try {
            saveExamBusiness.save(exam);
        } catch (BusinessRuleException e) {
        }
        return new ExamIndexView(examRepository.findByActiveTrue(), null,true);
    }
    private ExamFormView viewWithoutMessage(ExamModel exam) {
        return viewWithMessage(exam, null);
    }
    private ExamFormView viewWithMessage(ExamModel exam, String message) {
        return new ExamFormView(exam);
    }


    @GetMapping("/find/{id}")
    @ResponseBody
    public Optional<ExamModel> getMedicalProcedure(@PathVariable Long id) {
        try{
            Optional<ExamModel> exam;

            exam = examRepository.findById(id);
            return exam;
        }catch (Exception e){
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
}

