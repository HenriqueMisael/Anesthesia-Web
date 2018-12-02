package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.controller.request.AppointmentRequest;
import br.uem.iss.anesthesia.model.entity.*;

import java.util.ArrayList;

public class ConsultFormView extends AbstractModelAndView {

    public ConsultFormView(AppointmentRequest consults, Iterable<ProcessModel> process, ArrayList<String> timeConsult) {
        super("form_consult", "consult", consults);
        this.addObject("message", consults);
        this.addObject("metodo", "Adicionar ");
        this.addObject("medicalprocedures",process);
        this.addObject("process",consults.getProcess());
        this.addObject("date", consults.getDate());
        this.addObject("hour", consults.getHour());
        this.addObject("timeConsult", timeConsult);
        this.addObject("metodo", "Adicionar ");

    }
}
