package com.esjo.tdddemo.payment.adapter;

interface PaymentGateway {
    void execute(int totalPrice, String cardNumber);

}
