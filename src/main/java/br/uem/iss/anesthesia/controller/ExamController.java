/*package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveExamBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import br.uem.iss.anesthesia.view.DoctorFormView;
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
    private SaveExamBusiness examBusiness;


    @GetMapping
    public ExamIndexView listExam(@RequestParam(value = "filtro_name", required = false) String name) {
        Iterable<ExamModel> exam;
        if (name != null) {
            exam = examRepository.findByNameContainingAndActiveTrue(name);
        } else {
            exam = examRepository.findByActiveTrue();
        }
        return new ExamIndexView(exam,name);
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
    public ModelAndView savePatient(@Valid DoctorModel doctor) {
        try {
            saveDoctorBusiness.save(doctor);
            return listDoctors(null, null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(doctor, e.getMessage());
        }
    }

    private ExamFormView viewWithoutMessage(ExamModel exam) {
        return viewWithMessage(exam, null);
    }

    private ExamFormView viewWithMessage(ExamModel exam, String message) {
        return new ExamFormView(exam, message);
    }
}*/
