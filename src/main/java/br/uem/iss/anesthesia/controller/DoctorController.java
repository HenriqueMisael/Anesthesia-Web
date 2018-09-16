package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.view.DoctorIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public ModelAndView listPatients() {
        return new DoctorIndexView(doctorRepository.findByActiveTrue());
    }
}
