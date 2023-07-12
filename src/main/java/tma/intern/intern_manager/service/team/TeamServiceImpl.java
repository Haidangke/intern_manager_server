package tma.intern.intern_manager.service.team;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.team.ITeamQuantity;
import tma.intern.intern_manager.dto.team.TeamQuantity;
import tma.intern.intern_manager.dto.team.TeamRequestDto;
import tma.intern.intern_manager.dto.team.TeamDetailDto;
import tma.intern.intern_manager.entity.Mentor;
import tma.intern.intern_manager.entity.Team;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.MentorRepository;
import tma.intern.intern_manager.repository.TeamRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private final TeamRepository teamRepository;
    @Autowired
    private final MentorRepository mentorRepository;
    @Autowired
    private final ModelMapper mapper;

    public TeamServiceImpl(TeamRepository teamRepository, MentorRepository mentorRepository, ModelMapper mapper) {
        this.teamRepository = teamRepository;
        this.mentorRepository = mentorRepository;
        this.mapper = mapper;
    }
    @Override
    public Page<ITeamQuantity> getListTeam(PageRequest pr) {
        Page<ITeamQuantity> response = teamRepository.findAll(pr).map(entity -> {
            TeamQuantity item = mapper.map(entity, TeamQuantity.class);
            item.setTotalIntern(entity.getInterns().size());
            return item;
        });

        return response;
    }

    @Override
    public TeamQuantity getTeamById(UUID id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team is not found"));
        TeamQuantity response = mapper.map(team, TeamQuantity.class);
        response.setTotalIntern(team.getInterns().size());
        return response;
    }

    @Override
    public TeamDetailDto addTeam(TeamRequestDto request) {
        Team create = mapper.map(request, Team.class);

        if(request.getMentor() != null) {
            Optional<Mentor> mentor = mentorRepository.findById(request.getMentor());
            mentor.ifPresent(create::setMentor);
        }

        Team newTeam = teamRepository.save(create);
        return mapper.map(newTeam, TeamDetailDto.class);
    }

    @Override
    public TeamDetailDto updateTeam(TeamRequestDto request, UUID id) {
        Team existingTeam = teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team is not found"));

        if(request.getMentor() != null) {
            Optional<Mentor> mentor = mentorRepository.findById(request.getMentor());
            mentor.ifPresent(existingTeam::setMentor);
        }

        if(request.getName() !=null) {
            existingTeam.setName(request.getName());
        }

        Team teamUpdated = teamRepository.save(existingTeam);
        return mapper.map(teamUpdated, TeamDetailDto.class);
    }

    @Override
    public String deleteTeam(UUID id) {
        Team existingTeam = teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team is not found"));
        teamRepository.deleteById(existingTeam.getId());
        return "Delete team successfully";
    }
}
