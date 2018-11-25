package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.*;

public class ProcessFormView extends AbstractModelAndView {

    public ProcessFormView(String metodo, ProcessModel process, String message, Iterable<DoctorModel> doctors, Iterable<PatientModel> patients, Iterable<MedicalProcedureModel> medicalprocedures, Iterable<ExamModel> exams) {
        super("form_process", "process", process);
        this.addObject("message", message);
        this.addObject("metodo", metodo);
        this.addObject("doctors", doctors);
        this.addObject("patients", patients);
        this.addObject("medicalprocedures", medicalprocedures);
        this.addObject("exams", exams);
    }
}
