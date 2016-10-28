package com.repository.user;


import com.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByLogin(String login);

    List<User> getAll();
}
