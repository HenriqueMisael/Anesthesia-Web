package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ProcessModel;

public class ProcessIndexView extends AbstractModelAndView {

    public ProcessIndexView(Iterable<ProcessModel> process, String codigo, String nome_paciente, String nome_medico) {
        super("index_process", "process", process);
        this.addObject("filtro_codigo", codigo);
        this.addObject("filtro_nome_paciente", nome_paciente);
        this.addObject("filtro_nome_medico", nome_medico);
    }
}
