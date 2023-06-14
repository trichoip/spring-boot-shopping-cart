package com.demo.service;

import com.demo.domain.Customer;
import com.demo.exception.ResourceAlreadyExistsException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.CustomerRepository;
import com.demo.service.dto.CustomerDto;
import com.demo.service.mapper.CustomerMapper;
import com.demo.web.vm.CustomerVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final GenderService genderService;
    private final RankService rankService;

    public CustomerDto createCustomer(CustomerVM customerVM) {
        if (customerRepository.existsByTelephone(customerVM.telephone())) {
            throw new ResourceAlreadyExistsException("Customer with telephone " + customerVM.telephone() + " already existed");
        }
        Customer customer = customerMapper.toEntity(customerVM, genderService);
        customer.setRank(rankService.getRankByCustomerPoint(customer.getPoint()));
        return customerMapper.toDto(customerRepository.save(customer));
    }

    public CustomerDto getCustomerByTelephone(String telephone) {
        Customer customer = customerRepository
                .findByTelephone(telephone)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with telephone " + telephone + " not found"));
        return customerMapper.toDto(customer);
    }

    public CustomerDto updateCustomer(Long id, CustomerVM customerVM) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));
        if (!customerVM.telephone().equals(customer.getTelephone()) &&
                customerRepository.existsByTelephone(customerVM.telephone())) {
            throw new ResourceAlreadyExistsException("Customer with telephone " + customerVM.telephone() + " already existed");
        }
        customerMapper.map(customerVM, customer, genderService);
        return customerMapper.toDto(customerRepository.save(customer));
    }
}
