package com.proyectoMulti.MedicHealt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class History {

    @Id
    @Column(name = "idHistory")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String observation;

    private Date date;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @JsonIgnore
    @OneToMany(targetEntity = BiometricsData.class,fetch = FetchType.EAGER,mappedBy = "history",cascade = CascadeType.REMOVE)
    private List<BiometricsData> biometricsDataList;


}
