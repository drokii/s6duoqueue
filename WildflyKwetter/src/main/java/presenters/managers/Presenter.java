package presenters.managers;
import dao.jpa.ServiceBean;
import models.SimpleProperty;
import repo.RepositoryManager;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;


@Model
public class Presenter {

    @Inject
    ServiceBean ejb;

    @Inject
    RepositoryManager db;

    @Produces
    @Named
    SimpleProperty property;

    private List<SimpleProperty> propertyList;

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final SimpleProperty member) {
        retrieveData();
    }

    @Produces
    @Named
    public List<SimpleProperty> getPropertyList() {
        return propertyList;
    }

    public void setProperty(List<SimpleProperty> property) {
        this.propertyList = propertyList;
    }

    public void retrieveData() {
        propertyList = db.queryCache();

    }

    @PostConstruct
    public void initNewProperty() throws IOException {
        property = new SimpleProperty();
        retrieveData();
    }

    public void save() throws IOException {
        ejb.put(property);
        initNewProperty();
    }

    public void clear(SimpleProperty property) {
        ejb.delete(property);

    }

}