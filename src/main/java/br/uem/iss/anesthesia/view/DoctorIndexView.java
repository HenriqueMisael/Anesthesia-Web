package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class DoctorIndexView extends ModelAndView {

    public DoctorIndexView(Iterable<DoctorModel> doctors){
        super("layouts/app", "doctors", doctors);
        this.addObject("conteudo", "index_doctor");
    }
}
