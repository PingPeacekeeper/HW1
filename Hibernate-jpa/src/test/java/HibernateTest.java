
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.entity.Student;

import java.util.List;


public class HibernateTest {
    private SessionFactory sf;

    @Before
    public void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml").build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void insert() throws HibernateException {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student("Bob");
        session.persist(student);
        transaction.commit();
    }

    @Test
    public void select() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

//        Student student = session.find(Student.class, 1);
        Student student = session.getReference(Student.class, 1);
        System.out.println("=== after query ===");
        System.out.println(student);

        transaction.commit();
    }

    @Test
    public void update() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.find(Student.class, 1);
//        Student student = session.load(Student.class, 1);
        student.setDisplay("new msg");
        session.persist(student);

        transaction.commit();
    }

    @Test
    public void delete() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.find(Student.class, 4);
        session.remove(student);

        transaction.commit();
    }

    @Test
    public void hql() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Student where display =:msg";
        List<Student> resultList = session.createQuery(hql, Student.class)
                .setParameter("msg", "shown text")
                .getResultList();
        System.out.println(resultList);

        transaction.commit();
    }

    @After
    public void finish() {
        sf.close();
    }

}
