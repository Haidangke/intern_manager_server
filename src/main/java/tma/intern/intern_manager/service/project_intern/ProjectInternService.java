package tma.intern.intern_manager.service.project_intern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import tma.intern.intern_manager.dto.api.ApiResponse;
import tma.intern.intern_manager.dto.project_intern.ProjectInternDetailDto;
import tma.intern.intern_manager.dto.project_intern.ProjectInternRequestDto;

import java.util.List;
import java.util.UUID;

public interface ProjectInternService {
    List<ProjectInternDetailDto> addProjectIntern(ProjectInternRequestDto request);

    Page<ProjectInternDetailDto> getListProjectIntern(UUID id, PageRequest pr);

    ApiResponse deleteProjectIntern(UUID id);
}
