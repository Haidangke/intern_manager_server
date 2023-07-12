package tma.intern.intern_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tma.intern.intern_manager.dto.project_intern.ProjectInternDetailDto;
import tma.intern.intern_manager.dto.project_intern.ProjectInternRequestDto;
import tma.intern.intern_manager.service.project_intern.ProjectInternService;

import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/api/v1/project_intern")
public class ProjectInternController {
    @Autowired
    private final ProjectInternService projectInternService;

    public ProjectInternController(ProjectInternService projectInternService) {
        this.projectInternService = projectInternService;
    }

    @PostMapping
    ResponseEntity<ProjectInternDetailDto> add(@RequestBody ProjectInternRequestDto request) {
        ProjectInternDetailDto response = projectInternService.addProjectIntern(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable(name = "id") UUID id)  {
        String response = projectInternService.deleteProjectIntern(id);
        return ResponseEntity.ok(response);
    }
}
