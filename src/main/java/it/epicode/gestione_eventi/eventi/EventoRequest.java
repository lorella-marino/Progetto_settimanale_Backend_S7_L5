package it.epicode.gestione_eventi.eventi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoRequest {
    private Long idOrganizzatore;
    @NotBlank (message = "Il titolo non può essere vuoto")
    private String titolo;
    private String descrizione;
    private String data;
    private String luogo;
    @NotNull (message = "Il numero di posti disponibili non può essere vuoto")
    private int numeroPostiDisponibili;
}
