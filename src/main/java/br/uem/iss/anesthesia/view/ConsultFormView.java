package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.*;

public class ConsultFormView extends AbstractModelAndView {

    public ConsultFormView(ConsultModel consults, Iterable<DoctorModel> doctors, Iterable<PatientModel> patients, Iterable<MedicalProcedureModel> medicalprocedures) {
        super("form_consult", "consults", consults);
        this.addObject("message", consults);
        this.addObject("metodo", "Adicionar ");
        this.addObject("doctors", doctors);
        this.addObject("patients", patients);
        this.addObject("medicalprocedures", medicalprocedures);
    }
}
