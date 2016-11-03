package com.controller;

import com.controller.user.AbstractUserController;
import com.dto.UserDTO;
import com.dto.UserUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController{

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contactList() {
        return "contacts";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) boolean message){
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    @RequestMapping(value="/logout_sec", method = {RequestMethod.GET, RequestMethod.POST})
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout_sec";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("register", true);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(@Valid UserDTO userDTO, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createNewUserFromDTO(userDTO));
                status.setComplete();
                return "login";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("login", "User with such login is presented");
            }
        }
        model.addAttribute("register", true);
        return "register";
    }
}
