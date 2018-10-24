package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.MedicineModel;

public class MedicineFormView extends AbstractModelAndView {

    public MedicineFormView(MedicineModel medicine, String message) {
        super("form_medicine", "medicine", medicine);
        this.addObject("message", message);
    }
}
