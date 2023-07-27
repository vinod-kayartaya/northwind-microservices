package com.infosys.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.entity.Supplier;
import com.infosys.model.SupplierDTO;
import com.infosys.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository repo;

	private SupplierDTO convertToDto(Supplier supplier) {
		SupplierDTO dto = new SupplierDTO();
		BeanUtils.copyProperties(supplier, dto);
		return dto;
	}

	public SupplierDTO getSupplierById(Integer supplierId) {
		Optional<Supplier> result = repo.findById(supplierId);
		if (result.isEmpty()) {
			return null;
		}

		return convertToDto(result.get());
	}
}
