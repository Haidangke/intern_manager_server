package tma.intern.intern_manager.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.enums.Role;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {
    private UUID id;
    private String username;
    private String password;
    private Role role;
    private UUID mentor;
    private UUID intern;
}
