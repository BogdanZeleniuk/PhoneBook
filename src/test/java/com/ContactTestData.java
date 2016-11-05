package com;

import com.matcher.ModelMatcher;
import com.model.Contact;
import com.model.Role;
import com.model.User;

import java.util.Arrays;
import java.util.List;

public class ContactTestData {

    public static final ModelMatcher<Contact> MATCHER = new ModelMatcher<>(Contact.class);
    public static final int USER_ID = 1;
    public static final int FAKE_USER_ID = 2;
    public static final String PART_OF_WORD_FOR_TESTING = "99";

    public static final Contact CONTACT1 = new Contact(1, "Ivan", "Ivanov","Ivanovych","+380(66)1234567","","USA","bill@gmail.com");
    public static final Contact CONTACT2 = new Contact(2, "Petro", "Petrov","Petrovych","+380(66)9876543","+380(44)1122334","USA","mark@gmail.com");
    public static final Contact CONTACT3 = new Contact(3, "Sydor", "Sydorov","Sydorovych","+380(99)1234567","","USA","barak@gmail.com");
    public static final Contact CONTACT4 = new Contact(4, "Mykola", "Mykolaiov","Mykolaiovych","+380(99)9876543","","USA","michael@gmail.com");

    public static final User USER = new User(USER_ID, "Bill", "112233", "user", Role.ROLE_USER);

    public static final List<Contact> CONTACTS = Arrays.asList(CONTACT1, CONTACT2, CONTACT3, CONTACT4);

    public static Contact getCreated() {
        return new Contact(null, "Sergey","Sergeev","Sergeevych","+380(99)2244888","","USA","tim@gmail.com");
    }

    public static Contact getUpdated() {
        return new Contact(1, CONTACT1.getFirstName(), "NEW LAST_NAME", CONTACT1.getPatronymic(), CONTACT1.getMobilePhone(), "NEW HOME_PHONE", CONTACT1.getAddress(), "bill@microsoft.com");
    }
}
