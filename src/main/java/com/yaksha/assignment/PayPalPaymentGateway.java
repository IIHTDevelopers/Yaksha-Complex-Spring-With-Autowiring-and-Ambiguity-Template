package com.yaksha.assignment;

public class PayPalPaymentGateway implements PaymentGateway {

	@Override
	public void processPayment() {
		System.out.println("Processing payment through PayPal...");
	}
}
