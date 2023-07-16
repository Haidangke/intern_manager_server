package tma.intern.intern_manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tma.intern.intern_manager.entity.Intern;

import java.util.UUID;

@Repository
public interface InternRepository extends JpaRepository<Intern, UUID> {
    Page<Intern> findAllByMentorId(UUID id, PageRequest pr);

    Page<Intern> findAllByTeamId(UUID id, PageRequest pr);

    @Query(value = "select i FROM Intern i WHERE i.id NOT IN (SELECT pi.intern.id FROM ProjectIntern pi WHERE pi.project.id = :id)")
    Page<Intern> findInternsNotInProject(UUID id, PageRequest pr);
}