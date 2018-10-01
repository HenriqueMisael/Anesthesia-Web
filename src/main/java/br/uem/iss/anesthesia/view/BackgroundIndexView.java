package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import org.springframework.web.servlet.ModelAndView;

public class BackgroundIndexView extends ModelAndView{

    public BackgroundIndexView(Iterable<BackgroundModel> backgrounds) {
        super("layouts/app", "backgrounds", backgrounds);
        this.addObject("conteudo", "index_background");
    }
}
