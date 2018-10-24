package br.uem.iss.anesthesia.view;

public class AbstractModelAndView extends org.springframework.web.servlet.ModelAndView {

    public AbstractModelAndView(String viewName, String modelName, Object model) {
        this(viewName);
        this.addObject(modelName, model);
    }

    public AbstractModelAndView(String viewName) {
        super("layouts/app");
        this.addObject("conteudo", viewName);
    }
}
