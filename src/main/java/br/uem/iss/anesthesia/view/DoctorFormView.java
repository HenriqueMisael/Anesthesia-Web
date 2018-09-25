package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.Week;
import org.springframework.web.servlet.ModelAndView;

public class DoctorFormView extends ModelAndView {
    public DoctorFormView(DoctorModel doctor, String message) {
        super("layouts/app", "doctor", doctor);
        this.addObject("message", message);
        this.addObject("metodo", "Adicionar ");
        this.addObject("dias_semana", Week.values());
        this.addObject("conteudo", "form_doctor");
    }
}
