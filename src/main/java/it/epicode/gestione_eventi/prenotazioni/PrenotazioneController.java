package it.epicode.gestione_eventi.prenotazioni;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import it.epicode.gestione_eventi.eventi.EventoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ORGANIZZATORE')")
    public List<PrenotazioneResponse> findAll() {
        return prenotazioneService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER')")
    public PrenotazioneResponse findById(@PathVariable Long id, @AuthenticationPrincipal AppUser organizzatore) {
        return prenotazioneService.findById(id, organizzatore);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public PrenotazioneResponse create(@RequestBody @Valid PrenotazioneRequest prenotazioneRequest, @AuthenticationPrincipal AppUser organizzatore) {
        return prenotazioneService.create(prenotazioneRequest, organizzatore);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal AppUser utente) {
        prenotazioneService.delete(id, utente);
    }
}
