package com.infosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.entity.Shipper;

public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

}
