package com.yaksha.assignment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yaksha.assignment.config.AppConfig;

public class ECommerceApplication {

	public static void main(String[] args) {
		// Load the Spring context using Java-based configuration
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Retrieve the Order bean from the context
		Order order = context.getBean(Order.class);

		// Process the order using the autowired payment gateway
		order.processOrder(); // Should use PayPalPaymentGateway (as defined in @Qualifier)
	}
}
