package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

/**
 * Interface Store for task: dream job.
 */
public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    Post findPostById(int id);

    void save(Candidate candidate);

    Candidate findCandidateById(int id);

    void deleteCandidate(Candidate candidate);

    void addUser(User user);

    User findUserById(int id);

    Collection<User> getAllUsers();

    void deleteUser(User user);

    User findByEmail(String email);

    Collection<City> getAllCities();
}
