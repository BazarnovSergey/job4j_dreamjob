package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class UserDBStore {
    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    private static final Logger LOG = LoggerFactory.getLogger(UserDBStore.class.getName());

    public Optional<User> add(User user) {
        Optional<User> result = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO users(name,email, password ) VALUES (?,?,?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
            result = Optional.of(user);
        } catch (Exception e) {
            LOG.error("exception: ", e);
        }
        return result;
    }

    public Optional<User> findUserByEmailAndPwd(String email, String password) {
        Optional<User> rsl = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT * FROM users WHERE email = ? AND password = ? ")
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    rsl = Optional.of(new User(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("email"),
                            it.getString("password")));
                    return rsl;
                }
            }
        } catch (Exception e) {
            LOG.error("exception: ", e);
        }
        return rsl;
    }
}
