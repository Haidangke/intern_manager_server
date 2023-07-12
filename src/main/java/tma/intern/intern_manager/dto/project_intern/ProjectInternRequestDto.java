package tma.intern.intern_manager.dto.project_intern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInternRequestDto {
    private UUID id;
    private UUID project;
    private UUID intern;
}
