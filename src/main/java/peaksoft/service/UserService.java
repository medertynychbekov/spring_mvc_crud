package peaksoft.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.User;

import java.util.List;

@Service
@Transactional
public class UserService implements ServiceLayer<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User update(int id, User user) {
        User oldUser = entityManager.find(User.class, id);

        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());

        entityManager.merge(oldUser);
        return oldUser;
    }

    @Override
    public void deleteById(int id) {
        entityManager.createQuery("delete from User u where u.id =?").setParameter("id", id);
        entityManager.remove(entityManager.find(User.class, id));
    }
}
