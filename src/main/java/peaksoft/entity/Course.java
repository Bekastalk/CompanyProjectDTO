package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_gen",
            sequenceName = "course_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "date_of_start")
    private LocalDate dateOfStart;
    private String description;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Company company;
    @ManyToOne(cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH})
    private Instructor instructor;
    @OneToMany(mappedBy = "courses",cascade = CascadeType.ALL)
    private List<Lesson> lessons;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Group> groups;

    public void addGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        } else {
            groups.add(group);
        }
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        } else {
            lessons.add(lesson);
        }
    }
}