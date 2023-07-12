package tma.intern.intern_manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tma.intern.intern_manager.dto.mentor.IMentorQuantity;
import tma.intern.intern_manager.entity.Mentor;

import java.util.UUID;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, UUID> {
    @Query("SELECT m.id as id, m.address as address, m.birthday as birthday, m.email as email, m.name as name, m.phone as phone, m.gender as gender, " +
            "COUNT(DISTINCT i) as totalIntern, COUNT(DISTINCT t) as totalTeam " +
            "FROM Mentor m LEFT JOIN m.interns i LEFT JOIN m.teams t GROUP BY m")
    Page<IMentorQuantity> getMentorsWithTotalInternAndTotalTeam(Pageable pageable);
}
