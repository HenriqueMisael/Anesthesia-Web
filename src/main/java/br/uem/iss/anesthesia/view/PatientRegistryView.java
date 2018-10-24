package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.PatientModel;

public class PatientRegistryView extends AbstractModelAndView {

    public PatientRegistryView(PatientModel patientModel) {
        super("report_patient-registry", "patient", patientModel);
    }
}
