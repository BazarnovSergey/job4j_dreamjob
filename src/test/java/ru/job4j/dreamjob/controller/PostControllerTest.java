package ru.job4j.dreamjob.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostControllerTest {
    @Test
    public void whenPosts() {
        City city = new City(1, "Москва");
        List<Post> posts = Arrays.asList(
                new Post(1, "New post", "description",
                        true, LocalDateTime.now(), city),
                new Post(2, "New post", "description",
                        true, LocalDateTime.now(), city)
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        HttpSession session = mock(HttpSession.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        City city = new City(1, "Москва");
        Post input = new Post(1, "New post", "description",
                true, LocalDateTime.now(), city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);

        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() {
        City city = new City(1, "Москва");
        Post input = new Post(1, "New post", "description",
                true, LocalDateTime.now(), city);
        Post input2 = new Post(2, "New post", "description",
                true, LocalDateTime.now(), city);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);

        PostController postController = new PostController(
                postService,
                cityService
        );
        postController.createPost(input);
        String page = postController.updatePost(input2);
        verify(postService).update(input2);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFormAddPost() {
        List<City> cities = Arrays.asList(new City(1, "Москва"),
                new City(2, "СПб"), new City(3, "ЕКб"));
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        HttpSession session = mock(HttpSession.class);

        CityService cityService = mock(CityService.class);
        when(cityService.getAllCities()).thenReturn(cities);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.formAddPost(model, session);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addPost"));
    }

    @Test
    public void formUpdatePost() {
        City city = new City(1, "Москва");
        Post post = new Post(1, "New post", "description",
                true, LocalDateTime.now(), city);
        List<City> cities = Arrays.asList(new City(1, "Москва"),
                new City(2, "СПб"), new City(3, "ЕКб"));
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        HttpSession session = mock(HttpSession.class);
        CityService cityService = mock(CityService.class);
        when(postService.findById(1)).thenReturn(post);
        when(cityService.getAllCities()).thenReturn(cities);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.formUpdatePost(model, 1, session);
        verify(model).addAttribute("post", post);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("updatePost"));
    }
}