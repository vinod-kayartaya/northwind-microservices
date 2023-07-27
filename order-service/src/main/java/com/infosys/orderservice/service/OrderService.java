package com.infosys.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.infosys.orderservice.entity.LineItem;
import com.infosys.orderservice.entity.Order;
import com.infosys.orderservice.model.CustomerDTO;
import com.infosys.orderservice.model.LineItemDTO;
import com.infosys.orderservice.model.OrderDTO;
import com.infosys.orderservice.model.ProductRecord;
import com.infosys.orderservice.model.ShipperRecord;
import com.infosys.orderservice.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Value("${shipper.service-url}")
	private String shipperServiceUrl;
	
	@Value("${customer.service-url}")
	private String customerServiceUrl;
	
	@Value("${product.service-url}")
	private String productServiceUrl;
		
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	public OrderDTO getOrderById(Integer orderId) {
		Optional<Order> result = repo.findById(orderId);
		
		if(result.isEmpty()) {
			return null;
		}
		
		OrderDTO dto = new OrderDTO();
		Order order = result.get();
		BeanUtils.copyProperties(order, dto);
		
		try {
			List<LineItemDTO> lineItemDTOs = order.getLineItems()
				.stream()
				.map(this::toLineItemDTO)
				.toList();
			dto.setLineItems(lineItemDTOs);
		}
		catch(Exception ex) {
			log.warn("", ex);
		}
		
		// get the shipper data from the shipper-service and add to dto
		try {
			ShipperRecord shipper = webClientBuilder.build()
				.get()
				.uri(shipperServiceUrl + order.getShipperId())
				.retrieve()
				.bodyToMono(ShipperRecord.class)
				.block();
			dto.setShipper(shipper);
		}
		catch(Exception e) {
			// duck and log the exception
			log.warn("Error while trying to get Shipper data", e);
		}
		try {
			CustomerDTO customer = webClientBuilder.build()
				.get()
				.uri(customerServiceUrl + order.getCustomerId())
				.retrieve()
				.bodyToMono(CustomerDTO.class)
				.block();
			dto.setCustomer(customer);
		}
		catch(Exception e) {
			// duck and log the exception
			log.warn("Error while trying to get Customer data", e);
		}
		
		return dto;
			
	}
	
	LineItemDTO toLineItemDTO(LineItem li) {
		LineItemDTO dto = new LineItemDTO();
		BeanUtils.copyProperties(li, dto);
		try {
			ProductRecord product = webClientBuilder.build()
				.get()
				.uri(productServiceUrl +li.getProductId())
				.retrieve()
				.bodyToMono(ProductRecord.class)
				.block();
			dto.setProduct(product);
		}
		catch(Exception e) {
			// duck and log the exception
			log.warn("Error while trying to get Customer data", e);
		}
		return dto;
	}
}
