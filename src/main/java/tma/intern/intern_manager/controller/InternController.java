package tma.intern.intern_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tma.intern.intern_manager.dto.intern.InternRequestDto;
import tma.intern.intern_manager.dto.intern.InternDetailDto;
import tma.intern.intern_manager.service.intern.InternService;

import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_MENTOR')")
@RequestMapping(value = "/api/v1/interns")
public class InternController {
    @Autowired
    private InternService internService;

    @GetMapping
    ResponseEntity<Page<InternDetailDto>> getList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "updatedAt") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction
    ) {
        Sort.Direction sortingDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest pr = PageRequest.of(page, size, Sort.by(sortingDirection, sort));
        Page<InternDetailDto> getResponse = internService.getListIntern(pr);
        return ResponseEntity.ok(getResponse);
    }

    @GetMapping(value = "/mentor/{id}")
    ResponseEntity<Page<InternDetailDto>> getListByMentor(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "updatedAt") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @PathVariable(name = "id") UUID mentorId
            ) {
        Sort.Direction sortingDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pr = PageRequest.of(page, size, Sort.by(sortingDirection, sort));
        Page<InternDetailDto> getResponse = internService.getListInternByMentor(pr, mentorId);
        return ResponseEntity.ok(getResponse);
    }

    @GetMapping(value = "/project/not/{id}")
    ResponseEntity<Page<InternDetailDto>> getListNotInProject(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @PathVariable(name = "id") UUID projectId
    ) {

        PageRequest pr = PageRequest.of(page, size);
        Page<InternDetailDto> getResponse = internService.getListInternNotInProject(projectId, pr);
        return ResponseEntity.ok(getResponse);
    }

    @GetMapping(value = "/team/{id}")
    ResponseEntity<Page<InternDetailDto>> getListByTeam(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "updatedAt") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction,
            @PathVariable(name = "id") UUID mentorId
    ) {
        Sort.Direction sortingDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pr = PageRequest.of(page, size, Sort.by(sortingDirection, sort));
        Page<InternDetailDto> getResponse = internService.getListInternByTeam(pr, mentorId);
        return ResponseEntity.ok(getResponse);
    }


    @GetMapping(value = "/{id}") ResponseEntity<InternDetailDto> getById(@PathVariable(value = "id") UUID id) {
        InternDetailDto getResponse = internService.getInternById(id);
        return ResponseEntity.ok(getResponse);
    }

    @PostMapping
    ResponseEntity<InternDetailDto> add(@RequestBody InternRequestDto request) {
        InternDetailDto postResponse = internService.addIntern(request);

        return ResponseEntity.ok(postResponse);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<InternDetailDto> update(@PathVariable(value = "id") UUID id, @RequestBody InternRequestDto request) {
        InternDetailDto putResponse = internService.updateIntern(id, request);

        return ResponseEntity.ok(putResponse);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> delete(@PathVariable(value = "id") UUID id) {
        String deleteResponse = internService.deleteIntern(id);
        return ResponseEntity.ok(deleteResponse) ;
    }
}
