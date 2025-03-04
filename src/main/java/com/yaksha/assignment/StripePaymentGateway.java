package com.yaksha.assignment;

public class StripePaymentGateway implements PaymentGateway {

	@Override
	public void processPayment() {
		System.out.println("Processing payment through Stripe...");
	}
}
