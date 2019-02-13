package controllers;
import beans.SimplePropertyDAO;
import model.SimpleProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;



@Model
public class Manager {

    @Inject
    SimplePropertyDAO ejb;

    @Produces
    @Named
    SimpleProperty property;

    @Inject
    Producer producer;

    @PostConstruct
    public void initNewProperty() {
        property = new SimpleProperty();
    }

    public void save() {
        ejb.put(property);
        initNewProperty();
    }

    public void clear(SimpleProperty property) {
        ejb.delete(property);

    }

}