package kevinramil.U5_W3_L5.Entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

// LISTA ATTRIBUTI
@Entity
@Table(name = "prenotazioni")
@NoArgsConstructor
@Getter
@Setter
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE) // serve per non richiamarlo nel setter
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private User user;

    // LISTA COSTRUTTORI
    public Prenotazione(Evento evento, User utente) {
        this.evento = evento;
        this.user = utente;
    }

    // LISTA METODI

    // GETTER/SETTER
    // genera automaticamente dal lombok
}
