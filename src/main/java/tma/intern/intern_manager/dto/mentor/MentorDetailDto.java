package tma.intern.intern_manager.dto.mentor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.dto.account.AccountDetailDto;
import tma.intern.intern_manager.dto.intern.InternCompactDto;
import tma.intern.intern_manager.dto.team.TeamCompactDto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorDetailDto {
    private UUID id;
    private String name;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private Date birthday;
    private AccountDetailDto account;
    private List<InternCompactDto> interns;
    private List<TeamCompactDto> teams;
}
