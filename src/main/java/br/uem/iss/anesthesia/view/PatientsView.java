package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.PatientModel;

public class PatientsView extends AbstractModelAndView {

    public PatientsView(Iterable<PatientModel> patients) {
        super("index_patient", "patients", patients);
    }
}
