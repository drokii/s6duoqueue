package dao;

import models.Mention;
import models.Tweet;

import javax.persistence.Query;
import java.util.List;

public class MentionDAO extends EntityDAO<Mention> {

    public MentionDAO() {
    }

    public List<Tweet> findAll() {
        Query query = em.createQuery("SELECT t FROM Mention t");
        return (List<Tweet>) query.getResultList();
    }

    public Mention find(Long id) {
        return em.find(Mention.class, id);
    }
}
