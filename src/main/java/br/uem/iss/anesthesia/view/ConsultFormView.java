package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.*;

public class ConsultFormView extends AbstractModelAndView {

    public ConsultFormView(AppointmentModel consults, Iterable<ProcessModel> process) {
        super("form_consult", "consult", consults);
        this.addObject("message", consults);
        this.addObject("metodo", "Adicionar ");
        this.addObject("medicalprocedures",process);
        this.addObject("date", consults.getDate());
        this.addObject("metodo", "Adicionar ");
    }
}
