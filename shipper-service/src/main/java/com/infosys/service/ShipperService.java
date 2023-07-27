package com.infosys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.dto.ShipperRecord;
import com.infosys.entity.Shipper;
import com.infosys.repository.ShipperRepository;

@Service
public class ShipperService {

	@Autowired
	private ShipperRepository repo;

	private static Shipper toShipper(ShipperRecord shipperRecord) {
		Shipper shipper = new Shipper();
		BeanUtils.copyProperties(shipperRecord, shipper);
		return shipper;
	}

	private static ShipperRecord toShipperRecord(Shipper shipper) {
		return new ShipperRecord(shipper.getShipperId(), shipper.getCompanyName(), shipper.getPhone());
	}
	
	
	public ShipperRecord getShipperById(Integer id) {
		Optional<Shipper> result = repo.findById(id);
		if(result.isEmpty()) {
			return null;
		}
		
		return toShipperRecord(result.get());
	}
	
	public List<ShipperRecord> getAllShippers(){
		return repo.findAll() 							// List<Shipper> 
				.stream()								// Stream<Shipper>
				.map(ShipperService::toShipperRecord)	// Stream<ShipperRecord>
				.toList();								// List<ShipperRecord>
	}
	
	public ShipperRecord addNewShipper(ShipperRecord shipperRecord) {
		Shipper shipper = toShipper(shipperRecord);
		shipper.setShipperId(null); // remove the id, so that hibernate will generate a new one when repo.save(..) called
		Shipper savedShipper = repo.save(shipper);
		return toShipperRecord(savedShipper);
	}
}
