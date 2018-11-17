package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.*;

public class ConsultFormView extends AbstractModelAndView {

    public ConsultFormView(AppointmentModel consults) {
        super("form_consult", "consults", consults);
        this.addObject("message", consults);
        this.addObject("metodo", "Adicionar ");
        this.addObject("medicalprocedures", consults.getProcess());
        this.addObject("date", consults.getDate());
    }
}
