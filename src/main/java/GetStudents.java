import com.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;

public class GetStudents {
    public static void main(String[] args) {

        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //Create session
        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();
            //get the student id
            int theId = 18;
            Student student = session.get(Student.class,theId);
            //print the student details
            System.out.println("Student details: " + student);
            //print the associated images
            System.out.println("The associated iamges: " + student.getImages());
            //commit the transaction
            session.getTransaction().commit();
            //done
            System.out.println("Done");
        } finally {
            //clean up code
            session.close();
            factory.close();
        }
    }
}
