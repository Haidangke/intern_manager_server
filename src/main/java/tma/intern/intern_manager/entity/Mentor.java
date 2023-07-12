package tma.intern.intern_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "mentor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    String name;
    String email;
    String gender;
    String phone;
    String address;
    Date birthday;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "mentor")
    List<Intern> interns;

    @OneToMany(mappedBy = "mentor")
    List<Team> teams;

    @OneToOne(mappedBy = "mentor")
    private Account account;
}
