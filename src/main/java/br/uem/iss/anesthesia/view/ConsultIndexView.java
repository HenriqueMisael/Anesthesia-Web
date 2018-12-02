package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.AppointmentModel;

public class ConsultIndexView extends AbstractModelAndView {

    public ConsultIndexView(Iterable<AppointmentModel> consults, String number, String codigo, String nome_paciente, String nome_medico) {
        super("index_consult", "consult", consults);
        this.addObject("filtro_number", number);
        this.addObject("filtro_ativo", codigo);
        this.addObject("nome_paciente", nome_paciente);
        this.addObject("filtro_nome_medico", nome_medico);
    }
}
