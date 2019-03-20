package dao.jpa;

import presenters.helper.LogInRequest;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Stateless
public class ServiceBean {

    @Inject
    private Event<LogInRequest> propEventSrc;

    @Inject
    private EntityManager em;


    public void put(LogInRequest p){
        em.persist(p);
        propEventSrc.fire(p);
    }

    public void delete(LogInRequest p){

        Query query = em.createQuery("delete FROM SimpleProperty p where p.key='"+p.getUsername()+"'");
        query.executeUpdate();
        propEventSrc.fire(p);
    }

}