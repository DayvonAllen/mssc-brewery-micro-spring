package com.example.beerorderservice.web.mapper;

import com.example.beerorderservice.domain.BeerOrder;
import com.example.beerorderservice.domain.BeerOrderLine;
import com.example.beerorderservice.domain.Customer;
import com.example.beerorderservice.repos.CustomerRepository;
import com.example.beerorderservice.web.model.BeerOrderDto;
import com.example.beerorderservice.web.model.BeerOrderLineDto;
import com.example.beerorderservice.web.model.OrderStatusEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BeerOrderMapperImpl implements  BeerOrderMapper {

    private final BeerOrderLineMapper orderLineMapper;
    private final CustomerRepository customerRepository;
    private final DateMapper dateMapper;

    public BeerOrderMapperImpl(BeerOrderLineMapper orderLineMapper, CustomerRepository customerRepository, DateMapper dateMapper) {
        this.orderLineMapper = orderLineMapper;
        this.customerRepository = customerRepository;
        this.dateMapper = dateMapper;
    }

    @Override
    public BeerOrderDto beerOrderToDto(BeerOrder beerOrder) {
        if(beerOrder == null) {
            return null;
        }
        List<BeerOrderLineDto> beerOrderLineDtos = beerOrder
                .getBeerOrderLines()
                .stream()
                .map(orderLineMapper::beerOrderLineToDto)
                .collect(Collectors.toList());

        return BeerOrderDto
                .builder()
                .id(beerOrder.getId())
                .version(Integer.parseInt(beerOrder.getVersion().toString()))
                .customerId(beerOrder.getCustomer().getId())
                .beerOrderLines(beerOrderLineDtos)
                .customerRef(beerOrder.getCustomerRef())
                .orderStatus(OrderStatusEnum.NEW)
                .orderStatusCallbackUrl(beerOrder.getOrderStatusCallbackUrl())
                .createdDate(dateMapper.asOffsetDateTime(beerOrder.getCreatedDate()))
                .lastModifiedDate(dateMapper.asOffsetDateTime(beerOrder.getLastModifiedDate()))
                .build();
    }

    @Override
    public BeerOrder dtoToBeerOrder(BeerOrderDto dto) {
        if(dto == null) {
            return null;
        }

        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(RuntimeException::new);

        Set<BeerOrderLine> beerOrderLines = dto
                .getBeerOrderLines()
                .stream()
                .map(orderLineMapper::dtoToBeerOrderLine)
                .collect(Collectors.toSet());

        return BeerOrder
                .builder()
                .id(dto.getId())
                .customerRef(dto.getCustomerRef())
                .customer(customer)
                .orderStatus(com.example.beerorderservice.domain.OrderStatusEnum.NEW)
                .beerOrderLines(beerOrderLines)
                .createdDate(dateMapper.asTimestamp(dto.getCreatedDate()))
                .lastModifiedDate(dateMapper.asTimestamp(dto.getLastModifiedDate()))
                .build();
    }
}
