package com.repository.contact;

import com.model.Contact;
import com.model.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ContactRepositoryImpl implements ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Contact save(Contact contact, int userId) {
        if (!contact.isNew() && get(contact.getId(), userId) == null) {
            return null;
        }
        contact.setUser(entityManager.getReference(User.class, userId));
        if (contact.isNew()) {
            entityManager.persist(contact);
            return contact;
        } else {
            return entityManager.merge(contact);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return entityManager.createNamedQuery(Contact.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Contact get(int id, int userId) {
        List<Contact> userMeals = entityManager.createNamedQuery(Contact.GET, Contact.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(userMeals);
    }

    @Override
    public List<Contact> getAll(int userId) {
        return entityManager.createNamedQuery(Contact.ALL_SORTED, Contact.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Contact> getFiltered(String fName, String lName, String mPhone, int userId) {
        return entityManager.createNamedQuery(Contact.GET_FILTERED, Contact.class)
                .setParameter("userId",userId)
                .setParameter("fName",fName)
                .setParameter("lName",lName)
                .setParameter("mPhone",mPhone)
                .getResultList();
    }

    @Override
    public Contact getWithUser(Integer id, Integer userId) {
        return null;
    }
}
