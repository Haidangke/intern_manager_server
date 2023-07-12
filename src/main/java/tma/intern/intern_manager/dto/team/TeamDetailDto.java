package tma.intern.intern_manager.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.dto.mentor.MentorCompactDto;
import tma.intern.intern_manager.dto.mentor.MentorDetailDto;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDetailDto {
    private UUID id;
    private String name;
    private MentorCompactDto mentor;
}
