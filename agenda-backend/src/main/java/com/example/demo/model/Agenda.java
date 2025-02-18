package com.example.demo.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "agendas")
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {
    @Id
    private String id;
    private String titulo;
    private String descricao;

    public Agenda(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
