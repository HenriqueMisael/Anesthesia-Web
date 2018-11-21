package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.entity.PatientModel;
import br.uem.iss.anesthesia.model.repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PatientsControllerTest {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MockMvc mvc;

    @Test
    public void listAll() throws Exception {
        insertPatient("Henrique Misael", "Machado", "09213087950");
        insertPatient("Julio Cesar", "Machado", "38166666987");
        insertPatient("Luciana Rathunde", "Machado", "17173025052");
        insertPatient("Ricardo", "Ribeiro e Silva", "49222100085");

        ModelAndView modelAndView = mvc.perform(get("/patient")).andExpect(status().isOk()).andReturn().getModelAndView();

        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "layouts/app");
        assertEquals(modelAndView.getModel().get("patients"), patientRepository.findAll());
        assertEquals(modelAndView.getModel().get("conteudo"), "index_patient");
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