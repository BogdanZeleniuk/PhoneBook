package com.controller.contact;

import com.ContactTestData;
import com.dto.JsonUtil;
import com.model.Contact;
import org.junit.Test;

import java.util.List;

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(ContactTestData.CONTACT1);
        System.out.println(json);
        Contact contact = JsonUtil.readValue(json, Contact.class);
        ContactTestData.MATCHER.assertEquals(ContactTestData.CONTACT1, contact);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(ContactTestData.CONTACTS);
        System.out.println(json);
        List<Contact> contacts = JsonUtil.readValues(json, Contact.class);
        ContactTestData.MATCHER.assertCollectionEquals(ContactTestData.CONTACTS, contacts);
    }
}