package com.springbootacadamy.pointofsale.service.impl;

import com.springbootacadamy.pointofsale.dto.CustomerDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerUpdateQueryDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.springbootacadamy.pointofsale.dto.response.CustomerResponse;
import com.springbootacadamy.pointofsale.entity.Customer;
import com.springbootacadamy.pointofsale.repository.CustomerRepo;
import com.springbootacadamy.pointofsale.service.CustomerService;
import com.springbootacadamy.pointofsale.utils.mappers.CustomerMapper;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String addCustomer(CustomerSaveRequestDTO customerSaveRequestDTO) {
        Customer customer = new Customer(
                1,
                customerSaveRequestDTO.getCustomerName(),
                customerSaveRequestDTO.getCustomerAddress(),
                customerSaveRequestDTO.getSalary(),
                customerSaveRequestDTO.getContactNumbers(),
                customerSaveRequestDTO.getNic(),
                false

        );

        if(!customerRepo.existsById(customer.getCustomerId())){
            customerRepo.save(customer);
            return customerSaveRequestDTO.getCustomerName() + " saved";
        }else{
            System.out.println("customer already in there");
            return "customer already in there";
        }
    }

    @Override
    public String updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        System.out.println(customerUpdateRequestDTO.getCustomerId());
        if(customerRepo.existsById(customerUpdateRequestDTO.getCustomerId())){
            Customer customer = customerRepo.getById(customerUpdateRequestDTO.getCustomerId());

            customer.setCustomerName(customerUpdateRequestDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateRequestDTO.getCustomerAddress());
            customer.setContactNumbers(customerUpdateRequestDTO.getContactNumbers());
            customer.setSalary(customerUpdateRequestDTO.getSalary());
            customer.setNic(customerUpdateRequestDTO.getNic());
            customer.setActiveState(customerUpdateRequestDTO.isActiveState());

            customerRepo.save(customer);
        }else {
            System.out.println("this customer not in database");
        }

        return "complete";
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isPresent()){
            /*CustomerDTO customerDTO = new CustomerDTO(
                    customer.get().getCustomerId(),
                    customer.get().getCustomerName(),
                    customer.get().getCustomerAddress(),
                    customer.get().getSalary(),
                    customer.get().getContactNumbers(),
                    customer.get().getNic(),
                    customer.get().isActiveState()
            );*/
//            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            CustomerDTO customerDTO = customerMapper.entityToDto(customer.get());
            return customerDTO;
        }else{
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
       // List<CustomerDTO> customerDTOS = new ArrayList<>();

        /*for(Customer c: customers){
            CustomerDTO customerDTO = new CustomerDTO(
                    c.getCustomerId(),
                    c.getCustomerName(),
                    c.getCustomerAddress(),
                    c.getSalary(),
                    c.getContactNumbers(),
                    c.getNic(),
                    c.isActiveState()
            );

            customerDTOS.add(customerDTO);
        }*/

        List<CustomerDTO> customerDTOS = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>(){}.getType());

        return customerDTOS;
    }

    @Override
    public String deleteCustomer(int id) throws NotFoundException {
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id);
        }else{
            throw new NotFoundException("not found customer for this id");
        }
        return null;
    }

    @Override
    public List<CustomerDTO> getCustomerByName(String customerName) throws NotFoundException {
        List<Customer> customers = customerRepo.getCustomersByCustomerNameEquals(customerName);
        if(customers.size() != 0){
            List<CustomerDTO> customerDTOS = modelMapper.map(customers, new TypeToken<List<CustomerDTO>>(){}.getType());
            return customerDTOS;
        }else {
            throw new NotFoundException("not found a customer by this name");
        }
    }

    @Override
    public String updateCustomerByQuery(CustomerUpdateQueryDTO customerUpdateQueryDTO, int id) {
        if(customerRepo.existsById(id)){
            customerRepo.updateCustomerByQuery(customerUpdateQueryDTO.getCustomerName(), customerUpdateQueryDTO.getNic(), id);
            return "updated";
        }else{
            System.out.println("update failure");
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAllActiveCustomers(boolean b) {
        List<Customer> customer = customerRepo.findCustomerByActiveStateEquals(b);
        List<CustomerDTO> customerDTOS = customerMapper.entityListToDtoList(customer);

        return customerDTOS;
    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        /* Customer customer = customerRepo.findCustomerByNicEquals(nic);
       if(customer != null){
            CustomerDTO customerDTO = customerMapper.entityToDto(customer);
            return customerDTO;
        }
        return null;*/


        Optional<Customer> customer = customerRepo.findByNicEquals(nic);
        if(customer.isPresent()){
            CustomerDTO customerDTO = modelMapper.map(customer.get(),CustomerDTO.class);
            return  customerDTO;
        }else{
            throw new com.springbootacadamy.pointofsale.exceptions.NotFoundException("not found hjghjhgjg");
        }
    }

    @Override
    public CustomerResponse getCustomerNewResponseById(int id) {
        Customer customer = customerRepo.findCustomerByCustomerId(id);
        System.out.println(customer);
        CustomerResponse customerResponse = customerMapper.entityToResponse(customer);
        System.out.println("------------------------------");
        System.out.println(customerResponse);
        if(customerResponse != null){
            return customerResponse;
        }else{
            return null;
        }
    }
}

