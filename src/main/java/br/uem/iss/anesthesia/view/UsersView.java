package br.uem.iss.anesthesia.view;

import br.uem.iss.anesthesia.model.entity.UserModel;

public class UsersView extends AbstractModelAndView {

    public UsersView(Iterable<UserModel> users) {
        super("index_user", "users", users);
    }
}
