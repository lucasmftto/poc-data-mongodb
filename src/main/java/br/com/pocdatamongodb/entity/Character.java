package br.com.pocdatamongodb.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Set;

@Data
public class Character extends CharacterRoot implements Serializable {

    @Id
    private String id;

    private Long dx;

    private Long st;

    private Long ht;

    private Long iq;

    private String gamer;

    private String description;

    private Set<AdvantagesDisadvantages> advantagesDisadvantages;

    private Set<Peculiarity> peculiaritys;

    private Set<Expertise> expertises;

    public Character(String id, Long dx, Long st, Long ht, Long iq, String gamer, String description, Set<AdvantagesDisadvantages> advantagesDisadvantages, Set<Peculiarity> peculiaritys, Set<Expertise> expertises) {
        this.id = id;
        this.dx = dx;
        this.st = st;
        this.ht = ht;
        this.iq = iq;
        this.gamer = gamer;
        this.description = description;
        this.advantagesDisadvantages = advantagesDisadvantages;
        this.peculiaritys = peculiaritys;
        this.expertises = expertises;
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "id='" + id + '\'' +
                ", dx=" + dx +
                ", st=" + st +
                ", ht=" + ht +
                ", iq=" + iq +
                ", gamer='" + gamer + '\'' +
                ", descricao='" + description + '\'' +
                ", vantagensDesvantagens=" + advantagesDisadvantages +
                ", peculariedades=" + peculiaritys +
                ", pericias=" + expertises +
                '}';
    }
}
