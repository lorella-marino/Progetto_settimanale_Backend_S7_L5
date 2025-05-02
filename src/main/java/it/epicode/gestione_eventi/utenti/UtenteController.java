package it.epicode.gestione_eventi.utenti;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/utenti" )
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<UtenteResponse> findAll() {
        return (List<UtenteResponse>) utenteService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public UtenteResponse findById(@PathVariable Long id) {
        return utenteService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated()")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal AppUser userAutenticato) {
        utenteService.delete(id, userAutenticato);
    }

}
