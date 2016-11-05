package com.controller.contact;


import com.dto.JsonUtil;
import com.model.Contact;
import com.service.contact.ContactService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.ContactTestData.*;
import static com.matcher.TestUtil.authorize;
import static com.matcher.TestUtil.userHttpBasic;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ContactAjaxControllerTest extends AbstractContactAjaxControllerTest{

    private static final String REST_URL = "/ajax/contacts";

    @Autowired
    private ContactService service;

    @Test
    public void testDeleteNotFound() throws Exception {
        authorize(USER);
        mockMvc.perform(delete(REST_URL + "/5")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        authorize(USER);
        mockMvc.perform(delete(REST_URL + "/4")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        Assert.assertArrayEquals(new Contact[]{CONTACT1, CONTACT2, CONTACT3}, service.getAll(USER_ID).toArray());
    }

    @Test
    public void testUpdate() throws Exception {
        Contact updated = getUpdated();
        authorize(USER);
        mockMvc.perform(post(REST_URL + "/1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(CONTACT1.getId(), USER_ID));
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        Contact updated = getUpdated();
        authorize(USER);
        mockMvc.perform(put(REST_URL + "/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/exception/exception.jsp"));
    }


    @Test
    public void testCreate() throws Exception {
        Contact created = getCreated();
        authorize(USER);
         mockMvc.perform(post(REST_URL + "/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAll() throws Exception {
        authorize(USER);
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}