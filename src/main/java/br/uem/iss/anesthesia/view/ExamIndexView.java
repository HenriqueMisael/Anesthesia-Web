package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import org.springframework.web.servlet.ModelAndView;

public class ExamIndexView extends ModelAndView {

    public ExamIndexView(Iterable<ExamModel> exam){
        super("layouts/app", "exam", exam);
        this.addObject("conteudo", "index_exam");
        
    }
}
