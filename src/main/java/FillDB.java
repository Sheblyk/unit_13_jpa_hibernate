import ua.com.unit13.entity.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

public class FillDB {
    private final EntityManager e;

    FillDB(EntityManager entityManager) {
        e = entityManager;
    }

    public void fill() {
        Topic topic = new Topic("bubble sort");
        e.persist(topic);
        Topic topic1 = new Topic("dimensionality reduction methods");
        e.persist(topic1);

        Lecturer lecturer = new Lecturer("Ivan", "Gontar");
        e.persist(lecturer);
        Lecturer lecturer1 = new Lecturer("Inna", "Sokolova");
        e.persist(lecturer1);

        Course course = new Course("first");
        e.persist(course);
        Course course1 = new Course("third");
        e.persist(course1);

        Group group = new Group("ITKN-16-3");
        Group group1 = new Group("ITKN-19-2");
        group.setCourse(course);
        group1.setCourse(course1);

        Student student = new Student("Daria", " Sheblykina");
        Student student1 = new Student("Ivan", "Gluchi");
        student.setGroup(group);
        student1.setGroup(group1);
        e.persist(group);
        e.persist(group1);
        e.persist(student);
        e.persist(student1);

        Lesson lesson = new Lesson(LocalDateTime.of(2021, 8, 11, 13, 10, 0), "algoritm");
        Lesson lesson1 = new Lesson(LocalDateTime.of(2021, 8, 9, 11, 15, 0), "multicriteria methods");
        Lesson lesson2 = new Lesson(LocalDateTime.of(2021, 8, 17, 13, 10, 0), "algoritm");
        lesson.setLecturer(lecturer);
        lesson.setTopic(topic);
        lesson.setGroup(group);

        lesson1.setLecturer(lecturer1);
        lesson1.setTopic(topic1);
        lesson1.setGroup(group);

        lesson2.setLecturer(lecturer);
        lesson2.setTopic(topic);
        lesson2.setGroup(group1);

        e.persist(lesson);
        e.persist(lesson1);
        e.persist(lesson2);

        Grade grade = new Grade();
        grade.setLesson(lesson);
        grade.setStudent(student);
        grade.setMark((byte) 5);
        e.persist(grade);
    }
}


