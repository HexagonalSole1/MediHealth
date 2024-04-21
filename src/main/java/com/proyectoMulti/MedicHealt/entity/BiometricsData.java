package com.proyectoMulti.MedicHealt.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BiometricsData {


    @Id
    @Column(name = "idBiometricsData")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private   Float oximeter;

    private Short heartbeat;

    private float temperature;

    @JoinColumn(name = "id_history")
    @ManyToOne(targetEntity = History.class)
    private History history;
}
