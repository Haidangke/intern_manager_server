package tma.intern.intern_manager.service.project;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.project.ProjectDetailDto;
import tma.intern.intern_manager.dto.project.ProjectRequestDto;
import tma.intern.intern_manager.entity.Project;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.ProjectRepository;

import java.util.UUID;

@Service
public class ProjectServiceImpl implements  ProjectService {
    @Autowired
    private final ModelMapper mapper;
    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ModelMapper mapper, ProjectRepository projectRepository) {
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }

    public Page<ProjectDetailDto> getListProject(PageRequest pr) {
        Page<Project> listProject = projectRepository.findAll(pr);
        Page<ProjectDetailDto> response = listProject.map(entity -> mapper.map(entity, ProjectDetailDto.class));
        return response;
    }

    public ProjectDetailDto addProject(ProjectRequestDto request) {
        Project create = mapper.map(request, Project.class);
        Project newProject = projectRepository.save(create);

        return mapper.map(newProject, ProjectDetailDto.class);
    }

    @Override
    public ProjectDetailDto updateProject(UUID id, ProjectRequestDto request) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project is not found"));

        existingProject.setName(request.getName());
        projectRepository.save(existingProject);
        ProjectDetailDto response = mapper.map(existingProject, ProjectDetailDto.class);
        return response;
    }

    @Override
    public String deleteProject(UUID id) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project is not found"));
        projectRepository.deleteById(existingProject.getId());
        return "Delete project successfully";
    }
}
