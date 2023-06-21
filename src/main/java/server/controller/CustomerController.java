package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import server.CustomerRepository;
import server.model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "v1/customer")
@Service
public class CustomerController {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return this.customerRepository.findAll();
    }

    record NewCustomerRequest(
            String firstName,
            String lastName,
            LocalDate dob,
            Integer balance
    ){}

    @PostMapping
    public void createCustomer(@RequestBody NewCustomerRequest newCustomerRequest){
        Customer customer = new Customer();
        customer.setFirstName(newCustomerRequest.firstName());
        customer.setLastName(newCustomerRequest.lastName());
        customer.setDob(newCustomerRequest.dob());
        customer.setBalance(newCustomerRequest.balance());
        this.customerRepository.save(customer);
    }

    @PutMapping("{id}")
    public void updateCustomer(@PathVariable("id") Long id,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) LocalDate dob,
                               @RequestParam(required = false) Integer balance){
        Optional<Customer> customer = this.customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer existingCustomer = customer.get();

            if (firstName != null) {
                existingCustomer.setFirstName(firstName);
            }

            if (lastName != null) {
                existingCustomer.setLastName(lastName);
            }

            if (dob != null) {
                existingCustomer.setDob(dob);
            }
            if (balance != null) {
                existingCustomer.setBalance(balance);
            }
            this.customerRepository.save(existingCustomer);
        } else {
            // Handle the case when the customer with the provided id is not found
            throw new IllegalStateException("Customer not found with id: " + id);
        }

    }
    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        this.customerRepository.deleteById(id);
    }
}
