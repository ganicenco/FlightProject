package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.model.modelAuth.AuthenticationRequest;
import ro.itschool.model.modelAuth.AuthenticationResponse;
import ro.itschool.model.modelAuth.RegisterRequest;

@Service
public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
