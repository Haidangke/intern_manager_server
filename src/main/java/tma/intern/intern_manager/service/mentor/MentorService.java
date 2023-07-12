package tma.intern.intern_manager.service.mentor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import tma.intern.intern_manager.dto.mentor.IMentorQuantity;
import tma.intern.intern_manager.dto.mentor.MentorRequestDto;
import tma.intern.intern_manager.dto.mentor.MentorDetailDto;

import java.util.UUID;

public interface MentorService {
    Page<IMentorQuantity> getListMentor(PageRequest pr);
    MentorDetailDto getMentorById(UUID id);
    MentorDetailDto addMentor(MentorRequestDto request);

    MentorDetailDto updateMentor(UUID id, MentorRequestDto request);
    String deleteMentor(UUID id);
}
