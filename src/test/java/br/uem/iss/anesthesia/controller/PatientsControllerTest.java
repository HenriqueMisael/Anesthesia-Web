package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import br.uem.iss.anesthesia.view.PatientsView;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.xmlunit.util.Linqy.asList;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PatientsControllerTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private Gson gson;

    @Test
    public void savePatient() throws Exception {
        PatientModel patient = getPatient("092.130.879-50");

        savePatient(patient);

        assertPatientSaved(patient);
    }

    @Test
    public void savePatientWithWrongCpfFormat() throws Exception {
        PatientModel patient = getPatient("09213abc950");

        PatientsView view = savePatient(patient);

        assertEquals("Formato do CPF inválido: CPF deve ter 11 dígitos", view.getModel().get("message"));
    }

    @Test
    public void savePatientWithEmptyCpf() throws Exception {
        PatientModel patient = getPatient("");

        PatientsView view = savePatient(patient);

        assertEquals("Formato do CPF inválido: CPF deve ter 11 dígitos", view.getModel().get("message"));
    }

    private PatientsView savePatient(PatientModel patient) throws Exception {
        return (PatientsView) mvc.perform(post("/patient").content(gson.toJson(patient))).andReturn().getModelAndView();
    }

    private void assertPatientSaved(PatientModel patient) {
        List<PatientModel> patientModels = asList(patientRepository.findAll());

        assertNotNull(patientModels);
        assertEquals(1, patientModels.size());
        assertEquals(patient, patientModels.get(0));
    }

    private PatientModel getPatient(String cpf) {
        return getPatient("Henrique Misael", "Machado", cpf, "henrique.nolear@hotmail.com", "4433053658", "44997372668");
    }

    private PatientModel getPatient(String name, String surname, String cpf, String email, String phoneNumber, String cellphoneNumber) {
        PatientModel patientModel = new PatientModel();

        patientModel.setName(name);
        patientModel.setSurname(surname);
        patientModel.setCpf(cpf);
        patientModel.setEmail(email);
        patientModel.setPhoneNumber(phoneNumber);
        patientModel.setCellphoneNumber(cellphoneNumber);

        return patientModel;
    }

    private PatientModel insertPatient(String name, String surname, String cpf) {
        PatientModel patient = new PatientModel();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setCpf(cpf);

        patientRepository.save(patient);

        return patient;
    }
}