package com.lab3.lab3.service;

import com.lab3.lab3.dto.UserInfoDto;
import com.lab3.lab3.dto.UserRegisterDto;
import com.lab3.lab3.model.Users;
import com.lab3.lab3.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    UsersRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    public UsersService(UsersRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public UserInfoDto getByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        return new UserInfoDto(user.getName(), user.getSecondName(), user.getEmail(), user.getId());
    }

    public Users save(UserRegisterDto registrationDto) {

        Users.UserRole role = registrationDto.getRole();

        if (role == null) {
            role = Users.UserRole.READER;
        }

        Users user = new Users(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), role);


        return userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().toString())));
    }
}
