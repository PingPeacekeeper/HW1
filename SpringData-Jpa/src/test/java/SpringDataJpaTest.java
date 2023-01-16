import config.SpringDataJpaCfg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.entity.Student;
import repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataJpaCfg.class)
public class SpringDataJpaTest {
    @Autowired
    StudentRepository repository;

    @Test
    public void test() {
        Optional<Student> byId = repository.findById(1);
        System.out.println(byId.get());

//        repository.findAll();
//        repository.count();
//        repository.save();
//        repository.deleteById();
//        repository.delete();

    }

    @Test
    public void testJpqlR(){
        Student tom = repository.findStudentBySName("Tom");
        System.out.println(tom);
    }

    @Test
    public void testJpqlU(){
        int affected=repository.updateDisplayMsg("new hidden msg",3);
        System.out.println(affected);
    }


    @Test
    public void testMethodName(){
        System.out.println(repository.existsBySName("zard"));
    }
}
