package com.dto;

import com.model.Role;
import com.model.User;

public class UserUtil {

    public static User createNewUserFromDTO(UserDTO newUser) {
        return new User(null, newUser.getLogin(), newUser.getPassword(), newUser.getFullName(), Role.ROLE_USER);
    }

    public static UserDTO userAsDTO(User user) {
        return new UserDTO(user.getId(), user.getLogin(), user.getPassword(), user.getFullName());
    }

    public static void updateUserFromDTO(User user, UserDTO userDTO) {
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
    }

    public static User prepareToSave(User user) {
        user.setPassword(user.getPassword());
        user.setLogin(user.getLogin());
        return user;
    }
}
