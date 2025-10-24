package kevinramil.U5_W3_L5.Services;

import kevinramil.U5_W3_L5.Entities.Evento;
import kevinramil.U5_W3_L5.Entities.Prenotazione;
import kevinramil.U5_W3_L5.Entities.User;
import kevinramil.U5_W3_L5.Exceptions.BadRequestException;
import kevinramil.U5_W3_L5.Payload.PrenotazioneDTO;
import kevinramil.U5_W3_L5.Repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventoService evServ;

    public Prenotazione savePrenotazione(PrenotazioneDTO pren, UUID userId) {

        User userFound = userService.findById(userId);
        Evento eventoFound = evServ.findById(pren.idEvento());
        List<Prenotazione> preList = prenotazioneRepository.findByUser(userFound);
        if (!preList.isEmpty()) {
            for (int i = 0; i < preList.size(); i++) {
                if (preList.get(i).getEvento().getDate().equals(eventoFound.getDate())) {
                    throw new BadRequestException("hai già una prenotazione per quella data");
                }
            }
        }
        Prenotazione prenotazione = new Prenotazione(eventoFound, userFound);
        prenotazioneRepository.save(prenotazione);
        System.out.println("prenotazione salvata");
        return prenotazione;
    }
}
