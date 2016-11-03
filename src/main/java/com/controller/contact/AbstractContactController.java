package com.controller.contact;

import com.AuthorizedUser;
import com.dto.ContactDTO;
import com.dto.ContactUtil;
import com.model.Contact;
import com.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractContactController {

    @Autowired
    private ContactService service;

    public Contact get(int id) {
        int userId = AuthorizedUser.id();
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
    }

    public List<ContactDTO> getAll() {
        int userId = AuthorizedUser.id();
        return ContactUtil.getContactDTOFromContact(service.getAll(userId));
    }

    public void update(Contact contact, int id) {
        contact.setId(id);
        int userId = AuthorizedUser.id();
        service.update(contact, userId);
    }


    public Contact create(Contact contact) {
        contact.setId(null);
        int userId = AuthorizedUser.id();
        return service.save(contact, userId);
    }

    public List<ContactDTO> getFiltered(String fName, String lName, String mPhone) {
        int userId = AuthorizedUser.id();
        return ContactUtil.getContactDTOFromContact(service.getFiltered(fName != null ? fName : "",
                                   lName != null ? lName : "",
                                   mPhone != null ? mPhone : "", userId));
    }

}
