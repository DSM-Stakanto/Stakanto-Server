package com.server.stakantoserver.security.details;

import com.server.stakantoserver.entity.User;
import com.server.stakantoserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccountID(username).orElseThrow(RuntimeException::new);
        return new UserDetails(user);
    }
}
