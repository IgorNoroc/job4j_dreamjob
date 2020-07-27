package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Local Memory Storage for task: dream job.
 */
public class MemStore implements Store {
    /**
     * Create singleton.
     */
    private static final MemStore INST = new MemStore();
    /**
     * storage for posts.
     */
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    /**
     * storage for candidates.
     */
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    /**
     * storage for users.
     */
    private Map<Integer, User> users = new ConcurrentHashMap<>();
    private static AtomicInteger POST_ID = new AtomicInteger(4);
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    private static AtomicInteger USER_ID = new AtomicInteger(2);

    /**
     * When at first time creating instance of this class,
     * the storage is initialize with the following fields.
     */
    private MemStore() {
        posts.put(1, new Post(1, "Junior Java Job"));
        posts.put(2, new Post(2, "Middle Java Job"));
        posts.put(3, new Post(3, "Senior Java Job"));
        candidates.put(1, new Candidate(1, "Junior Java", ""));
        candidates.put(2, new Candidate(2, "Middle Java", ""));
        candidates.put(3, new Candidate(3, "Senior Java", ""));
        users.put(1, new User(1, "Igor", "igor.com", "password"));
    }

    public static MemStore instOf() {
        return INST;
    }

    /**
     * return storage posts.
     *
     * @return list.
     */
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    /**
     * return storage candidates.
     *
     * @return list.
     */
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    /**
     * return storage users.
     *
     * @return map.
     */
    public Map<Integer, Post> getPosts() {
        return posts;
    }

    /**
     * Save post to storage and set id to post.
     *
     * @param post post.
     */
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.getAndIncrement());
        }
        posts.put(post.getId(), post);
    }

    /**
     * Save candidate to storage and set id to candidate.
     *
     * @param candidate candidate.
     */
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.getAndIncrement());
        }
        candidates.put(candidate.getId(), candidate);
    }

    /**
     * Save user to storage and set id to user.
     *
     * @param user user.
     */
    @Override
    public void addUser(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.getAndIncrement());
        }
        users.put(user.getId(), user);
    }

    public Post findPostById(int id) {
        return posts.get(id);
    }

    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }

    @Override
    public void deleteCandidate(Candidate candidate) {
        candidates.remove(candidate.getId(), candidate);
    }


    @Override
    public User findUserById(int id) {
        return users.get(id);
    }

    public Map<Integer, Candidate> getCandidates() {
        return candidates;
    }

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user.getId());
    }

    @Override
    public User findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail()
                        .equals(email)).findFirst().orElseGet(() -> new User(0, "", "", ""));
    }
}
