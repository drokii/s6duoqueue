package dao;


import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GenericProducer {
    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager em;
}