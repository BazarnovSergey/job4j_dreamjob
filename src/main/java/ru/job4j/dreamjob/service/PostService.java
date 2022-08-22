package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDBStore;

import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class PostService {

    private final PostDBStore postDBStore;
    private final CityService cityService = new CityService();

    public PostService(PostDBStore postDBStore) {
        this.postDBStore = postDBStore;
    }

    public Post findById(int id) {
        return postDBStore.findById(id);
    }

    public Collection<Post> findAll() {
        List<Post> posts = postDBStore.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return posts;
    }

    public void add(Post post) {
        postDBStore.add(post);
    }

    public void update(Post post) {
        postDBStore.update(post);
    }

}
