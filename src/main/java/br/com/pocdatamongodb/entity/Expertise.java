package br.com.pocdatamongodb.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Expertise extends CharacterRoot implements Serializable {

    @Id
    private String id;

    public Expertise(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pericia{" +
                "id='" + id + '\'' +
                '}';
    }
}
