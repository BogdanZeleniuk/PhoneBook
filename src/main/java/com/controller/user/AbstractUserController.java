package com.controller.user;

import com.dto.UserDTO;
import com.model.User;
import com.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractUserController {

    @Autowired
    private UserService service;

    public List<User> getAll() {
        return service.getAll();
    }

    public User get(int id){
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        return service.save(user);
    }

    public void delete(int id){
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        service.update(user);
    }

    public void update(UserDTO userDTO) {
        service.update(userDTO);
    }

    public User getByLogin(String login) {
        return service.getByLogin(login);
    }
}
