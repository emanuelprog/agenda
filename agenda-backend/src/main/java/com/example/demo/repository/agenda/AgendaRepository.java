package com.example.demo.repository.agenda;

import com.example.demo.dtos.agenda.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendaRepository extends MongoRepository<Agenda, String> {
}
