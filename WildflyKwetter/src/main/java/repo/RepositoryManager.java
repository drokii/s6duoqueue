package repo;

import models.SimpleProperty;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepositoryManager {

    @Inject
    private EntityManager em;

    public List<SimpleProperty> queryCache(){
        Query query = em.createQuery("FROM SimpleProperty");

        List <SimpleProperty> list =  query.getResultList();
        return list;
    }
}
