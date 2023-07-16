package tma.intern.intern_manager.dto.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailDto {
    private UUID id;
    private String name;
    private int totalIntern;
}
