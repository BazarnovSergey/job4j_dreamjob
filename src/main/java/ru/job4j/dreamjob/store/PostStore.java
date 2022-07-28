package ru.job4j.dreamjob.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger();

    private PostStore() {
        posts.put(1, new Post(id.incrementAndGet(), "Junior Java Job", "Description Junior Java Job"));
        posts.put(2, new Post(id.incrementAndGet(), "Middle Java Job", "Description Middle Java Job"));
        posts.put(3, new Post(id.incrementAndGet(), "Senior Java Job", "Description Senior Java Job"));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(id.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.replace(post.getId(), post);
    }

}