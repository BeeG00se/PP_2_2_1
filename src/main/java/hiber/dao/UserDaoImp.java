package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final String HQL = "from User u where u.car.series =: series and u.car.model =: model";

    @Override
    public void add(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery("from User");
            return query.getResultList();
        }
    }

    public User userByCar(String model, int series) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(HQL);
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.getResultList().get(0);
        }

    }

}
