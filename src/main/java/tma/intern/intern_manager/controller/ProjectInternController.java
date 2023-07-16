package tma.intern.intern_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tma.intern.intern_manager.dto.api.ApiResponse;
import tma.intern.intern_manager.dto.intern.InternDetailDto;
import tma.intern.intern_manager.dto.project_intern.ProjectInternDetailDto;
import tma.intern.intern_manager.dto.project_intern.ProjectInternRequestDto;
import tma.intern.intern_manager.service.project_intern.ProjectInternService;

import java.util.List;
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

    @GetMapping("/project/{id}")
    ResponseEntity<Page<ProjectInternDetailDto>> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @PathVariable(value = "id") UUID id) {
        Sort.Direction sortingDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pr = PageRequest.of(page, size, Sort.by(sortingDirection, sort));
        Page<ProjectInternDetailDto> getResponse = projectInternService.getListProjectIntern(id, pr);
        return ResponseEntity.ok(getResponse);
    }

    @PostMapping
    ResponseEntity<List<ProjectInternDetailDto>> add(@RequestBody ProjectInternRequestDto request) {
        List<ProjectInternDetailDto> response = projectInternService.addProjectIntern(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> delete(@PathVariable(name = "id") UUID id)  {
        ApiResponse response = projectInternService.deleteProjectIntern(id);
        return ResponseEntity.ok(response);
    }
}
