package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.DoctorModel;

public class DoctorIndexView extends AbstractModelAndView {

    public DoctorIndexView(Iterable<DoctorModel> doctors, String name, String crm, boolean ativo) {
        super("index_doctor", "doctors", doctors);
        this.addObject("filtro_name", name);
        this.addObject("filtro_crm", crm);
        this.addObject("filtro_ativo", ativo);
    }
}
