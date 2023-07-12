package tma.intern.intern_manager.dto.intern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.dto.account.AccountCompactDto;
import tma.intern.intern_manager.dto.account.AccountDetailDto;
import tma.intern.intern_manager.dto.mentor.MentorCompactDto;
import tma.intern.intern_manager.dto.team.TeamCompactDto;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternDetailDto {
    UUID id;
    String name;
    String email;
    String phone;
    String gender;
    String address;
    Date birthday;
    String description;
    String technology;
    String status;
    MentorCompactDto mentor;
    TeamCompactDto team;
    AccountCompactDto account;
}
