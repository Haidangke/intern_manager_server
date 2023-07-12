package tma.intern.intern_manager.service.team;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import tma.intern.intern_manager.dto.team.ITeamQuantity;
import tma.intern.intern_manager.dto.team.TeamQuantity;
import tma.intern.intern_manager.dto.team.TeamRequestDto;
import tma.intern.intern_manager.dto.team.TeamDetailDto;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    Page<ITeamQuantity> getListTeam(PageRequest pr);

    TeamQuantity getTeamById(UUID id);

    TeamDetailDto addTeam(TeamRequestDto request);

    TeamDetailDto updateTeam(TeamRequestDto request, UUID id);

    String deleteTeam(UUID id);

}
