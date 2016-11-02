package com.repository.user;

import com.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepository{

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            entityManager.persist(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return entityManager.createNamedQuery(User.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id,
                Collections.singletonMap("javax.persistence.fetchgraph", entityManager.getEntityGraph(User.GRAPH_WITH_ROLES)));
    }

    @Override
    public User getByLogin(String login) {
        User result = null;
        try{
            result = entityManager.createNamedQuery(User.BY_LOGIN, User.class).setParameter("login", login).getSingleResult();
        }
        catch (NoResultException ignored){
            LOG.debug("There is no such user");
        }
        catch (EmptyResultDataAccessException e){
            LOG.debug("Empty user");
        }
        return result;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }
}
