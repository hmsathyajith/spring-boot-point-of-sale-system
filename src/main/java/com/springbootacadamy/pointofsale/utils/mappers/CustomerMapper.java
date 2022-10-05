package com.springbootacadamy.pointofsale.utils.mappers;

import com.springbootacadamy.pointofsale.dto.CustomerDTO;
import com.springbootacadamy.pointofsale.dto.response.CustomerResponse;
import com.springbootacadamy.pointofsale.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO entityToDto(Customer customer);

    List<CustomerDTO> entityListToDtoList(List<Customer> customers);

    CustomerResponse entityToResponse(Customer customer);

}
