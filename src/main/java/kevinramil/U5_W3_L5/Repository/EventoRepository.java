package kevinramil.U5_W3_L5.Repository;

import kevinramil.U5_W3_L5.Entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    List<Evento> findByData(LocalDate date);

    List<Evento> findByPlace(String place);
}
