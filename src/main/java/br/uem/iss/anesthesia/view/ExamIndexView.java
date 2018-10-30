package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ExamModel;

public class ExamIndexView extends AbstractModelAndView {

    public ExamIndexView(Iterable<ExamModel> exam,String name,boolean ativo) {
        super("index_exam", "exam", exam);
        this.addObject("filtro_name", name);
        this.addObject("filtro_ativo", ativo);
    }
}
