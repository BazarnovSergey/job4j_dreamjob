package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {

    @Test
    public void whenFindAll() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Salary 1000$",
                true, LocalDateTime.now(), new City(1, "Moscow"));
        store.add(post);
        List<Post> postList = store.findAll();
        assertThat(postList.get(0).getName(), is(post.getName()));
    }

    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Salary 1000$",
                true, LocalDateTime.now(), new City(1, "Moscow"));
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdate() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Salary 1000$",
                true, LocalDateTime.now(), new City(1, "Moscow"));
        store.add(post);
        store.update(new Post(post.getId(), "Java Job", "Salary 1500$",
                true, LocalDateTime.now(), new City(1, "Moscow")));
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getDescription(), is("Salary 1500$"));
    }

    @Test
    public void whenFindById() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(0, "Java Job", "Salary 1000$",
                true, LocalDateTime.now(), new City(1, "Moscow"));
        Post post2 = new Post(1, "Java Job", "Salary 1500$",
                true, LocalDateTime.now(), new City(2, "SPB"));
        store.add(post);
        store.add(post2);
        Post postInDb = store.findById(post.getId());
        Post post2InDb = store.findById(post2.getId());
        assertThat(postInDb.getName(), is(post.getName()));
        assertThat(post2InDb.getName(), is(post2.getName()));
    }
}