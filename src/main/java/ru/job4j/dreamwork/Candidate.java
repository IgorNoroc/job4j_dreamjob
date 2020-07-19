package ru.job4j.dreamwork;

import java.util.Objects;

public class Candidate {
    private String name;
    private String id;
    private String cv;

    public Candidate(String name, String id, String cv) {
        this.name = name;
        this.id = id;
        this.cv = cv;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getCv() {
        return cv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name)
                && Objects.equals(id, candidate.id)
                && Objects.equals(cv, candidate.cv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, cv);
    }
}
