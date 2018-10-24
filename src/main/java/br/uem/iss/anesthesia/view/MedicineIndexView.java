package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.MedicineModel;

public class MedicineIndexView extends AbstractModelAndView {

    public MedicineIndexView(Iterable<MedicineModel> medicines) {
        super("index_medicine", "medicines", medicines);
    }
}
