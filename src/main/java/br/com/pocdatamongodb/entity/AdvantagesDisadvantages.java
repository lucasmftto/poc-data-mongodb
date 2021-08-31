package br.com.pocdatamongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class AdvantagesDisadvantages extends CharacterRoot implements Serializable {

    @Id
    private String id;

    public AdvantagesDisadvantages(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AdvantagesDisadvantages{" +
                "id='" + id + '\'' +
                '}';
    }
}
