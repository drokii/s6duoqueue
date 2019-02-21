package dao.memory;

import dao.EntityDAO;
import exceptions.TweetNotFoundException;
import exceptions.UserNotFoundException;
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
        tweet.setId(repository.getTweets().size());
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
    public Tweet find(int id) throws TweetNotFoundException {
        try{
            Tweet tweet = repository.getTweets().get(id);
            return tweet;
        }catch (IndexOutOfBoundsException e){
            throw new TweetNotFoundException();
        }



    }

    @Override
    public List<Tweet> findAll() {
        return repository.getTweets();
    }


}
