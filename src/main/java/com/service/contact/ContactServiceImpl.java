package com.service.contact;

import com.model.Contact;
import com.repository.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository repository;

    @Override
    public Contact get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    @Override
    public List<Contact> getFiltered(String fName, String lName, String mPhone, int userId) {
        return repository.getFiltered(fName, lName, mPhone, userId);
    }

    @Override
    public List<Contact> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Contact update(Contact contact, int userId) {
        return repository.save(contact, userId);
    }

    @Override
    public Contact save(Contact contact, int userId) {
        return repository.save(contact, userId);
    }
}
