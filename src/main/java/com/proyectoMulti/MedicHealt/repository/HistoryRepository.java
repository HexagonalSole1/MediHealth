package com.proyectoMulti.MedicHealt.repository;

import com.proyectoMulti.MedicHealt.entity.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface HistoryRepository extends CrudRepository<History,Long> {
    Optional<History> findByPatientId(Long patientId);

}
