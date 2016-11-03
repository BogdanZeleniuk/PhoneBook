package com.controller.user;

import com.AuthorizedUser;
import com.dto.UserDTO;
import com.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(RegisterRestController.REST_URL)
public class RegisterRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserDTO userTo) {
        userTo.setId(AuthorizedUser.id());
        super.update(userTo);
    }
}