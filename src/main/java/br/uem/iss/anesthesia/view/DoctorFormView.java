package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.Week;

public class DoctorFormView extends AbstractModelAndView {
    public DoctorFormView(DoctorModel doctor, String message) {
        super("form_doctor", "doctor", doctor);
        this.addObject("message", message);
        this.addObject("metodo", "Adicionar ");
        this.addObject("dias_semana", Week.values());
    }
}
