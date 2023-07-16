package tma.intern.intern_manager.service.project;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.project.ProjectDetailDto;
import tma.intern.intern_manager.dto.project.ProjectRequestDto;
import tma.intern.intern_manager.entity.Project;
import tma.intern.intern_manager.exception.ExistingException;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.ProjectRepository;

import java.util.Optional;
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
        return listProject.map(entity -> {
            ProjectDetailDto project = mapper.map(entity, ProjectDetailDto.class);
            project.setTotalIntern(entity.getProjectInterns().size());
            return project;
        });
    }

    @Override
    public ProjectDetailDto getProject(UUID id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project is not found"));
        ProjectDetailDto response = mapper.map(project, ProjectDetailDto.class);
        response.setTotalIntern(project.getProjectInterns().size());
        return response;
    }

    public ProjectDetailDto addProject(ProjectRequestDto request) {
        Optional<Project> existingProject = projectRepository.findByName(request.getName());
        if(existingProject.isPresent()) {
            throw new ExistingException("This project already exists");
        }
        Project create = mapper.map(request, Project.class);
        Project newProject = projectRepository.save(create);

        return mapper.map(newProject, ProjectDetailDto.class);
    }

    @Override
    public ProjectDetailDto updateProject(UUID id, ProjectRequestDto request) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project is not found"));

        existingProject.setName(request.getName());
        projectRepository.save(existingProject);
        return mapper.map(existingProject, ProjectDetailDto.class);
    }

    @Override
    public String deleteProject(UUID id) {
        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new NotFoundException("Project is not found"));
        projectRepository.deleteById(existingProject.getId());
        return "Delete project successfully";
    }
}
