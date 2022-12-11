package com.bank.account.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GeneralAccException extends Exception{


    public GeneralAccException(String message){
        super(message);
    }
}
