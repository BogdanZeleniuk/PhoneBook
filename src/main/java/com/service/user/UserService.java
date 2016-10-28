package com.service.user;


import com.controller.exception.NotFoundException;
import com.dto.UserDTO;
import com.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByLogin(String login) throws NotFoundException;

    void update(UserDTO user) throws NotFoundException;

    List<User> getAll();

    void update(User user);
}
