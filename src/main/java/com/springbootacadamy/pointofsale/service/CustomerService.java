package com.springbootacadamy.pointofsale.service;

import com.springbootacadamy.pointofsale.dto.CustomerDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerUpdateQueryDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.springbootacadamy.pointofsale.dto.response.CustomerResponse;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO);

    String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(int id) throws NotFoundException;

    List<CustomerDTO> getCustomerByName(String customerName) throws NotFoundException;

    String updateCustomerByQuery(CustomerUpdateQueryDTO customerUpdateQueryDTO,int id);

    List<CustomerDTO> getAllActiveCustomers(boolean b);

    CustomerDTO getCustomerByNic(String nic);

    CustomerResponse getCustomerNewResponseById(int id);
}
