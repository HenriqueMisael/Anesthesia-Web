package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.controller.request.PatientReportRequest;
import br.uem.iss.anesthesia.model.entity.DoctorModel;

public class PatientReportFormView extends AbstractModelAndView {

    public PatientReportFormView(Iterable<DoctorModel> doctors) {
        super("form_patient-report", "filter", new PatientReportRequest());
        addObject("doctors", doctors);
    }
}
