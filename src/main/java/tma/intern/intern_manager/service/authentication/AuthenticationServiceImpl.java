package tma.intern.intern_manager.service.authentication;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.account.AccountDetailDto;
import tma.intern.intern_manager.entity.Account;
import tma.intern.intern_manager.dto.authentication.AuthenticationRequest;
import tma.intern.intern_manager.dto.authentication.AuthenticationResponse;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.AccountRepository;
import tma.intern.intern_manager.service.jwt.JwtServiceImpl;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtServiceImpl jwtService;
    @Autowired
    private final ModelMapper mapper;

    public AuthenticationServiceImpl(AccountRepository accountRepository, AuthenticationManager authenticationManager, JwtServiceImpl jwtService, ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.mapper = mapper;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
        ));
        Account account = accountRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Account is not found"));
        String token = jwtService.generateToken(account);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(token);
        response.setAccount(mapper.map(account, AccountDetailDto.class));
        return response;
    }
}
