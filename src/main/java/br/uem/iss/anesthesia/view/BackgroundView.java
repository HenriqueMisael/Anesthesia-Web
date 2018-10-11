package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;
import org.springframework.web.servlet.ModelAndView;

public class BackgroundView extends ModelAndView {

    public BackgroundView(BackgroundModel background, String message) {
        super("layouts/app", "background", background);
        this.addObject("conteudo", "form_background");
        this.addObject("message", message);
    }
}
