package com.controller;

import com.controller.user.AbstractUserController;
import com.dto.UserDTO;
import com.dto.UserUtil;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class RootController extends AbstractUserController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contactList() {
        return "contacts";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @PreAuthorize("hasRole('ROLE_USER')")
    public String login(Model model, @RequestParam(value = "error", required = false) boolean error){
        model.addAttribute("error", error);
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

    @RequestMapping(value = PATH)
    public String error(){
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(@Valid UserDTO userDTO, BindingResult result, SessionStatus status, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                super.create(UserUtil.createNewUserFromDTO(userDTO));
                status.setComplete();
                return "redirect:login?message=app.registered";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("Login", "---");
            }
        }
        model.addAttribute("register", true);
        return "contacts";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
