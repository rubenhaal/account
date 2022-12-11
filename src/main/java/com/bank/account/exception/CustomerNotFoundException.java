package com.bank.account.exception;

public class CustomerNotFoundException extends GeneralAccException{
    public CustomerNotFoundException(){
        super("Customer Not Found");
    }
}
