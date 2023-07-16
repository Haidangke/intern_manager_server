package tma.intern.intern_manager.service.intern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import tma.intern.intern_manager.dto.intern.InternRequestDto;
import tma.intern.intern_manager.dto.intern.InternDetailDto;

import java.util.UUID;

public interface InternService {
    Page<InternDetailDto> getListIntern(PageRequest pr);

    InternDetailDto getInternById(UUID id);

    InternDetailDto addIntern(InternRequestDto request);

    Page<InternDetailDto> getListInternByMentor(PageRequest pr, UUID mentorId);

    Page<InternDetailDto> getListInternByTeam(PageRequest pr, UUID teamId);

    Page<InternDetailDto> getListInternNotInProject(UUID projectId, PageRequest pr);

    InternDetailDto updateIntern(UUID id, InternRequestDto request);

    String deleteIntern(UUID id);
}
