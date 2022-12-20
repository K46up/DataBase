package root.database.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import root.database.util.HibernateSessionFactoryUtil;

import java.util.Collections;
import java.util.List;

public class DaoManager<T> {

    final Class<T> typeParameterClass;

    private Session session;

    public DaoManager(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public T findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(typeParameterClass, id);
    }

    public void save(T t) {
        Transaction transaction = session.beginTransaction();
        session.persist(t);
        transaction.commit();
    }

    public void update(T t) {
        Transaction transaction = session.beginTransaction();
        session.merge(t);
        transaction.commit();
    }

    public void delete(T t) {
        Transaction transaction = session.beginTransaction();
        session.remove(t);
        transaction.commit();
    }

    public List<T> findAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(typeParameterClass);
        criteria.from(typeParameterClass);
        List<T> result = session.createQuery(criteria).getResultList();
        return result;
    }
}