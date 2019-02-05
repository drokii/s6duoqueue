package DAO.interfaces;

import models.Trend;
import models.Tweet;
import models.User;

public interface TrendDAO {
    void addTrend(Trend tweet);
    User getTweet(int id);
}
