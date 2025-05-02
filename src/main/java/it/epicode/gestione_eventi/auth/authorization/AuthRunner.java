package it.epicode.gestione_eventi.auth.authorization;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import it.epicode.gestione_eventi.auth.app_user.AppUserService;
import it.epicode.gestione_eventi.auth.app_user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {

            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername("admin");
            registerRequest.setPassword("adminpwd");
            registerRequest.setNome("Admin");
            registerRequest.setCognome("Admin");
            appUserService.registerUser(registerRequest, Set.of(Role.ROLE_ADMIN));
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername("user");
            registerRequest.setPassword("userpwd");
            registerRequest.setNome("User");
            registerRequest.setCognome("User");

            appUserService.registerUser(registerRequest, Set.of(Role.ROLE_USER));
        }

        Optional<AppUser> normalUser2 = appUserService.findByUsername("user2");
        if (normalUser2.isEmpty()) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername("user2");
            registerRequest.setPassword("userpwd2");
            registerRequest.setNome("User2");
            registerRequest.setCognome("User2");

            appUserService.registerUser(registerRequest, Set.of(Role.ROLE_USER));
        }



        // Creazione dell'utente organizzatore se non esiste
        Optional<AppUser> organizzatoreUser = appUserService.findByUsername("organizzatore");
        if (organizzatoreUser.isEmpty()) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername("organizzatore");
            registerRequest.setPassword("organizzatorepwd");
            registerRequest.setNome("Organizzatore");
            registerRequest.setCognome("Organizzatore");

            appUserService.registerUser(registerRequest, Set.of(Role.ROLE_ORGANIZZATORE));
        }

        Optional<AppUser> organizzatoreUser2 = appUserService.findByUsername("organizzatore2");
        if (organizzatoreUser2.isEmpty()) {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername("organizzatore2");
            registerRequest.setPassword("organizzatorepwd2");
            registerRequest.setNome("Organizzatore2");
            registerRequest.setCognome("Organizzatore2");

            appUserService.registerUser(registerRequest, Set.of(Role.ROLE_ORGANIZZATORE));
        }
}
}
