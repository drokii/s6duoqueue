package DAO.interfaces;

import models.Mention;
import models.Tweet;
import models.User;

public interface MentionDAO {
    void addMention(Mention mention);
    Mention getMention(int id);
    void removeMention(int id);
    void updateMention(int id, String msg);
}
