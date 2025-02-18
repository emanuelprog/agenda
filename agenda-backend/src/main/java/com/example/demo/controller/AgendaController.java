package com.example.demo.controller;

import com.example.demo.dto.AgendaRequestDTO;
import com.example.demo.dto.AgendaResponseDTO;
import com.example.demo.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agenda")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AgendaController {

    private final AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaResponseDTO>> buscarAgendas() {
        return ResponseEntity.status(HttpStatus.OK).body(agendaService.buscar());
    }

    @PostMapping
    public ResponseEntity<AgendaResponseDTO> salvarAgenda(@RequestBody AgendaRequestDTO agendaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.criar(agendaRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponseDTO> editarAgenda(@PathVariable String id, @RequestBody AgendaRequestDTO agendaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(agendaService.editar(id, agendaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AgendaResponseDTO> deletarAgenda(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(agendaService.deletar(id));
    }
}
