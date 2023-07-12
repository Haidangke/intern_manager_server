package tma.intern.intern_manager.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tma.intern.intern_manager.dto.account.AccountDetailDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String accessToken;
    private AccountDetailDto account;
}
