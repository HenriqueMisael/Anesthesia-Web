package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.controller.request.PatientReportRequest;
import br.uem.iss.anesthesia.model.entity.DoctorModel;

public class PatientReportFormView extends AbstractModelAndView {

    public PatientReportFormView(Iterable<DoctorModel> doctors) {
        this(doctors, new PatientReportRequest(), null);
    }

    public PatientReportFormView(Iterable<DoctorModel> doctors, PatientReportRequest filter, String message) {
        super("form_patient-report", "filter", filter);
        addObject("doctors", doctors);
        addObject("message", message);
    }
}
