package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.BackgroundModel;

public class BackgroundIndexView extends AbstractModelAndView {

    public BackgroundIndexView(Iterable<BackgroundModel> backgrounds) {
        super("index_background", "backgrounds", backgrounds);
    }
}
