package com.controller.user;

import com.View;
import com.dto.UserDTO;
import com.dto.UserUtil;
import com.fasterxml.jackson.annotation.JsonView;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ajax/users")
public class UserAjaxController extends AbstractUserController{

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.UI.class)
    public List<User> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.UI.class)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrUpdate(@Valid UserDTO userTo) {
        try {
            if (userTo.isNew()) {
                super.create(UserUtil.createNewUserFromDTO(userTo));
            } else {
                super.update(userTo);
            }
        }
        catch (DataIntegrityViolationException exception){
            throw new DataIntegrityViolationException(messageSource.getMessage("exception.wrong.password", null, LocaleContextHolder.getLocale()));
        }
    }
}
