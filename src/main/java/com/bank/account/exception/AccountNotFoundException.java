package com.bank.account.exception;

public class AccountNotFoundException extends GeneralAccException{
    public AccountNotFoundException(){
        super("Account not found");
    }
}
