package dao.memory;

import dao.EntityDAO;
import models.Trend;
import models.Tweet;

import javax.ejb.DependsOn;
import javax.inject.Inject;
import java.util.List;

@DependsOn("DAORepository")
public class TweetDAOMemory implements EntityDAO<Tweet>{
    @Inject
    DAORepository repository;

    @Override
    public void create(Tweet tweet) {
        repository.getTweets().add(tweet);
    }

    @Override
    public void delete(Tweet tweet) {
        repository.getTweets().remove(tweet);
    }

    @Override
    public void update(Tweet tweet) {
        repository.getTweets().set(repository.getTweets().indexOf(tweet), tweet);
    }

    @Override
    public Tweet find(int id) {
        return repository.getTweets().get(id);
    }

    @Override
    public List<Tweet> findAll() {
        return repository.getTweets();
    }


}
