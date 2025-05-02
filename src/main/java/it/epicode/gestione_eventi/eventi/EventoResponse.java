package it.epicode.gestione_eventi.eventi;

import it.epicode.gestione_eventi.utenti.Utente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoResponse {
    private Long id;
    private Long idOrganizzatore;
    private String titolo;
    private String descrizione;
    private String data;
    private String luogo;
    private int numeroPostiDisponibili;
}
