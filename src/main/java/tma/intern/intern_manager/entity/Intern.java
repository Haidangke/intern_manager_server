package tma.intern.intern_manager.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "intern")
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intern  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private UUID id;
    String name;
    String email;
    String phone;
    String gender;
    String address;
    Date birthday;
    String description;
    String technology;
    String status;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "intern", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProjectIntern> projectInterns;

    @OneToOne(mappedBy = "intern")
    private Account account;

}
