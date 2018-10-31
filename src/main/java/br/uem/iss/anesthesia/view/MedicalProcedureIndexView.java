package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.MedicalProcedureModel;

public class MedicalProcedureIndexView extends AbstractModelAndView {
    public MedicalProcedureIndexView(Iterable<MedicalProcedureModel> medicalProcedures){
        super("index_medicalProcedure", "medicalProcedures", medicalProcedures);
    }
}