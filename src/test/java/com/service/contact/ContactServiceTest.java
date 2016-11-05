package com.service.contact;

import com.controller.exception.NotFoundException;
import com.model.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ContactTestData.*;

public class ContactServiceTest extends AbstractServiceTest{

    @Autowired
    ContactService service;

    @Test
    public void testDeleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(CONTACT2.getId(), FAKE_USER_ID);
    }

    @Test
    public void testGet() throws Exception{
        Contact contactForGetTesting = service.get(CONTACT1.getId(), USER_ID);
        Assert.assertEquals(CONTACT1, contactForGetTesting);
    }

    @Test
    public void testSave() throws Exception {
        Contact created = getCreated();
        service.save(created, USER_ID);
        Assert.assertArrayEquals(new Contact[]{CONTACT1, CONTACT2, CONTACT3, CONTACT4,created}, service.getAll(USER_ID).toArray());
    }

    @Test
    public void testGetAllItems() throws Exception{
        //Assert.assertArrayEquals(CONTACTS.toArray(), service.getAll(USER_ID).toArray());
        Assert.assertEquals(CONTACTS.size()+1, service.getAll(USER_ID).size());
    }

    @Test
    public void testGetNotFoundException() throws Exception{
        thrown.expect(NotFoundException.class);
        service.get(CONTACT1.getId(), FAKE_USER_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(CONTACT1.getId(), USER_ID);
        //Assert.assertArrayEquals(new Contact[]{CONTACT2, CONTACT3, CONTACT4}, service.getAll(USER_ID).toArray());
        Assert.assertEquals(4,service.getAll(USER_ID).size());
    }

    @Test
    public void testUpdate() throws Exception {
        Contact updated = new Contact(3, CONTACT3.getFirstName(), "NEW LAST_NAME", CONTACT3.getPatronymic(),
                CONTACT3.getMobilePhone(), "NEW HOME_PHONE", CONTACT3.getAddress(), "barak@microsoft.com");
        service.update(updated, USER_ID);
        Assert.assertEquals(updated, service.get(CONTACT3.getId(), USER_ID));
    }

    @Test
    public void testNotFoundUpdate() throws Exception {
        Contact contact = service.get(CONTACT1.getId(), USER_ID);
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + CONTACT1.getId());
        service.update(contact, FAKE_USER_ID);
    }

    @Test
    public void testGetBetween() throws Exception {
        Assert.assertArrayEquals(new Contact [] {},
                service.getFiltered(null,null, PART_OF_WORD_FOR_TESTING, 2).toArray());
    }
}