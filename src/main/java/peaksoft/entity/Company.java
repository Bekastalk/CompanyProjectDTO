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
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_gen")
    @SequenceGenerator(
            name = "company_gen",
            sequenceName = "company_seq",
            allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String name;
    private String country;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private LocalDate createdDate;
    private LocalDate graduationDate;
    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Course> courses;

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        } else {
            courses.add(course);
        }
    }
}