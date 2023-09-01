package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import peaksoft.enums.StudyFormat;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_gen")
    @SequenceGenerator(name = "student_gen",
            sequenceName = "student_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Email @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private Boolean block;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Group group;
}