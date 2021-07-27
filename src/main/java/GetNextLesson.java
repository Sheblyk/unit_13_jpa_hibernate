import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.unit13.entity.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;

public class GetNextLesson {
    public void get(){
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            EntityManager entityManager = sessionFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                FillDB db = new FillDB(entityManager);
                db.fill();
                Query query = entityManager.createQuery("Select lesson From Lesson lesson join Group group_ ON lesson.group.group_id = group_.group_id join Student student on student.group.group_id = group_.group_id where student.student_id = 1L order by lesson.date ");
                query.setMaxResults(1);
                entityManager.getTransaction().commit();

                Lesson l = (Lesson) query.getSingleResult();
                LocalDateTime dateTime = l.getDate();

                System.out.println("\nDate: " + dateTime.getYear() + " " +
                        dateTime.getMonth() + " " + dateTime.getDayOfMonth() +
                        "\nTime: " + dateTime.getHour() + ":" + dateTime.getMinute()
                        + "\nLesson: " + l.getName() + "\nTopic: " + l.getTopic().getName() + "\nLecturer: "
                        + l.getLecturer().getSurname() + " " + l.getLecturer().getName());

            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                System.out.println(e.getMessage());
            }
        }
    }
}
