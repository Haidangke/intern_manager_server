package tma.intern.intern_manager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tma.intern.intern_manager.entity.Intern;
import tma.intern.intern_manager.entity.Project;
import tma.intern.intern_manager.entity.ProjectIntern;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectInternRepository extends JpaRepository<ProjectIntern, UUID> {
    Optional<ProjectIntern> findProjectInternByIntern(Intern intern);

    Page<ProjectIntern> findProjectInternByProject(Project project, PageRequest pr);
}
