package com.example.basejwt;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.basejwt.entity.User;
import com.example.basejwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
public class BaseJwtApplication {

	final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@PostConstruct
	public void UserInit() {
		List<User> users = Stream.of(
				new User("khmerside", passwordEncoder.encode("123"), "khmerside@mail.com"),
				new User("chumkiri", passwordEncoder.encode("111"), "chumkiri@mail.com"))
				.collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(BaseJwtApplication.class, args);
	}
}