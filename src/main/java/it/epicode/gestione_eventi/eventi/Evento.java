package it.epicode.gestione_eventi.eventi;

import it.epicode.gestione_eventi.organizzatori.Organizzatore;
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
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numeroPostiDisponibili;
    @ManyToOne
    private Organizzatore organizzatore;


}