package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.exeptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_gen")
    @SequenceGenerator(name = "lesson_gen",
            sequenceName = "lesson_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "lesson_name")
    private String lessonName;
    @OneToMany(mappedBy = "lesson")
    private List<Course> courses;
    @OneToMany(mappedBy = "lesson")
    private List<Task> tasks;

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        } else {
            courses.add(course);
        }
    }

    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        } else {
            tasks.add(task);
        }
    }
}