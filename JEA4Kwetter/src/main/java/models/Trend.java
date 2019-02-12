package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Trend {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int occurrences;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trend trend = (Trend) o;
        return id == trend.id &&
                occurrences == trend.occurrences &&
                Objects.equals(name, trend.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, occurrences);
    }
}
