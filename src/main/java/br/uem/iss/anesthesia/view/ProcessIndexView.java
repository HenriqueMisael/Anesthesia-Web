package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.ProcessModel;
import org.springframework.web.servlet.ModelAndView;

public class ProcessIndexView extends ModelAndView {

    public ProcessIndexView(Iterable<ProcessModel> process, String name, String crm){
        super("layouts/app", "process", process);
        this.addObject("conteudo", "index_process");
        this.addObject("filtro_name", name);
        this.addObject("filtro_crm", crm);
    }
}
