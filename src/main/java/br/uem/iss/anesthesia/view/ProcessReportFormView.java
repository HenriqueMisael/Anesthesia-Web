package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.controller.request.PatientReportRequest;
import br.uem.iss.anesthesia.controller.request.ProcessReportRequest;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;

public class ProcessReportFormView extends AbstractModelAndView {

    public ProcessReportFormView(Iterable<PatientModel> patients, ProcessReportRequest processReportRequest, Iterable<ProcessModel> process) {
        super("form_process-report", "filter", processReportRequest);
        addObject("patients", patients);
        addObject("process", process);
    }
}
