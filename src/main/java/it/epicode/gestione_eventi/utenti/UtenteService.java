package it.epicode.gestione_eventi.utenti;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public UtenteResponse toResponse( Utente utente) {
        return new UtenteResponse(utente.getId(), utente.getAppUser());
    }

    public List<UtenteResponse> findAll() {
        List <Utente> utenti = utenteRepository.findAll();
        return utenti.stream()
                .map(this::toResponse)
                .toList();
    }

    public UtenteResponse findById(Long id) {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        return new UtenteResponse(utente.getId(), utente.getAppUser());
    }

    // Consentire all'UTENTE di eliminare solo l'utente con il proprio ID
    public void delete(Long id, AppUser userAutenticato) {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        if (!utente.getAppUser().getId().equals(userAutenticato.getId())) {
            throw new IllegalArgumentException("Non sei autorizzato a eliminare questo utente");
        }

        utenteRepository.deleteById(id);
    }


}
