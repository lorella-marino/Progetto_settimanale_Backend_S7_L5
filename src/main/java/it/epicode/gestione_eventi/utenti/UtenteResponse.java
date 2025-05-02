package it.epicode.gestione_eventi.utenti;

import it.epicode.gestione_eventi.auth.app_user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteResponse {
    private Long id;
    private AppUser appUser;
}
