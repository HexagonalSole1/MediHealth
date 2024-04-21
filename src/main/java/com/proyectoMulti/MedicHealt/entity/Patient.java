package com.proyectoMulti.MedicHealt.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
    @Id
    @Column(name = "idPatient")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String curp;
    String name;
    String lastName;
    int weight;
    int height;
    short imc;
    String gender;
    short edad;



}
