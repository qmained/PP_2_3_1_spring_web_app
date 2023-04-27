package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


//    public UserDaoImp(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
        flushAndClear();
    }

    @Override
    public void removeById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        flushAndClear();
    }

    @Override
    public void updateById(long id, User user) {
        entityManager.detach(user);
        entityManager.merge(user);
        flushAndClear();
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getListUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }



}
