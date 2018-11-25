package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.UserModel;

public class LoginView extends AbstractModelAndView {

    public LoginView(UserModel user, String message) {
        super("login", "user", user);
        this.addObject("message", message);
    }
}
