package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ConsultModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;

public class ConsultFormView extends AbstractModelAndView {

    public ConsultFormView(ConsultModel consult) {
        super("form_consult", "consult", consult);
        this.addObject("message", consult);
        this.addObject("metodo", "Adicionar ");
    }
}
