package tma.intern.intern_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tma.intern.intern_manager.dto.mentor.IMentorQuantity;
import tma.intern.intern_manager.dto.mentor.MentorRequestDto;
import tma.intern.intern_manager.dto.mentor.MentorDetailDto;
import tma.intern.intern_manager.service.mentor.MentorService;

import java.util.UUID;


@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(value = "/api/v1/mentors")
public class MentorController {
    @Autowired
    private MentorService mentorService;

    @GetMapping
    public ResponseEntity<Page<IMentorQuantity>> getList(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "updatedAt") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction
    ) {
        Sort.Direction sortingDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pr = PageRequest.of(page, size, Sort.by(sortingDirection, sort));
        Page<IMentorQuantity> getResponse = mentorService.getListMentor(pr);

        return ResponseEntity.ok(getResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MentorDetailDto> getById(@PathVariable(name = "id")UUID id) {
        MentorDetailDto getResponse = mentorService.getMentorById(id);
        return ResponseEntity.ok(getResponse);
    }

    @PostMapping
    public ResponseEntity<MentorDetailDto> add(@RequestBody MentorRequestDto request) {

        MentorDetailDto postResponse = mentorService.addMentor(request);
        return ResponseEntity.ok(postResponse);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MentorDetailDto> update(
            @PathVariable(name = "id") UUID id,
            @RequestBody MentorRequestDto request
    ) {
       MentorDetailDto putResponse = mentorService.updateMentor(id, request);
       return ResponseEntity.ok(putResponse);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") UUID id) {
        String deleteResponse = mentorService.deleteMentor(id);
        return ResponseEntity.ok(deleteResponse);
    }



}
