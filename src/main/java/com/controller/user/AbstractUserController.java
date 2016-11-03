package com.controller.user;

import com.dto.UserDTO;
import com.model.User;
import com.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractUserController {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id){
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        LOG.info("create " + user);
        return service.save(user);
    }

    public void delete(int id){
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        LOG.info("update " + user);
        service.update(user);
    }

    public void update(UserDTO userDTO) {
        LOG.info("update " + userDTO);
        service.update(userDTO);
    }

    public User getByLogin(String login) {
        LOG.info("getByLogin " + login);
        return service.getByLogin(login);
    }
}
