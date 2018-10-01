package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.entity.Week;
import org.springframework.web.servlet.ModelAndView;

public class ExamFormView extends ModelAndView {
    public ExamFormView(ExamModel exam, String message) {
        super("layouts/app", "exam", exam);
        this.addObject("message", message);
        this.addObject("metodo", "Adicionar ");
        this.addObject("dias_semana", Week.values());
        this.addObject("conteudo", "form_doctor");
    }
}
