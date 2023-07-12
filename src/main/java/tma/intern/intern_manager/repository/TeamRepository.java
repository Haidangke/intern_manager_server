package tma.intern.intern_manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tma.intern.intern_manager.entity.Mentor;
import tma.intern.intern_manager.entity.Team;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    Page<Mentor> findAllByMentorId(UUID id, PageRequest pr);
}
