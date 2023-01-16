package repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pojo.entity.Student;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("From Student where SName=?1")
    Student findStudentBySName(String name);

    @Modifying
    @Transactional  // transaction in logic level
    @Query("update Student c set c.display=:msg where c.SID=:id")
    int updateDisplayMsg(@Param("msg") String msg, @Param("id") int id);

    boolean existsBySName(String name);
}
