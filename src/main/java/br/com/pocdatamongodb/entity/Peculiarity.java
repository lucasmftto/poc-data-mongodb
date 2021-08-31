package br.com.pocdatamongodb.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Peculiarity extends CharacterRoot{

    @Id
    private String id;

    public Peculiarity(String id) {
        this.id = id;
    }

    public Peculiarity() {

    }

    @Override
    public String toString() {
        return "Peculiarity{" +
                "id='" + id + '\'' +
                '}';
    }
}
