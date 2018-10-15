package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.MedicineModel;
import org.springframework.web.servlet.ModelAndView;

public class MedicineFormView  extends ModelAndView {

    public MedicineFormView(MedicineModel medicine, String message) {
        super("layouts/app", "medicine", medicine);
        this.addObject("conteudo", "form_medicine");
        this.addObject("message", message);
    }
}
