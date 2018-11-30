package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.controller.request.PatientReportRequest;
import br.uem.iss.anesthesia.controller.request.ProcessReportRequest;
import br.uem.iss.anesthesia.model.entity.AppointmentModel;
import br.uem.iss.anesthesia.model.entity.DoctorModel;
import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.entity.ProcessModel;
import br.uem.iss.anesthesia.model.repository.AppointmentRepository;
import br.uem.iss.anesthesia.model.repository.DoctorRepository;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.model.repository.ProcessRepository;
import br.uem.iss.anesthesia.util.DateSupport;
import br.uem.iss.anesthesia.view.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private ProcessRepository processRepository;
    private AppointmentRepository appointmentRepository;
    private DateSupport dateSupport;
    private ProcessRepository processRepository;

    public ReportController(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, DateSupport dateSupport, ProcessRepository processRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.processRepository = processRepository;
        this.appointmentRepository = appointmentRepository;
        this.dateSupport = dateSupport;
        this.processRepository = processRepository;
    }

    @GetMapping
    public AbstractModelAndView home() {
        return new AbstractModelAndView("report_index");
    }

    @GetMapping("/patient-registry/{id}")
    public ModelAndView patientRegistry(@PathVariable Long id) {
        return new PatientRegistryView(patientRepository.findById(id).get());
    }

    @GetMapping("/patient-report")
    public AbstractModelAndView patientReport() {
        return new PatientReportFormView(doctorRepository.findAll());
    }

    @PostMapping("/patient-report")

    @GetMapping("/process-report")
    public AbstractModelAndView formProcessReport() {
        return new ProcessReportFormView(patientRepository.findAll(), new ProcessReportRequest(), null);
    }

    @PostMapping("/process-report")
    public ModelAndView processReport(@ModelAttribute ProcessReportRequest request) {
        Iterable<ProcessModel> process;
        if(request.getInitial() != null && request.getEnd() != null && request.getPatient() != null){
            process = processRepository.findByInicialDateAfterAndInicialDateBeforeAndPatientEquals(request.getInitial(), request.getEnd(), request.getPatient());
        }else if(request.getInitial() != null && request.getEnd() != null ){
            process = processRepository.findByInicialDateAfterAndInicialDateBefore(request.getInitial(), request.getEnd());
        }else if(request.getInitial() != null && request.getPatient() != null ){
            process = processRepository.findByInicialDateAfterAndPatientEquals(request.getInitial(), request.getPatient());
        }else if(request.getEnd() != null && request.getPatient() != null){
            process = processRepository.findByInicialDateBeforeAndPatientEquals(request.getInitial(), request.getPatient());
        }else if(request.getEnd() != null){
            process = processRepository.findByInicialDateBefore(request.getEnd());
        }else if(request.getPatient() != null){
            process = processRepository.findByPatientEquals(request.getPatient());
        }else if (request.getInitial() != null){
            process = processRepository.findByInicialDateAfter(request.getInitial());
        }else{
            process = processRepository.findByActiveTrue();
        }
        return new ProcessReportFormView(patientRepository.findAll(), request, process);
    }
    /*@PostMapping("/patient-report")
    public ModelAndView patientReport(@ModelAttribute PatientReportRequest request) {
        if(request.getInitial() == null) {
            return getPatientReportViewError(request, "Data inicial não preenchida");
        } else if(request.getEnd() == null) {
            return getPatientReportViewError(request, "Data final não preenchida");
        }

        LocalDateTime initial = request.getInitial().atStartOfDay();
        LocalDateTime end = request.getEnd().atTime(23, 59, 59);

        if(end.isBefore(initial)) {
            return getPatientReportViewError(request, "Data final não pode ser anterior à inicial");
        }

        List<AppointmentModel> appointments;
        DoctorModel doctor = request.getDoctor();

        if (doctor == null) {
            appointments = appointmentRepository.findByDateBetween(initial, end);
        } else {
            appointments = appointmentRepository.findByDateBetweenAndProcessIn(initial, end, processRepository.findByDoctor(doctor));
        }

        if(appointments.isEmpty()) {
            return getPatientReportViewError(request, "Nenhuma consulta encontrada nos filtros informados");
        }

        return new PatientReportView(dateSupport.format(initial), dateSupport.format(end), doctor, appointments);
    }

    private ModelAndView getPatientReportViewError(@ModelAttribute PatientReportRequest request, String message) {
        return new PatientReportFormView(doctorRepository.findAll(), request, message);
    }
}
