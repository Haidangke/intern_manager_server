package tma.intern.intern_manager.service.project_intern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.project_intern.ProjectInternDetailDto;
import tma.intern.intern_manager.dto.project_intern.ProjectInternRequestDto;
import tma.intern.intern_manager.entity.Intern;
import tma.intern.intern_manager.entity.Project;
import tma.intern.intern_manager.entity.ProjectIntern;
import tma.intern.intern_manager.exception.ExistingException;
import tma.intern.intern_manager.exception.InvalidDataException;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.InternRepository;
import tma.intern.intern_manager.repository.ProjectInternRepository;
import tma.intern.intern_manager.repository.ProjectRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectInternServiceImpl implements  ProjectInternService{
    @Autowired
    private final ProjectInternRepository projectInternRepository;
    @Autowired
    private final InternRepository internRepository;
    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final ModelMapper mapper;

    public ProjectInternServiceImpl(ProjectInternRepository projectInternRepository, InternRepository internRepository, ProjectRepository projectRepository, ModelMapper mapper) {
        this.projectInternRepository = projectInternRepository;
        this.internRepository = internRepository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public ProjectInternDetailDto addProjectIntern(ProjectInternRequestDto request) {
        Intern intern = internRepository.findById(request.getIntern()).orElseThrow(() -> new NotFoundException("Intern is not found"));
        Project project = projectRepository.findById(request.getProject()).orElseThrow(() -> new NotFoundException("Project is not found"));;

        ProjectIntern existingProjectIntern = projectInternRepository.findProjectInternByIntern(intern);
        if(existingProjectIntern.getProject().getId() == project.getId()) {
            throw new ExistingException("This intern is existing in project");
        }

        ProjectIntern create = mapper.map(request, ProjectIntern.class);
        create.setIntern(intern);
        create.setProject(project);

        ProjectIntern newProjectIntern = projectInternRepository.save(create);
        ProjectInternDetailDto response = mapper.map(newProjectIntern, ProjectInternDetailDto.class);
        return response;
    }

    @Override
    public String deleteProjectIntern(UUID id) {
        ProjectIntern existingProjectIntern = projectInternRepository.findById(id).orElseThrow(() -> new NotFoundException("Intern in project is not found"));
        projectInternRepository.deleteById(existingProjectIntern.getId());
        return "Delete intern with project successfully";
    }
}
