package kevinramil.U5_W3_L5.Repository;

import kevinramil.U5_W3_L5.Entities.Prenotazione;
import kevinramil.U5_W3_L5.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    List<Prenotazione> findByUser(User user);
}
