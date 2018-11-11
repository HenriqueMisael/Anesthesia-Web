package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ConsultModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;

public class ConsultIndexView extends AbstractModelAndView {

    public ConsultIndexView(Iterable<ConsultModel> consults, String number, boolean ativo) {
        super("index_consult", "consults", consults);
        this.addObject("filtro_number", number);
        this.addObject("filtro_ativo", ativo);
    }
}
