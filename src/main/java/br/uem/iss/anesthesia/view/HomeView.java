package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.UserModel;

public class HomeView extends AbstractModelAndView {

    public HomeView(UserModel user, String message) {
        super("home", "user", user);
        this.addObject("message", message);
    }
}
