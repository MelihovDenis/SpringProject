package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        //Получаются имя пользователя и пароль и объединяются в экземпляр класса UsernamePasswordAuthenticationToken
        authenticationManager.authenticate(authenticationToken);
        //Токен передается экземпляру AuthenticationManager для проверки.
        System.out.println("Проверка токена");
        if (authenticationToken.isAuthenticated()) {  //AuthenticationManager возвращает полностью заполненный экземпляр Authentication в случае успешной аутентификации.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            System.out.println("Токен прошёл проверку");
            // Устанавливается контекст безопасности путем вызова SecurityContextHolder.getContext().setAuthentication(...),
            // куда передается вернувшийся экземпляр Authentication
        }
    }
}
