package it.epicode.gestione_eventi.prenotazioni;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {
    @NotNull (message = "L'id dell'evento non può essere vuoto")
    private Long idEvento;
    @NotNull (message = "L'id dell'utente non può essere vuoto")
    private Long idUtente;
}
