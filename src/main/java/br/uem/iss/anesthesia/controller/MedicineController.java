package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveMedicineBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.MedicineModel;
import br.uem.iss.anesthesia.model.repository.MedicineRepository;
import br.uem.iss.anesthesia.view.MedicineFormView;
import br.uem.iss.anesthesia.view.MedicineIndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private SaveMedicineBusiness saveMedicineBusiness;

    @GetMapping
    public ModelAndView listMedicines(@RequestParam(value = "name", required = false) String name) {
        Iterable<MedicineModel> medicines;
        if (name != null) {
            medicines = medicineRepository.findAll();
        } else {
            medicines = medicineRepository.findAll();
        }
        return new MedicineIndexView(medicines);
    }

    @GetMapping("/new")
    public MedicineFormView newMedicine() {
        return viewWithoutMessage(new MedicineModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editMedicine(@PathVariable Long id) {
        return viewWithoutMessage(medicineRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView saveMedicine(@Valid MedicineModel medicine) {
        try {
            saveMedicineBusiness.save(medicine);
            return listMedicines(null);
        } catch (BusinessRuleException e) {
            return viewWithMessage(medicine, e.getMessage());
        }
    }

    private MedicineFormView viewWithoutMessage(MedicineModel medicine) {
        return viewWithMessage(medicine, null);
    }

    private MedicineFormView viewWithMessage(MedicineModel medicine, String message) {
        return new MedicineFormView(medicine, message);
    }
}
