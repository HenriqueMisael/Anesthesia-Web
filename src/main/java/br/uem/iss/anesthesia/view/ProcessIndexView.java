package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ProcessModel;
import org.springframework.web.servlet.ModelAndView;

public class ProcessIndexView extends ModelAndView {

    public ProcessIndexView(Iterable<ProcessModel> process, String codigo, String nome_paciente, String nome_medico){
        super("layouts/app", "process", process);
        this.addObject("conteudo", "index_process");
        this.addObject("filtro_codigo", codigo);
        this.addObject("filtro_nome_paciente", nome_paciente);
        this.addObject("filtro_nome_medico", nome_medico);
    }
}
