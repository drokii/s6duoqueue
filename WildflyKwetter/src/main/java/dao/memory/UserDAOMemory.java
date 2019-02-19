package dao.memory;

import dao.EntityDAO;
import models.User;

import javax.ejb.DependsOn;
import javax.inject.Inject;
import java.util.List;

@DependsOn("DAORepository")
public class UserDAOMemory implements EntityDAO<User> {
    @Inject
    DAORepository repository;
    @Override
    public void create(User user) {
        repository.getUsers().add(user);
    }

    @Override
    public void delete(User user) {
        repository.getUsers().remove(user);
    }

    @Override
    public void update(User user) {
        repository.getUsers().set(repository.getUsers().indexOf(user), user);
    }

    @Override
    public User find(int id) {
        return repository.getUsers().get(id);
    }

    @Override
    public List<User> findAll() {
        return repository.getUsers();
    }
}
