package it.epicode.gestione_eventi.eventi;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/eventi" )
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<EventoResponse> findAll() {
        return eventoService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public EventoResponse findById(@PathVariable Long id) {
        return eventoService.findById(id);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public EventoResponse create(@RequestBody @Valid EventoRequest eventoRequest, @AuthenticationPrincipal AppUser organizzatore) {
        return eventoService.create(eventoRequest, organizzatore);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public EventoResponse update(@PathVariable Long id, @RequestBody @Valid EventoRequest eventoRequest, @AuthenticationPrincipal AppUser organizzatore) {
        return eventoService.update(id, eventoRequest, organizzatore);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ORGANIZZATORE')")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal AppUser organizzatore) {
        eventoService.delete(id, organizzatore);
    }
}
