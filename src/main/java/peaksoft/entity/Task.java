package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_gen")
    @SequenceGenerator(name = "task_gen",
            sequenceName = "task_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_text")
    private String taskText;
    @Column(name = "dead_line")
    private LocalDate deadLine;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Lesson lesson;

}