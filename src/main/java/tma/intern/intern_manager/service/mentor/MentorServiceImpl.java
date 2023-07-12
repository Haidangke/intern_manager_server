package tma.intern.intern_manager.service.mentor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.mentor.IMentorQuantity;
import tma.intern.intern_manager.dto.mentor.MentorRequestDto;
import tma.intern.intern_manager.dto.mentor.MentorDetailDto;
import tma.intern.intern_manager.entity.Account;
import tma.intern.intern_manager.entity.Mentor;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.AccountRepository;
import tma.intern.intern_manager.repository.MentorRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class MentorServiceImpl implements MentorService {
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper mapper;

    public Page<IMentorQuantity> getListMentor(PageRequest pr) {
        Page<IMentorQuantity> response = mentorRepository.getMentorsWithTotalInternAndTotalTeam(pr);
        return response;
    }

    public MentorDetailDto getMentorById(UUID id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() ->  new NotFoundException("Mentor is not found"));
        MentorDetailDto response = mapper.map(mentor, MentorDetailDto.class);
        return response;
    }
    public MentorDetailDto addMentor(MentorRequestDto request) {
        Mentor mentor = mapper.map(request, Mentor.class);

        if(request.getAccount() != null) {
            Optional<Account> account = accountRepository.findById(request.getAccount());
            account.ifPresent(mentor::setAccount);
        }

        mentorRepository.save(mentor);

        MentorDetailDto response = mapper.map(mentor, MentorDetailDto.class);
        return response;
    }

    public MentorDetailDto updateMentor(UUID id, MentorRequestDto request) {
        Mentor existingMentor = mentorRepository.findById(id).orElseThrow(() -> new NotFoundException("Mentor is not found"));
        if(request.getAccount() != null) {
            Optional<Account> account = accountRepository.findById(request.getAccount());
            account.ifPresent(existingMentor::setAccount);
        }
        if(request.getName() != null) {
            existingMentor.setName(request.getName());
        }
        if(request.getEmail() != null) {
            existingMentor.setEmail(request.getEmail());
        }
        if(request.getAddress() != null) {
            existingMentor.setAddress(request.getAddress());
        }
        if(request.getGender() != null) {
            existingMentor.setGender(request.getGender());
        }
        if(request.getPhone() != null) {
            existingMentor.setPhone(request.getPhone());
        }
        if(request.getBirthday() != null) {
            existingMentor.setBirthday(request.getBirthday());
        }

        Mentor mentorUpdated = mentorRepository.save(existingMentor);
        return mapper.map(mentorUpdated, MentorDetailDto.class);
    }

    public String deleteMentor(UUID id) {
        mentorRepository.deleteById(id);
        return "Delete mentor successfully";
    }
}
