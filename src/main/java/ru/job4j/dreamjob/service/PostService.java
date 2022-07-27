package ru.job4j.dreamjob.service;

import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import java.util.Collection;

public class PostService {

    private static final PostService INSTANCE = new PostService();

    private final PostStore postStore = PostStore.instOf();

    private PostService() {

    }

    public static PostService getInstance() {
          return INSTANCE;
    }

    public Post findById(int id) {
        return postStore.findById(id);
    }

    public Collection<Post> findAll() {
        return postStore.findAll();
    }

    public void add(Post post) {
        postStore.add(post);
    }

    public void update(Post post) {
        postStore.update(post);
    }
}
