package kevinramil.U5_W3_L5.Services;

import kevinramil.U5_W3_L5.Entities.Evento;
import kevinramil.U5_W3_L5.Entities.User;
import kevinramil.U5_W3_L5.Enum.UserType;
import kevinramil.U5_W3_L5.Exceptions.BadRequestException;
import kevinramil.U5_W3_L5.Exceptions.NotFoundException;
import kevinramil.U5_W3_L5.Payload.EventoDTO;
import kevinramil.U5_W3_L5.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UserService userService;

    private LocalDate getData(String data) {
        String dataString = "";
        if (data.length() > 10) {
            dataString = data.substring(0, 10);
        } else if (data.length() == 10) {
            dataString = data;
        } else {
            throw new BadRequestException("data non valida");
        }
        try {
            String[] dataArray = dataString.split("-");
            int year = Integer.parseInt(dataArray[0]);
            int month = Integer.parseInt(dataArray[1]);
            int day = Integer.parseInt(dataArray[2]);
            LocalDate dateLocal = LocalDate.of(year, month, day);
            return dateLocal;
        } catch (Exception ex) {
            throw new BadRequestException("data non valida");
        }
    }

    public Evento findById(UUID id) {
        Optional<Evento> found = eventoRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new NotFoundException("l'evento con id " + id + " non esiste");
        }
    }

    public Evento saveEvento(EventoDTO evento, UUID orgId) {
        LocalDate data = getData(evento.date());
        if (data.isBefore(LocalDate.now())) {
            throw new BadRequestException("non puoi organizzare un evento in data passata");
        }
        User organizzatore = userService.findById(orgId);
        if (organizzatore.getUserType() == UserType.NORMALE_UTENTE) {
            throw new BadRequestException("solo gli organizzatori possono salvare gli eventi");
        }
        List<Evento> fondByDate = eventoRepository.findByData(data);
        for (int i = 0; i < fondByDate.size(); i++) {
            if (fondByDate.get(i).getPlace().equals(evento.place())) {
                throw new BadRequestException("non puoi salvare due eventi con stessi luogo e data");
            }
        }

        Evento event = new Evento(organizzatore, evento.description(), data, evento.place(), evento.numMax());
        eventoRepository.save(event);
        System.out.println("evento salvato");
        return event;
    }

    public Evento editEvento(EventoDTO evento, UUID organizzatoreId, UUID eventoId) {
        LocalDate data = getData(evento.date());
        Evento ev = this.findById(eventoId);
        if (data.isBefore(LocalDate.now())) {
            throw new BadRequestException("non puoi salvare un evento in data passata");
        }
        User organizzatore = userService.findById(organizzatoreId);
        if (organizzatore.getUserType() == UserType.NORMALE_UTENTE) {
            throw new BadRequestException("solo gli organizzatori possono modificare gli eventi");
        }
        List<Evento> fondByDate = eventoRepository.findByData(data);
        for (int i = 0; i < fondByDate.size(); i++) {
            if (fondByDate.get(i).getPlace().equals(evento.place())) {
                throw new BadRequestException("non puoi salvare due eventi con stessi luogo e data");
            }
        }
        ev.setDate(data);
        ev.setPlace(evento.place());
        ev.setDescription(evento.description());
        ev.setNumMax(evento.numMax());
        eventoRepository.save(ev);
        System.out.println("evento modificato");
        return ev;
    }
}
