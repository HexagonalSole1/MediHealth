package com.proyectoMulti.MedicHealt.repository;

import com.proyectoMulti.MedicHealt.entity.BiometricsData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BiometricsDataRepository extends CrudRepository<BiometricsData,Long> {
}
