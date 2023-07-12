package tma.intern.intern_manager.service.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import tma.intern.intern_manager.dto.project.ProjectDetailDto;
import tma.intern.intern_manager.dto.project.ProjectRequestDto;

import java.util.UUID;

public interface ProjectService {
    Page<ProjectDetailDto> getListProject(PageRequest pr);
    ProjectDetailDto addProject(ProjectRequestDto request);
    ProjectDetailDto updateProject(UUID id, ProjectRequestDto request);
    String deleteProject(UUID id);

}
