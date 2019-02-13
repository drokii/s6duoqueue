package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Mention {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "mentioner_id")
    private User mentioner;

    @OneToOne
    @JoinColumn(name = "mentioned_id")
    private User mentioned;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mention mention = (Mention) o;
        return id == mention.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
