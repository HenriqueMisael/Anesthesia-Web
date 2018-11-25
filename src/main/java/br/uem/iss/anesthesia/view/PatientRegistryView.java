package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.PatientModel;
import org.springframework.web.servlet.ModelAndView;

public class PatientRegistryView extends ModelAndView {

    public PatientRegistryView(PatientModel patientModel) {
        super("report_patient-registry", "patient", patientModel);
    }
}
