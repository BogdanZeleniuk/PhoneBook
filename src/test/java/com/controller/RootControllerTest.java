package com.controller;

import com.controller.contact.AbstractContactAjaxControllerTest;
import org.junit.Test;

import static com.ContactTestData.USER;
import static com.matcher.TestUtil.authorize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractContactAjaxControllerTest{
    @Test
    public void testContactList() throws Exception {
        authorize(USER);
        mockMvc.perform(get("/contacts"))
                .andDo(print())
                .andExpect(view().name("contacts"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/contacts.jsp"));
    }

    @Test
    public void testContactListWithoutAuthorization() throws Exception {
        mockMvc.perform(get("/contacts"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

}