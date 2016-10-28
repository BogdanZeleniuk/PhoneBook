package com;

import com.dto.UserDTO;
import com.dto.UserUtil;
import com.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User{
    private static final long serialVersionUID = 1L;

    private UserDTO userDTO;

    public AuthorizedUser(User user) {
        super(user.getLogin(), user.getPassword(), true,  true, true, true, user.getRoles());
        this.userDTO = UserUtil.userAsDTO(user);
    }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().userDTO.getId();
    }

    public void update(UserDTO newDTO) {
        userDTO = newDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    @Override
    public String toString() {
        return userDTO.toString();
    }
}
