package com.service.contact;


import com.model.Contact;

import java.util.List;

public interface ContactService {

    Contact get(int id, int userId);

    void delete(int id, int userId);

    List<Contact> getFiltered(String fName, String lName, String mPhone, int userId);

    List<Contact> getAll(int userId);

    Contact update(Contact contact, int userId);

    Contact save(Contact contact, int userId);
}
