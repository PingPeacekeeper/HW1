import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pojo.entity.Student;




public class JpaTest {
    EntityManagerFactory factory;

    @Before
    public void Before() {
        factory = Persistence.createEntityManagerFactory("hibernateJPA");
    }

    @Test
    public void test() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // entityManager = session
        Student s = new Student("Bob");
        em.persist(s);

        // find(pk)
        // getReference(pk) lazy
        // merge(obj) update(with pk, select then update)/insert(without pk)
        // remove(obj) only persist obj from DB Delete

        tx.commit();
    }

    @Test
    public void Jpqj() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // use entity name
        String jpql = "Update Student set hidden=:str where SID=:id";
        em.createQuery(jpql)
                .setParameter("str", "new h text")
                .setParameter("id", 3)
                .executeUpdate();

        tx.commit();
    }

    @Test
    public void sql() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // use table column name
        String sql = "Update Student set Hidden=:str where SID=:id";
        em.createNativeQuery(sql)
                .setParameter("str", "native sql")
                .setParameter("id", 5)
                .executeUpdate();

        tx.commit();
    }

    @Test
    public void status() {
        EntityManager em = factory.createEntityManager();

        Student student = new Student("Charlie"); // New
        student.setSID(7); // Detached
        student = em.find(Student.class, 5); // Managed 持久态，commit会同步
        em.remove(student); // Remove


    }

    @After
    public void end() {
        factory.close();
    }
}
