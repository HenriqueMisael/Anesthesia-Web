package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;

public class BackgroundView extends AbstractModelAndView {

    public BackgroundView(BackgroundModel background, String message) {
        super("form_background", "background", background);
        this.addObject("message", message);
    }
}
