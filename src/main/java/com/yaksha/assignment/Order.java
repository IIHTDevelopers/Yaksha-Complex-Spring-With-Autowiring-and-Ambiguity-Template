package com.yaksha.assignment;

public class Order {

	private PaymentGateway paymentGateway;

	// Setter method for autowiring the correct PaymentGateway
	public void setPaymentGateway(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public void processOrder() {
		// Use the paymentGateway to process the order
		paymentGateway.processPayment();
	}
}
