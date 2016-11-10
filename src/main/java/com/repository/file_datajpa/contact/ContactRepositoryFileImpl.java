package com.repository.file_datajpa.contact;

import com.Profiles;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.model.Contact;
import com.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Profile({Profiles.MOCK, Profiles.FILE})
public class ContactRepositoryFileImpl implements ContactRepository{

    @Autowired
    private File file;

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    private static Map<Integer, Map<Integer, Contact>> mapForConverting = new ConcurrentHashMap<>();

    public static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Contact save(Contact contact, int userId) {
        Integer contactId = contact.getId();

        if (contact.isNew()) {
            contactId = counter.incrementAndGet();
            contact.setId(contactId);
        } else if (get(contactId, userId) == null) {
            return null;
        }
        Map<Integer, Contact> contactMap = mapForConverting.computeIfAbsent(userId, ConcurrentHashMap::new);
        contactMap.put(contactId, contact);
        try {
            writer.writeValue(file, mapForConverting);
        } catch (IOException e) {
            System.out.println(e.getMessage() + " in save()");
        }
        return contact;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Contact> contactMap = mapForConverting.get(userId);
        return contactMap != null && contactMap.remove(id) != null;
    }

    @Override
    public Contact get(int id, int userId) {
        Map<Integer, Contact> contactMap = mapForConverting.get(userId);
        return contactMap == null ? null : contactMap.get(id);
    }

    @Override
    public List<Contact> getAll(int userId) {
        Map<Integer, Contact> contactMap;
        try {
            contactMap = mapper.readValue(file, (TypeReference) mapForConverting.get(userId));
        } catch (IOException e) {
            return null;
        }
        return contactMap == null ?
                Collections.emptyList() :
                contactMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Contact> getFiltered(String fName, String lName, String mPhone, int userId) {
        return null;
    }

}
