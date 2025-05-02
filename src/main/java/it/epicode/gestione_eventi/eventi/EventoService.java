package it.epicode.gestione_eventi.eventi;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import it.epicode.gestione_eventi.auth.app_user.Role;
import it.epicode.gestione_eventi.utenti.Utente;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public EventoResponse toResponse(Evento evento) {
        return new EventoResponse(evento.getId(), evento.getIdOrganizzatore(), evento.getTitolo(), evento.getDescrizione(), evento.getData(), evento.getLuogo(), evento.getNumeroPostiDisponibili());
    }

    public List<EventoResponse> findAll() {
        return eventoRepository.findAll().stream().map(this::toResponse).toList();
    }

    public EventoResponse findById(Long id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));
        return toResponse(evento);
    }

    // Consentire all'ORGANIZZATORE di creare un evento inserendo il proprio ID
    public EventoResponse create(@Valid EventoRequest eventoRequest, AppUser organizzatore) {
        Evento evento = new Evento();
        BeanUtils.copyProperties(eventoRequest, evento);

        boolean isOrganizzatore = organizzatore.getRoles().contains(Role.ROLE_ORGANIZZATORE);

        if (!organizzatore.getId().equals(eventoRequest.getIdOrganizzatore()) || !isOrganizzatore) {
            throw new IllegalArgumentException("Non sei autorizzato a creare questo evento");
        }

        Utente utente = new Utente();
        utente.setId(eventoRequest.getIdOrganizzatore());
        evento.setIdOrganizzatore(utente.getId());

        eventoRepository.save(evento);
        return toResponse(evento);
    }

    // Consentire all'ORGANIZZATORE di modificare solo l'evento con il proprio ID
    public EventoResponse update(Long id, @Valid EventoRequest eventoRequest, AppUser organizzatore) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));

        boolean isOrganizzatore = organizzatore.getRoles().contains(Role.ROLE_ORGANIZZATORE);

        if (!organizzatore.getId().equals(evento.getIdOrganizzatore()) || !isOrganizzatore) {
            throw new IllegalArgumentException("Non sei autorizzato a modificare questo evento");
        }

        BeanUtils.copyProperties(eventoRequest, evento);
        eventoRepository.save(evento);
        return toResponse(evento);

    }


    // Consentire all'ORGANIZZATORE di eliminare solo l'evento con il proprio ID
    public void delete(Long id, AppUser organizzatore) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Evento non trovato"));

        boolean isOrganizzatore = organizzatore.getRoles().contains(Role.ROLE_ORGANIZZATORE);

        if (!organizzatore.getId().equals(evento.getIdOrganizzatore()) || !isOrganizzatore) {
            throw new IllegalArgumentException("Non sei autorizzato a eliminare questo evento");
        }

        eventoRepository.deleteById(id);
    }


}
