package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.User;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UserDBStoreTest {

    @Test
    void WhenFindUserByEmailAndPwd() {
        UserDBStore store = new UserDBStore(new Main().loadPool());
        User userInDb = new User();
        User user = new User(0, "Sergey", "Sergey@gmail.com", "321");
        store.add(user);
        Optional<User> optionalUser =
                store.findUserByEmailAndPwd(user.getEmail(),user.getPassword());
        if (optionalUser.isPresent()){
            userInDb = optionalUser.get();
        }
        assertThat(userInDb.getEmail(), is(user.getEmail()));
        assertThat(userInDb.getPassword(), is(user.getPassword()));
    }

}