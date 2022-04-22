import com.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;
import java.util.SortedMap;

public class CreateStudents {
    public static void main(String[] args) {

        //Create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //Create session
        Session session = factory.getCurrentSession();

        try {
            //create the object
            Student tempStudent = new Student("Paul", "Wall", "paul.wall@gmail.com");
            //  List<String> theImages = tempStudent.getImages();
            //  theImages.add("photo1.jpg");
            //  theImages.add("photo2.jpg");
            //  theImages.add("photo3.jpg");

            SortedMap<String,String> theImages = tempStudent.getImages();
            theImages.put("photo1.jpg", "photo 1");
            theImages.put("photo2.jpg", "photo 2");
            theImages.put("photo3.jpg", "photo 3");

//            Set<String> theImages = tempStudent.getImages();
//            theImages.add("Image1.png");
//            theImages.add("Image2.png");
//            theImages.add("Image3.png");
//            theImages.add("Image4.png");
//            theImages.add("Image5.png");


            //start a transaction
            session.beginTransaction();

            //save
            System.out.println("Saving the student and images");
            session.persist(tempStudent);

            //commit the transaction
            session.getTransaction().commit();
        } finally {
            //clean up code
            session.close();
            factory.close();
        }
    }
}
