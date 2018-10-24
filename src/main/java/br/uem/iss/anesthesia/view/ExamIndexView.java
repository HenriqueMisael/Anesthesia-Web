package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ExamModel;

public class ExamIndexView extends AbstractModelAndView {

    public ExamIndexView(Iterable<ExamModel> exam) {
        super("index_exam", "exam", exam);
    }
}
