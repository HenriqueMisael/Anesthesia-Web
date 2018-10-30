package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveExamBusiness;
import br.uem.iss.anesthesia.model.business.SaveProcessBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import br.uem.iss.anesthesia.view.DoctorFormView;
import br.uem.iss.anesthesia.view.DoctorIndexView;
import br.uem.iss.anesthesia.view.ExamFormView;
import br.uem.iss.anesthesia.view.ExamIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
            exam = examRepository.findByNameContainingAndActiveTrue(name);
        }else{
            exam = examRepository.findByNameContainingAndActiveTrue(name);
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
        try {
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


}

