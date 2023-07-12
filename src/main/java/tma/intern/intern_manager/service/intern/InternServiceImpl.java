package tma.intern.intern_manager.service.intern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.intern.InternRequestDto;
import tma.intern.intern_manager.dto.intern.InternDetailDto;
import tma.intern.intern_manager.entity.Account;
import tma.intern.intern_manager.entity.Intern;
import tma.intern.intern_manager.entity.Mentor;
import tma.intern.intern_manager.entity.Team;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.AccountRepository;
import tma.intern.intern_manager.repository.InternRepository;
import tma.intern.intern_manager.repository.MentorRepository;
import tma.intern.intern_manager.repository.TeamRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class InternServiceImpl implements  InternService {
    @Autowired
    private final InternRepository internRepository;
    @Autowired
    private final ModelMapper mapper;
    @Autowired
    private final MentorRepository mentorRepository;
    @Autowired
    private final TeamRepository teamRepository;
    @Autowired
    private  final AccountRepository accountRepository;

    public InternServiceImpl(
            InternRepository internRepository,
            ModelMapper mapper, MentorRepository mentorRepository,
            TeamRepository teamRepository,
            AccountRepository accountRepository) {
        this.internRepository = internRepository;
        this.mapper = mapper;
        this.mentorRepository = mentorRepository;
        this.teamRepository = teamRepository;
        this.accountRepository = accountRepository;
    }

    public Page<InternDetailDto> getListIntern(PageRequest pr) {
        Page<Intern> listIntern = internRepository.findAll(pr);
        Page<InternDetailDto> response = listIntern.map(entity -> mapper.map(entity, InternDetailDto.class));
        return response;
    }

    public InternDetailDto getInternById(UUID id) {
        Intern intern =  internRepository.findById(id).orElseThrow(() -> new NotFoundException("User is not found"));
        InternDetailDto response = mapper.map(intern, InternDetailDto.class);
        return response;
    }

    public InternDetailDto addIntern(InternRequestDto request) {
        Intern intern = mapper.map(request, Intern.class);

        if(request.getMentor() != null) {
            Optional<Mentor> mentor = mentorRepository.findById(request.getMentor());
            mentor.ifPresent(intern::setMentor);
        }

        if(request.getTeam() != null) {
            Optional<Team> team = teamRepository.findById(request.getTeam());
            team.ifPresent(intern::setTeam);
        }

        if(request.getAccount() != null) {
            Optional<Account> account = accountRepository.findById(request.getAccount());
            account.ifPresent(intern::setAccount);
        }

        internRepository.save(intern);
        return mapper.map(intern, InternDetailDto.class) ;
    }

    public Page<InternDetailDto> getListInternByMentor(PageRequest pr, UUID mentorId) {
        return internRepository.findAllByMentorId(mentorId, pr)
                .map(entity -> mapper.map(entity, InternDetailDto.class));
    }

    public Page<InternDetailDto> getListInternByTeam(PageRequest pr, UUID mentorId) {
        return internRepository.findAllByTeamId(mentorId, pr)
                .map(entity -> mapper.map(entity, InternDetailDto.class));
    }

    public InternDetailDto updateIntern(UUID id, InternRequestDto request) {
        Intern existingIntern = internRepository.findById(id).orElseThrow(() -> new NotFoundException("Intern is not found"));
        if(request.getMentor() != null) {
            Optional<Mentor> mentor = mentorRepository.findById(request.getMentor());
            mentor.ifPresent(existingIntern::setMentor);
        }
        if(request.getTeam() != null) {
            Optional<Team> team = teamRepository.findById(request.getTeam());
            team.ifPresent(existingIntern::setTeam);
        }
        if(request.getAccount() != null) {
            Optional<Account> account = accountRepository.findById(request.getAccount());
            account.ifPresent(existingIntern::setAccount);
        }
        if(request.getName()!= null) {
            existingIntern.setName(request.getName());
        }
        if(request.getAddress()!= null) {
            existingIntern.setAddress(request.getAddress());
        }
        if(request.getGender()!= null) {
            existingIntern.setGender(request.getGender());
        }
        if(request.getPhone()!= null) {
            existingIntern.setPhone(request.getPhone());
        }
        if(request.getBirthday()!= null) {
            existingIntern.setBirthday(request.getBirthday());
        }
        if(request.getDescription()!= null) {
            existingIntern.setDescription(request.getDescription());
        }
        if(request.getTechnology()!= null) {
            existingIntern.setTechnology(request.getTechnology());
        }
        if(request.getName()!= null) {
            existingIntern.setEmail(request.getEmail());
        }
        if(request.getStatus()!= null) {
            existingIntern.setStatus(request.getStatus());
        }

        internRepository.save(existingIntern);

        return mapper.map(existingIntern, InternDetailDto.class);
    }

    public String deleteIntern(UUID id) {
        Intern intern = internRepository.findById(id).orElseThrow(() -> new NotFoundException("Intern is not found"));

        internRepository.deleteById(intern.getId());
        return "Delete intern successfully";
    }
}
