package com.groupproject.services;

import com.groupproject.entities.Account;
import com.groupproject.entities.Role;
import com.groupproject.repository.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountRepository applicationUserRepository;

    public UserDetailsServiceImpl(AccountRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        Role role = applicationUser.getRole();
        if (role.getType().contains("User")) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            log.info("loadUserByUsername: found match, returning "
                    + applicationUser.getUsername() + " With role " + authorities.toString()
            );
            return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
        } else if (role.getType().contains("Admin")) {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            log.info("loadUserByUsername: found match, returning "
                    + applicationUser.getUsername() + " With role " + authorities.toString()
            );
            return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
        } else {
            return null;
        }
    }
}
