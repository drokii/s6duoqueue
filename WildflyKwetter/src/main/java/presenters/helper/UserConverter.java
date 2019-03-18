package presenters.helper;

import exceptions.UserNotFoundException;
import models.User;
import services.UserService;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

// You must annotate the converter as a managed bean, if you want to inject
// anything into it, like your persistence unit for example.
@FacesConverter(value = "userConverter")
public class UserConverter implements Converter {

    @Inject
    UserService userService;

    @Override
    public User getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        try {
            return userService.getUserById(Integer.valueOf(s));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid User ID", s)), e);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }
}