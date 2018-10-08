package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveExamBusiness;
import br.uem.iss.anesthesia.model.business.SaveProcessBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.ExamRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.view.DoctorFormView;
import br.uem.iss.anesthesia.view.ExamFormView;
import br.uem.iss.anesthesia.view.ExamIndexView;
import br.uem.iss.anesthesia.view.ProcessIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamRepository ExamRepository;

    @Autowired
    private SaveProcessBusiness saveProcessBusiness;

    @GetMapping
    public ModelAndView listExam() {
        Iterable<ExamModel> exam;

        exam = ExamRepository.findByActiveTrue();
        return new ExamIndexView(exam);
    }
    @GetMapping("/new")
    public ModelAndView newExam() {
        return viewWithoutMessage(new ExamModel());
    }

//    @GetMapping("/new")
//    public ModelAndView newDoctor() {
//        return viewWithoutMessage(new DoctorModel());
//    }
//
//    @GetMapping("/{id}")
//    public ModelAndView editDoctor(@PathVariable Long id) {
//        return viewWithoutMessage(doctorRepository.findById(id).get());
//    }
//
//    @PostMapping
//    public ModelAndView savePatient(@Valid DoctorModel doctor) {
//        try {
//            saveDoctorBusiness.save(doctor);
//            return listDoctors(null, null);
//        } catch (BusinessRuleException e) {
//            return viewWithMessage(doctor, e.getMessage());
//        }
//    }
//
//    @GetMapping("/delete/{id}")
//    public ModelAndView deleteDoctor(@PathVariable Long id) {
//        DoctorModel doctor = doctorRepository.findById(id).get();
//        doctor.inactivate();
//        try {
//            saveDoctorBusiness.save(doctor);
//        } catch (BusinessRuleException e) {
//        }
//        return new DoctorIndexView(doctorRepository.findByActiveTrue(), null, null);
//    }

    private ExamFormView viewWithoutMessage(ExamModel exam) {
        return viewWithMessage(exam);
    }

    private ExamFormView viewWithMessage(ExamModel exam) {
        return new ExamFormView(exam);
    }
}

