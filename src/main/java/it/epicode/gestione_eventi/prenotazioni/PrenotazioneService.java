package it.epicode.gestione_eventi.prenotazioni;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import it.epicode.gestione_eventi.auth.app_user.Role;
import it.epicode.gestione_eventi.eventi.Evento;
import it.epicode.gestione_eventi.eventi.EventoRepository;
import it.epicode.gestione_eventi.utenti.Utente;
import it.epicode.gestione_eventi.utenti.UtenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private UtenteRepository utenteRepository ;
    @Autowired
    private EventoRepository eventoRepository;

    public PrenotazioneResponse toResponse (Prenotazione prenotazione){
        return new PrenotazioneResponse(prenotazione.getId(), prenotazione.getUtente().getId(), prenotazione.getEvento().getTitolo());
    }

    public List<PrenotazioneResponse> findAll() {
        return prenotazioneRepository.findAll().stream().map(this::toResponse).toList();
    }

    // Consentire all'UTENTE di trovare solo la prenotazione con il proprio ID
    public PrenotazioneResponse findById(Long id, AppUser utente) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        if (!prenotazione.getUtente().getId().equals(utente.getId())) {
            throw new IllegalArgumentException("Non sei autorizzato a visualizzare questa prenotazione");
        }

        return new PrenotazioneResponse(
                prenotazione.getId(),
                prenotazione.getUtente().getId(),
                prenotazione.getEvento().getTitolo()
        );
    }


    // Consentire all'UTENTE la prenotazione solo se il numero di posti disponibili è maggiore di 0 e con il suo ID
    public PrenotazioneResponse create(@Valid PrenotazioneRequest prenotazioneRequest, AppUser utenteAutenticato) {
        if (!prenotazioneRequest.getIdUtente().equals(utenteAutenticato.getId())) {
            throw new IllegalArgumentException("Non sei autorizzato a prenotare per un altro utente");
        }

        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(prenotazioneRequest, prenotazione);

        Utente utente = utenteRepository.findById(prenotazioneRequest.getIdUtente())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        prenotazione.setUtente(utente);

        Evento evento = eventoRepository.findById(prenotazioneRequest.getIdEvento())
                .orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));
        prenotazione.setEvento(evento);

        if (evento.getNumeroPostiDisponibili() > 0) {
            evento.setNumeroPostiDisponibili(evento.getNumeroPostiDisponibili() - 1);
            eventoRepository.save(evento);
            prenotazioneRepository.save(prenotazione);
            return toResponse(prenotazione);
        } else {
            throw new IllegalArgumentException("Non ci sono posti disponibili per questo evento");
        }
    }


    // Consentire all'UTENTE di eliminare solo la prenotazione con il proprio ID
    public void delete(Long id, AppUser utente) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        if (!prenotazione.getUtente().getId().equals(utente.getId())) {
            throw new IllegalArgumentException("Non sei autorizzato a eliminare questa prenotazione");
        }

        prenotazioneRepository.deleteById(id);
    }

}
