package com.repository.file_datajpa.user;

import com.Profiles;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
@Profile({Profiles.MOCK, Profiles.FILE})
public class UserRepositoryFileImpl implements UserRepository{

    @Autowired
    private File file;

    public static Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        try {
            writer.writeValue(file, repository);
        } catch (IOException e) {
            System.out.println(e.getMessage() + " in save() User");
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        User currentUser = repository.get(id);
        return currentUser == null ? null : currentUser;
    }

    @Override
    public User getByLogin(String login) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream()
                .collect(Collectors.toList());
    }
}
