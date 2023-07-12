package tma.intern.intern_manager.service.authentication;

import tma.intern.intern_manager.dto.authentication.AuthenticationRequest;
import tma.intern.intern_manager.dto.authentication.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
