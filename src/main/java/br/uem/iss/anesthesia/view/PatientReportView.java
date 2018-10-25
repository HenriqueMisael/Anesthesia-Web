package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class PatientReportView extends ModelAndView {

    public PatientReportView(String initial, String end, DoctorModel doctor, List<AppointmentModel> appointments) {
        super("report_patient-report", "appointments", appointments);
        String description = "Consultas";
        if (doctor != null) {
            description += " com o médico " + doctor.getName();
        }
        description += " de " + initial + " a " + end;
        addObject("description", description);
    }
}
