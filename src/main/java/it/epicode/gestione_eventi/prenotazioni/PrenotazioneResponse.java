package it.epicode.gestione_eventi.prenotazioni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {
    private Long id;
    private Long idUtente;
    private String titoloEvento;
}
