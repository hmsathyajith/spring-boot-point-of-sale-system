package com.springbootacadamy.pointofsale.controller;

import com.springbootacadamy.pointofsale.dto.CustomerDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerSaveRequestDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerUpdateQueryDTO;
import com.springbootacadamy.pointofsale.dto.request.CustomerUpdateRequestDTO;
import com.springbootacadamy.pointofsale.dto.response.CustomerResponse;
import com.springbootacadamy.pointofsale.entity.Customer;
import com.springbootacadamy.pointofsale.service.CustomerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerSaveRequestDTO customerSaveRequestDTO){
        String result = customerService.addCustomer(customerSaveRequestDTO);
        return result;
    }

    @PutMapping("/update")
    public String updateCustomer(@RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO){
        System.out.println("in update customer " + customerUpdateRequestDTO.getCustomerId());
        String result = customerService.updateCustomer(customerUpdateRequestDTO);
        return result;
    }

    @GetMapping(path = "/get-customer-by-id" , params = "id")
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

    @GetMapping(path = {"/get-all-customers"})
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        return customerDTOS;
    }

    @DeleteMapping(path = {"/deleteCustomer/{id}"})
    public String deleteCustomer(@PathVariable(value = "id") int id) throws NotFoundException {
        String result = customerService.deleteCustomer(id);
        return "deleted customer " + id;
    }

    @GetMapping(
            path = {"/getCustomerByname"},
            params = {"name"}
    )
    public List<CustomerDTO> getCustomerByName(@RequestParam (value = "name") String customerName) throws NotFoundException {
        List<CustomerDTO> customerDTOS = customerService.getCustomerByName(customerName);
        return customerDTOS;
    }

    @PutMapping("/update-query/{id}")
    public String updateCustomerByQuery(@RequestBody CustomerUpdateQueryDTO customerUpdateQueryDTO,
                                        @PathVariable(value = "id") int id){

        System.out.println("in update customer " + customerUpdateQueryDTO.getCustomerName());
        String result = customerService.updateCustomerByQuery(customerUpdateQueryDTO, id);
        return result;
    }

    @GetMapping("/get-all-active-customers")
    public List<CustomerDTO> getAllActiveCustomers(){
        List<CustomerDTO> customerDTOS = customerService.getAllActiveCustomers(true);
        return  customerDTOS;
    }

    @GetMapping(path = {"/getCustomerByNic"},
            params = {"nic"})
    public CustomerDTO getCustomerByNic(@RequestParam (value = "nic") String nic){
        CustomerDTO customerDTO = customerService.getCustomerByNic(nic);
        if(customerDTO == null){
            System.out.println("no customer fond for this " + nic);
            return null;
        }else{
            return customerDTO;
        }
    }

    @GetMapping(path={"/getCustomerById"},
        params = {"id"})
    public CustomerResponse getCustomerNewResponseById(@RequestParam(value = "id") int id){
        CustomerResponse customerResponse = customerService.getCustomerNewResponseById(id);
        return customerResponse;
    }

    @GetMapping(path = {"/getActiveCustomers"},
          params = {"id"})
    public ResponseEntity getActiveCustomer(@RequestParam(value = "id") int id){
        CustomerDTO customerDTO = customerService.getCustomerById(id);

        if(customerDTO.isActiveState() == false){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("this customer is not active");
        }else{
            return ResponseEntity.ok(customerDTO);
        }
    }
}
