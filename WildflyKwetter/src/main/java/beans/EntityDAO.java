package beans;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class EntityDAO<T> {
    @Inject

    protected EntityManager em;


    @Inject
    private Event<T> propEventSrc;

    public void create(T t) {
        em.persist(t);
        propEventSrc.fire(t);
    }

    public void delete(T t) {
        em.remove(t);
        propEventSrc.fire(t);
    }

    public void update(T t) {
        em.merge(t);
        propEventSrc.fire(t);

    }
}
