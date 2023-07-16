package tma.intern.intern_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tma.intern.intern_manager.dto.project.ProjectDetailDto;
import tma.intern.intern_manager.dto.project.ProjectRequestDto;
import tma.intern.intern_manager.service.project.ProjectService;

import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {
    @Autowired
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectDetailDto> getOne(
            @PathVariable(name = "id") UUID id) {
        ProjectDetailDto response = projectService.getProject(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    ResponseEntity<Page<ProjectDetailDto>> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size);
        Page<ProjectDetailDto> response = projectService.getListProject(pr);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<ProjectDetailDto> add(@RequestBody ProjectRequestDto request) {
        ProjectDetailDto response = projectService.addProject(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProjectDetailDto> update( @PathVariable(name = "id") UUID id,@RequestBody ProjectRequestDto request) {
        ProjectDetailDto response = projectService.updateProject(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable(name = "id")UUID id) {
        String response = projectService.deleteProject(id);
        return ResponseEntity.ok(response);
    }
}
