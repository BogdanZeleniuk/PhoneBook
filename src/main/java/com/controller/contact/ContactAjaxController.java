package com.controller.contact;

import com.model.Contact;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ajax/contacts")
public class ContactAjaxController extends AbstractContactController{

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contact> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}")
    public Contact get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateOrCreate(@Valid Contact contact) {
        if (contact.isNew()) {
            super.create(contact);
        } else {
            super.update(contact, contact.getId());
        }
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Contact> getFiltered(
            @RequestParam(value = "fName", required = false) String fName,
            @RequestParam(value = "lName", required = false) String lName,
            @RequestParam(value = "mPhone", required = false) String mPhone) {
        return super.getFiltered(fName, lName, mPhone);
    }
}
