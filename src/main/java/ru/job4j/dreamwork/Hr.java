package ru.job4j.dreamwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hr {
    private String name;
    private String id;
    private List<String> offers = new ArrayList<>();

    public Hr(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<String> getOffers() {
        return offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hr hr = (Hr) o;
        return Objects.equals(name, hr.name)
                && Objects.equals(id, hr.id)
                && Objects.equals(offers, hr.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, offers);
    }
}
