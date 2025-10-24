package kevinramil.U5_W3_L5.Entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "event")
public class Evento {

    // LISTA ATTRIBUTI
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE) // serve per non richiamarlo nel setter
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "place")
    private String place;

    @Column(name = "n_max")
    private int numMax;

    @ManyToMany
    @JoinColumn(name = "organizzatore_id")
    private User organizzatore;

    // LISTA COSTRUTTORI
    public Evento(User organizzatore, String description, LocalDate date, String place, int numMax) {
        this.organizzatore = organizzatore;
        this.description = description;
        this.date = date;
        this.place = place;
        this.numMax = numMax;
    }

    // LISTA METODI

    // GETTER/SETTER
    // generati automaticamente con il lombok
}
