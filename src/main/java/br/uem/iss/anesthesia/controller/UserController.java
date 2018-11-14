package br.uem.iss.anesthesia.controller;

import br.uem.iss.anesthesia.model.business.SaveUserBusiness;
import br.uem.iss.anesthesia.model.business.exception.BusinessRuleException;
import br.uem.iss.anesthesia.model.entity.UserModel;
import br.uem.iss.anesthesia.model.entity.UserType;
import br.uem.iss.anesthesia.model.repository.UserRepository;
import br.uem.iss.anesthesia.view.UserView;
import br.uem.iss.anesthesia.view.UsersView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SaveUserBusiness saveUserBusiness;

    @GetMapping
    public ModelAndView listUsers() {
        Iterable<UserModel> users;
        users = userRepository.findAll();
        return new UsersView(users);
    }

    @GetMapping("/new")
    public ModelAndView newUser() {
        return viewWithoutMessage(new UserModel());
    }

    @GetMapping("/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        return viewWithoutMessage(userRepository.findById(id).get());
    }

    @PostMapping
    public ModelAndView savePatient(@Valid UserModel user) {
        try {
            saveUserBusiness.save(user);
            return listUsers();
        } catch (BusinessRuleException e) {
            return viewWithMessage(user, e.getMessage());
        }
    }

    private UserView viewWithoutMessage(UserModel user) {
        return viewWithMessage(user, null);
    }

    private UserView viewWithMessage(UserModel user, String message) {
        return new UserView(user, message, UserType.values());
    }
}
