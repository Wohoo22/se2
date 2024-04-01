package gr6.se2.model.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name="course")
public class Course {
    @Id
    @Column(name="cid")
    private Integer cid;
    @Column(name="enrollKey")
    private String enrollKey;
}
