package ru.job4j.dream.model;

import java.util.Objects;

/**
 * Candidate for tack: dream job.
 */
public class Candidate {
    private int id;
    private String name;
    private String photo;
    private int cityId;

    public Candidate(int id, String name, String photo, int cityId) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.cityId = cityId;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCityId() {
        return cityId;
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
        return id == candidate.id
                && cityId == candidate.cityId
                && Objects.equals(name, candidate.name)
                && Objects.equals(photo, candidate.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photo, cityId);
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id
                + ", name='" + name + '\''
                + ", photo='" + photo + '\''
                + ", cityId=" + cityId
                + '}';
    }
}
