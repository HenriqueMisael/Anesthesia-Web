package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ExamModel;
import br.uem.iss.anesthesia.model.entity.MedicalProcedureModel;

public class MedicalProcedureFormView extends AbstractModelAndView {
    public MedicalProcedureFormView(MedicalProcedureModel medicalProcedure, String message, Iterable<ExamModel> exams){
        super("form_medicalProcedure", "medicalProcedure", medicalProcedure);
        this.addObject("message", message);
        this.addObject("exams", exams);
    }
}
