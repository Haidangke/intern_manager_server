package tma.intern.intern_manager.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.dto.mentor.MentorCompactDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamQuantity implements ITeamQuantity{
    UUID id;
    String name;
    MentorCompactDto mentor;
    Integer totalIntern;
}
