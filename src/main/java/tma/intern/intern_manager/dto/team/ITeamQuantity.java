package tma.intern.intern_manager.dto.team;

import tma.intern.intern_manager.dto.mentor.MentorCompactDto;

import java.util.UUID;

public interface ITeamQuantity {
    UUID getId();
    String getName();
    Integer getTotalIntern();
    MentorCompactDto getMentor();
}
