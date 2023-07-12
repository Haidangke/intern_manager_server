package tma.intern.intern_manager.dto.project_intern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.dto.intern.InternCompactDto;
import tma.intern.intern_manager.dto.project.ProjectCompactDto;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInternDetailDto {
    private UUID id;
    private InternCompactDto intern;
    private ProjectCompactDto project;
}
