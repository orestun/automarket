package com.automarket.repository;

import com.automarket.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    Page<Vehicle> findAll(Pageable pageable);

    boolean existsByVINCode(String VINCode);
}
