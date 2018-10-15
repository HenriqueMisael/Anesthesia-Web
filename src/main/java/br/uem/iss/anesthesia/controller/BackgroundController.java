package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveBackgroundBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import br.uem.iss.anesthesia.model.repository.BackgroundRepository;
import br.uem.iss.anesthesia.view.BackgroundIndexView;
import br.uem.iss.anesthesia.view.BackgroundView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/background")
public class BackgroundController {
    @Autowired
    private BackgroundRepository backgroundRepository;
    @Autowired
    private SaveBackgroundBusiness saveBackgroundBusiness;

    @GetMapping
    public ModelAndView listBackgrounds(@RequestParam(value = "name", required = false) String name) {
        Iterable<BackgroundModel> backgrounds;
        if (name != null) {
            backgrounds = backgroundRepository.findAll();
        } else {
            backgrounds = backgroundRepository.findAll();
        }
        return new BackgroundIndexView(backgrounds);
    }

    @GetMapping("/new")
    public ModelAndView newBackground() {
        return viewWithoutMessage(new BackgroundModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editBackground(@PathVariable Long id) {
        return viewWithoutMessage(backgroundRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView savePatient(@Valid BackgroundModel background) {
        try {
            saveBackgroundBusiness.save(background);
            return listBackgrounds(null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(background, e.getMessage());
        }
    }

    private BackgroundView viewWithoutMessage(BackgroundModel background) {
        return viewWithMessage(background, null);
    }

    private BackgroundView viewWithMessage(BackgroundModel background, String message) {
        return new BackgroundView(background, message);
    }
}
