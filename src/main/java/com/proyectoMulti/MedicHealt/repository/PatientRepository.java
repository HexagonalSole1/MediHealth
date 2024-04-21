package com.proyectoMulti.MedicHealt.repository;

import com.proyectoMulti.MedicHealt.entity.Patient;
import com.proyectoMulti.MedicHealt.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PatientRepository extends CrudRepository<Patient,Long> {


}
