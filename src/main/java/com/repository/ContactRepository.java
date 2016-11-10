package com.repository;


import com.model.Contact;

import java.util.List;

public interface ContactRepository {

    Contact save(Contact contact, int userId);

    boolean delete(int id, int userId);

    Contact get(int id, int userId);

    List<Contact> getAll(int userId);

    List<Contact> getFiltered(String fName, String lName,String mPhone, int userId);

    default Contact getWithUser(Integer id, Integer userId) {
        throw new UnsupportedOperationException();
    }
}
