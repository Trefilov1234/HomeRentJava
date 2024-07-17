package ru.exfadra.homeRent.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.exfadra.homeRent.model.User;
import ru.exfadra.homeRent.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Bean
    public Authentication getAuth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(s);
        return new UserPrincipal(user);
    }
}
