package tma.intern.intern_manager.service.project_intern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.api.ApiResponse;
import tma.intern.intern_manager.dto.intern.InternDetailDto;
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

import java.util.ArrayList;
import java.util.List;
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

    public Page<ProjectInternDetailDto> getListProjectIntern(UUID projectId, PageRequest pr) {
        Project project = projectRepository
                .findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project is not found"));
        Page<ProjectIntern> projectInterns = projectInternRepository.findProjectInternByProject(project, pr);

        return projectInterns.map(projectIntern ->
                mapper.map(projectIntern, ProjectInternDetailDto.class)
        );
    }

    @Override
    public List<ProjectInternDetailDto> addProjectIntern(ProjectInternRequestDto request) {
        List<ProjectIntern> createList = new ArrayList<>();
        for(UUID internId: request.getInterns()) {
            Intern intern = internRepository.findById(internId).orElseThrow(() -> new NotFoundException("Intern is not found"));
            Project project = projectRepository.findById(request.getProject()).orElseThrow(() -> new NotFoundException("Project is not found"));

            ProjectIntern existingProjectIntern = projectInternRepository.findProjectInternByIntern(intern).orElse(null);
            if(existingProjectIntern != null) {
                if(existingProjectIntern.getProject().getId() == request.getProject()) {
                    throw new ExistingException("This intern is existing in project");
                }
            }

            ProjectIntern create = mapper.map(request, ProjectIntern.class);
            create.setIntern(intern);
            create.setProject(project);

            createList.add(create);
        }


        List<ProjectIntern> newProjectInternList = projectInternRepository.saveAll(createList);
        List<ProjectInternDetailDto> response = new ArrayList<>();
        for(ProjectIntern projectIntern: newProjectInternList) {
            response.add(mapper.map(projectIntern, ProjectInternDetailDto.class));
        }

        return response;
    }

    @Override
    public ApiResponse deleteProjectIntern(UUID id) {
        ProjectIntern existingProjectIntern = projectInternRepository.findById(id).orElseThrow(() -> new NotFoundException("Intern in project is not found"));
        projectInternRepository.deleteById(existingProjectIntern.getId());
        return new ApiResponse("Delete intern with project successfully");
    }


}
