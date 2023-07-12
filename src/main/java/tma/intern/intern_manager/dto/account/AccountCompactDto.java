package tma.intern.intern_manager.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.enums.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCompactDto {
    private UUID id;
    private String username;
    private String password;
    private Role role;
}
