package it.epicode.gestione_eventi.prenotazioni;

import it.epicode.gestione_eventi.eventi.Evento;
import it.epicode.gestione_eventi.utenti.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prenotazioni")

public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Evento evento;
    @ManyToOne
    private Utente utente;


}