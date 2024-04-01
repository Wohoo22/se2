package gr6.se2.model.enrollment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Table(name = "enrollment")
@Entity()
public class Enrollment {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "sid")
    private int sid;

    @Column(name = "cid")
    private int cid;

    public Enrollment(int sid, int cid) {
        this.id = sid + "_" + cid;
        this.sid = sid;
        this.cid = cid;
    }
}
