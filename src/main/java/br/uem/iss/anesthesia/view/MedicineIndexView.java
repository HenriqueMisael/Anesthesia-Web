package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.MedicineModel;
import org.springframework.web.servlet.ModelAndView;

public class MedicineIndexView  extends ModelAndView {

    public MedicineIndexView(Iterable<MedicineModel> medicines) {
        super("layouts/app", "medicines", medicines);
        this.addObject("conteudo", "index_medicine");
    }
}
