package tma.intern.intern_manager.dto.team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequestDto {
    private UUID id;
    private String name;
    private UUID mentor;
}
