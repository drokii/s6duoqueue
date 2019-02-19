package dao.jpa;

import models.Mention;

import javax.persistence.Query;
import java.util.List;

public class MentionDAOJPA extends EntityDAOJPA<Mention> {

    public MentionDAOJPA() {
    }

    @Override
    public List<Mention> findAll() {
        Query query = em.createQuery("SELECT t FROM Mention t");
        return (List<Mention>) query.getResultList();
    }

    @Override
    public Mention find(int id) {
        return em.find(Mention.class, id);
    }
}
