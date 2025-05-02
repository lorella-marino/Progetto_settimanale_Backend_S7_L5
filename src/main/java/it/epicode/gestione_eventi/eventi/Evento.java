package it.epicode.gestione_eventi.eventi;

import it.epicode.gestione_eventi.utenti.Utente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventi")

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long idOrganizzatore;
    private String titolo;
    private String descrizione;
    private String data;
    private String luogo;
    private int numeroPostiDisponibili;

}