package com.service.contact;

import com.Profiles;
import com.controller.exception.ExceptionUtil;
import com.model.Contact;
import com.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({Profiles.ACTIVE_DB, Profiles.DB_IMPLEMENTATION})
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository repository;

    @Override
    public Contact get(int id, int userId) {

        return ExceptionUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId), id);
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

        return ExceptionUtil.checkNotFoundWithId(repository.save(contact, userId), contact.getId());
    }

    @Override
    public Contact save(Contact contact, int userId) {
        return repository.save(contact, userId);
    }
}
