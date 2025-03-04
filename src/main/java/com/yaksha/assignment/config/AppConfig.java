package com.yaksha.assignment.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.yaksha.assignment.Order;
import com.yaksha.assignment.PayPalPaymentGateway;
import com.yaksha.assignment.PaymentGateway;
import com.yaksha.assignment.StripePaymentGateway;

@Configuration
public class AppConfig {

	@Bean
	@Scope("singleton")
	public PaymentGateway payPalPaymentGateway() {
		return new PayPalPaymentGateway();
	}

	@Bean
	@Scope("singleton")
	public PaymentGateway stripePaymentGateway() {
		return new StripePaymentGateway();
	}

	@Bean
	public Order order(@Qualifier("payPalPaymentGateway") PaymentGateway paymentGateway) {
		Order order = new Order();
		order.setPaymentGateway(paymentGateway); // Autowire with PayPalPaymentGateway
		return order;
	}
}
