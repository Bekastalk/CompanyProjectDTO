package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_gen")
    @SequenceGenerator(name = "group_gen",
            sequenceName = "group_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "image_link")
    private String imageLink;
    private String description;
    @ManyToMany(mappedBy = "groups")
    private List<Course> courses;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        } else {
            courses.add(course);
        }
    }
}