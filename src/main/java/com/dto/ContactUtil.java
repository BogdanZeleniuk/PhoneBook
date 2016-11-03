package com.dto;

import com.model.Contact;

import java.util.List;
import java.util.stream.Collectors;

public class ContactUtil {

    public static List<ContactDTO> getContactDTOFromContact (List<Contact> contacts) {
        return contacts.stream().map(ContactUtil::createContactDTO).collect(Collectors.toList());
    }

    public static ContactDTO createContactDTO(Contact contact) {
        return new ContactDTO(contact.getId(), contact.getFirstName(),
                contact.getLastName(), contact.getPatronymic(),
                contact.getMobilePhone(), contact.getHomePhone(), contact.getAddress(), contact.getEmail());
    }
}
