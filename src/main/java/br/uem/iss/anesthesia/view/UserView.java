package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.UserModel;
import br.uem.iss.anesthesia.model.entity.UserType;

public class UserView extends AbstractModelAndView {

    public UserView(UserModel user, String message, UserType[] userTypeOptions) {
        super("form_user", "user", user);
        this.addObject("message", message);
        this.addObject("userTypeOptions", userTypeOptions);
        this.addObject("metodo", "Adicionar ");
    }
}
