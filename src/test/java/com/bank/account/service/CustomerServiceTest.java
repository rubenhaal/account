package com.bank.account.service;

import com.bank.account.dto.CustomerDto;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.exception.CustomerNotFoundException;
import com.bank.account.model.entity.Customer;
import com.bank.account.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Spy
    ModelMapper modelMapper;

    @Mock
    CustomerRepository customerRepository;



    @Test
    public void WhenCreateCustomer_ThenReturnCustomerCreated(){
        //Given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("TestName");
        customerDto.setSurname("TestSurname");

        Customer customer = new Customer();
        customer.setName("TestName");
        customer.setSurname("TestSurname");

        when(customerRepository.save(any())).thenReturn(customer);
        //When
        CustomerDto result = customerService.createCustomer(customerDto);
        //Then
        assertThat(result.getName()).isEqualTo("TestName");
        assertThat(result.getSurname()).isEqualTo("TestSurname");

        verify(customerRepository, times(1)).save(any());
    }

    @Test
    public void WhenGetCustomer_ThenReturnCustomer() throws CustomerNotFoundException {
        //Given
        Customer  customer = new Customer();
        customer.setSurname("nameTest");
        customer.setName("nameTest");
        when(customerRepository.findById(10L)).thenReturn((Optional.of(customer)));
        //when
        CustomerDto result = customerService.getCustomer(10L);
        //then
        assertThat(result.getName()).isEqualTo("nameTest");
        assertThat(result.getSurname()).isEqualTo("nameTest");

        verify(customerRepository, times(1)).findById(10L);

    }
    @Test
    public void WhenGetCustomerNotInDb_ThenExceptionIsThrown(){
        when(customerRepository.findById(20L)).thenReturn(Optional.empty());
        assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> customerService.getCustomer(20L));
    }
}