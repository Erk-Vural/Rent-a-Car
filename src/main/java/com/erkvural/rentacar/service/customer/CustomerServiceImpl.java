package com.erkvural.rentacar.service.customer;

import com.erkvural.rentacar.entity.customer.Customer;
import com.erkvural.rentacar.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer getById(long id) {
        return repository.findByUserId(id);
    }
}
