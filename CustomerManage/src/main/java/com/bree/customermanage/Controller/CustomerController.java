package com.bree.customermanage.Controller;

import com.bree.customermanage.Model.Customer;
import com.bree.customermanage.Model.CustomerRequest;
import com.bree.customermanage.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    //@Autowired
 private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/{customerId}")
    public Mono<Customer> retrieveCustomerById(@PathVariable String customerId) {
        return customerService.retrieveCustomerById(customerId);
    }

    @GetMapping
    public Flux<Customer> retrieveAllCustomers() {
        return customerService.retrieveAllCustomers();
    }

    @DeleteMapping("/{customerId}")
    public Mono<Void> deleteCustomer(@PathVariable String customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @PostMapping("/handle-request")
    public Mono<String> handleRequest(@RequestBody CustomerRequest request) {
        return customerService.handleRequest(request);
    }

}
