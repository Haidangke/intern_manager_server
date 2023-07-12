package tma.intern.intern_manager.service.project_intern;

import tma.intern.intern_manager.dto.project_intern.ProjectInternDetailDto;
import tma.intern.intern_manager.dto.project_intern.ProjectInternRequestDto;

import java.util.UUID;

public interface ProjectInternService {
    ProjectInternDetailDto addProjectIntern(ProjectInternRequestDto request);

    String deleteProjectIntern(UUID id);
}
