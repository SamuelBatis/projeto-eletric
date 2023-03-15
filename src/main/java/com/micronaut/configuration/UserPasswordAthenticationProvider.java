package com.micronaut.configuration;

import com.micronaut.etities.User;
import com.micronaut.repositories.UserRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.sql.SQLException;

@Singleton
public class UserPasswordAthenticationProvider implements AuthenticationProvider {

  @Inject
  private UserRepository userRepository;
  @Override
  public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> httpRequest, AuthenticationRequest<?, ?> authenticationRequest) {

    String username = authenticationRequest.getIdentity().toString();
    String password = authenticationRequest.getSecret().toString();
    User user;
    try {
      user = userRepository.findByLogin(username);
      System.out.println("entrou");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return Flux.create(emitter -> {
      if (authenticationRequest.getIdentity().equals(user.getLogin()) &&
          authenticationRequest.getSecret().equals(user.getSenha())) {
        emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
        emitter.complete();
      } else {
        emitter.error(AuthenticationResponse.exception());
      }
    }, FluxSink.OverflowStrategy.ERROR);
  }
}
