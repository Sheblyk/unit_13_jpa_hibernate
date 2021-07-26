package ua.com.unit13.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topic_id;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    private List<Lesson> lessons = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    public Topic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> classes) {
        this.lessons = classes;
    }

    public void addLesson(Lesson lesson_) {
        lessons.add(lesson_);
    }
}
