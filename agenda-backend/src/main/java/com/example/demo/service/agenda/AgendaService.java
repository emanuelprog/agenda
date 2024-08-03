package com.example.demo.service.agenda;

import com.example.demo.dtos.agenda.Agenda;
import com.example.demo.dtos.agenda.AgendaRequestDTO;
import com.example.demo.dtos.agenda.AgendaResponseDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.agenda.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public List<AgendaResponseDTO> buscar() {
        List<Agenda> agendas = agendaRepository.findAll();

        if (agendas.isEmpty()) {
            return new ArrayList<>();
        }

        return agendas.stream()
                .map(agenda -> new AgendaResponseDTO(
                        agenda.getId(),
                        agenda.getTitulo(),
                        agenda.getDescricao()))
                .collect(Collectors.toList());
    }

    public AgendaResponseDTO criar(AgendaRequestDTO agendaRequestDTO) {
        try {
            Agenda agenda = agendaRepository.save(new Agenda(agendaRequestDTO.titulo(), agendaRequestDTO.descricao()));
            return new AgendaResponseDTO(agenda.getId(), agenda.getTitulo(), agenda.getDescricao());
        } catch (Exception e) {
            throw new BadRequestException("Não foi possível criar a agenda!");
        }
    }

    public AgendaResponseDTO editar(String id, AgendaRequestDTO agendaRequestDTO) {
        Optional<Agenda> agendaDB = agendaRepository.findById(id);

        if (agendaDB.isPresent()) {
            Agenda agenda = agendaRepository.save(
                    new Agenda(
                            id,
                            agendaRequestDTO.titulo(),
                            agendaRequestDTO.descricao()
                    ));
            return new AgendaResponseDTO(agenda.getId(), agenda.getTitulo(), agenda.getDescricao());
        } else {
            throw new BadRequestException("Não foi possível editar a agenda!");
        }
    }

    public AgendaResponseDTO deletar(String id) {
        Optional<Agenda> agendaDB = agendaRepository.findById(id);

        if (!agendaDB.isPresent()) {
            throw new NotFoundException("Nenhuma agenda encontrada!");
        }

        agendaRepository.deleteById(id);
        return null;
    }
}
