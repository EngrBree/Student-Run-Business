package com.bree.customermanage.Service;

import com.bree.customermanage.Model.Customer;
import com.bree.customermanage.Model.CustomerRequest;
import com.bree.customermanage.Model.RequestType;
import com.bree.customermanage.Repository.CustomerRepository;
import com.bree.customermanage.Repository.CustomerRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CustomerService {
    private  final CustomerRepository customerRepository;
    CustomerRequestRepository customerRequestRepository;
    private WebClient OrderWebclient;

    public CustomerService(CustomerRepository customerRepository,WebClient.Builder webClientBuilder) {
        this.customerRepository = customerRepository;
        this.OrderWebclient=webClientBuilder.baseUrl("http://localhost:8081/OrderManagement").build();
    }
    public Mono<Customer> addCustomer(Customer customer){
        return  customerRepository.save(customer);
    }
    public Mono<Customer> retrieveCustomerById(String customerId){
        return customerRepository.findById(customerId);
    }
    public Flux<Customer> retrieveAllCustomers(){
        return customerRepository.findAll();
    }
    public Mono<Void>deleteCustomer(String customerId){
        return customerRepository.deleteById(customerId);
    }


    public Mono<String> handleRequest(CustomerRequest request) {
        RequestType requestType = request.getRequestType();

        if (requestType == RequestType.INQUIRY) {
            return handleInquiry(request);
        } else if (requestType == RequestType.COMPLAINT) {
            return handleComplaint(request);
        } else if (requestType == RequestType.OTHER_REQUEST) {
            return handleCustomerRequest(request);
        } else {
            return Mono.error(new IllegalArgumentException("Unsupported request type"));
        }

    }

    private Mono<String> handleInquiry(CustomerRequest request) {

        return Mono.just("Inquiry Response: Inquiry received. Wait for the response");
    }

    private Mono<String> handleComplaint(CustomerRequest request) {

        return Mono.just("Complaint Acknowledgment: Thank you for your complaint. We will investigate and take appropriate actions.");
    }

    private Mono<String> handleCustomerRequest(CustomerRequest request) {

        return Mono.just("Request Confirmation: Your request has been received and will be processed.");
    }
}
