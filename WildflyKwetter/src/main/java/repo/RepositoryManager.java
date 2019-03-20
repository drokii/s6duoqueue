package repo;

import presenters.helper.LogInRequest;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RepositoryManager {

    @Inject
    private EntityManager em;

    public List<LogInRequest> queryCache(){
        Query query = em.createQuery("FROM SimpleProperty");

        List <LogInRequest> list =  query.getResultList();
        return list;
    }
}
