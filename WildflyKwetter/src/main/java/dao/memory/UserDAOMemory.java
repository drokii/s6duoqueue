package dao.memory;

import dao.EntityDAO;
import exceptions.UserNotFoundException;
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
        user.setId(repository.getUsers().size());
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
    public User find(int id) throws UserNotFoundException {
        try{
            return repository.getUsers().get(id);
        }catch(IndexOutOfBoundsException e){
            throw new UserNotFoundException();
        }

    }

    @Override
    public List<User> findAll() {
        return repository.getUsers();
    }

    public User findByUsername(String username) throws UserNotFoundException {
        for (User user: repository.getUsers()
             ) {
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        throw  new UserNotFoundException();
    }
}
