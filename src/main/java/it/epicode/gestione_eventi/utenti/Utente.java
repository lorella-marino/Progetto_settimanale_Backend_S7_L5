package it.epicode.gestione_eventi.utenti;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utenti")

public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade={CascadeType.REMOVE, CascadeType.PERSIST})
    private AppUser appUser;
    private String nome;
    private String cognome;
}