package tma.intern.intern_manager.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.dto.intern.InternCompactDto;
import tma.intern.intern_manager.dto.mentor.MentorCompactDto;
import tma.intern.intern_manager.enums.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailDto {
    private UUID id;
    private String username;
    private String password;
    private Role role;
    private InternCompactDto intern;
    private MentorCompactDto mentor;
}
