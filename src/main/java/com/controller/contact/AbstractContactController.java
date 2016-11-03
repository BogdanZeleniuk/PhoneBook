package com.controller.contact;

import com.AuthorizedUser;
import com.dto.ContactDTO;
import com.dto.ContactUtil;
import com.model.Contact;
import com.service.contact.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractContactController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractContactController.class);

    @Autowired
    private ContactService service;

    public Contact get(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("get contact {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete contact {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<ContactDTO> getAll() {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        return ContactUtil.getContactDTOFromContact(service.getAll(userId));
    }

    public void update(Contact contact, int id) {
        contact.setId(id);
        int userId = AuthorizedUser.id();
        LOG.info("update {} for User {}", contact, userId);
        service.update(contact, userId);
    }


    public Contact create(Contact contact) {
        contact.setId(null);
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", contact, userId);
        return service.save(contact, userId);
    }

    public List<ContactDTO> getFiltered(String fName, String lName, String mPhone) {
        int userId = AuthorizedUser.id();
        LOG.info("getFiltered parameters {} - {} - {} for User {}", fName, lName, mPhone, userId);
        return ContactUtil.getContactDTOFromContact(service.getFiltered(fName != null ? fName : "",
                                   lName != null ? lName : "",
                                   mPhone != null ? mPhone : "", userId));
    }

}
