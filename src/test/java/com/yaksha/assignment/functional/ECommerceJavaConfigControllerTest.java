package com.yaksha.assignment.functional;

import static com.yaksha.assignment.utils.TestUtils.businessTestFile;
import static com.yaksha.assignment.utils.TestUtils.currentTest;
import static com.yaksha.assignment.utils.TestUtils.testReport;
import static com.yaksha.assignment.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yaksha.assignment.Order;
import com.yaksha.assignment.PayPalPaymentGateway;
import com.yaksha.assignment.PaymentGateway;
import com.yaksha.assignment.StripePaymentGateway;
import com.yaksha.assignment.config.AppConfig;
import com.yaksha.assignment.utils.CustomParser;

public class ECommerceJavaConfigControllerTest {

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	// Test if the Spring context loads the beans correctly from Java configuration
	@Test
	public void testApplicationContextLoads() throws IOException {
		// Load the context using Java-based configuration
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Retrieve beans from the context by type to avoid ambiguity
		PaymentGateway payPalGateway = context.getBean(PayPalPaymentGateway.class);
		PaymentGateway stripeGateway = context.getBean(StripePaymentGateway.class);
		Order order = context.getBean(Order.class);

		// Assert that beans are created
		boolean payPalLoaded = payPalGateway != null;
		boolean stripeLoaded = stripeGateway != null;
		boolean orderLoaded = order != null;

		// Log the checks
		System.out.println("PayPal PaymentGateway bean loaded: " + payPalLoaded);
		System.out.println("Stripe PaymentGateway bean loaded: " + stripeLoaded);
		System.out.println("Order bean loaded: " + orderLoaded);

		// Auto-grading with yakshaAssert
		yakshaAssert(currentTest(), payPalLoaded && stripeLoaded && orderLoaded, businessTestFile);
	}

	// Test if AppConfig class contains @Configuration annotation
	@Test
	public void testAppConfigHasConfiguration() throws IOException {
		String filePath = "src/main/java/com/yaksha/assignment/config/AppConfig.java"; // Path to your AppConfig.java

		try {
			// Check if @Configuration annotation is present at class level
			boolean result = CustomParser.checkClassAnnotation(filePath, "Configuration");

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), result, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test if Order bean is available and @Qualifier annotation is used with the
	// correct value ("payPalPaymentGateway")
	@Test
	public void testOrderBeanAvailabilityAndQualifierUsage() throws IOException {
		String filePath = "src/main/java/com/yaksha/assignment/config/AppConfig.java"; // Path to your AppConfig.java

		try {
			// Load the context using Java-based configuration
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

			// Retrieve the Order bean from the context
			Order order = context.getBean(Order.class);

			// Assert that the Order bean is loaded
			boolean orderBeanLoaded = order != null;

			// Check if the method "order" has a parameter annotated with @Qualifier and the
			// value "payPalPaymentGateway"
			boolean qualifierUsedCorrectly = CustomParser.checkMethodParameterAnnotationWithValue(filePath, "order",
					"Qualifier", "payPalPaymentGateway");

			// Log the results
			System.out.println("Order bean loaded: " + orderBeanLoaded);
			System.out
					.println("Qualifier annotation used with value 'payPalPaymentGateway': " + qualifierUsedCorrectly);

			// Auto-grading with yakshaAssert
			yakshaAssert(currentTest(), orderBeanLoaded && qualifierUsedCorrectly, businessTestFile);
		} catch (Exception e) {
			e.printStackTrace();
			yakshaAssert(currentTest(), false, businessTestFile);
		}
	}

	// Test to check if 1 Order and 2 PaymentGateway beans are present in the
	// AppConfig
	@Test
	public void testBeansPresenceInAppConfig() throws IOException {
		// Load the context using Java-based configuration
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Retrieve beans from the context
		PaymentGateway payPalGateway = context.getBean(PayPalPaymentGateway.class);
		PaymentGateway stripeGateway = context.getBean(StripePaymentGateway.class);
		Order order = context.getBean(Order.class);

		// Assert the presence of beans
		boolean payPalLoaded = payPalGateway != null;
		boolean stripeLoaded = stripeGateway != null;
		boolean orderLoaded = order != null;

		// Verify that exactly one instance of each PaymentGateway bean exists
		boolean correctNumberOfGateways = payPalLoaded && stripeLoaded;

		// Log the checks for debugging
		System.out.println("PayPal PaymentGateway bean loaded: " + payPalLoaded);
		System.out.println("Stripe PaymentGateway bean loaded: " + stripeLoaded);
		System.out.println("Order bean loaded: " + orderLoaded);
		System.out.println("Correct number of PaymentGateway beans present: " + correctNumberOfGateways);

		// Auto-grading with yakshaAssert
		yakshaAssert(currentTest(), payPalLoaded && stripeLoaded && orderLoaded && correctNumberOfGateways,
				businessTestFile);
	}

	// Test to check if the PaymentGateway beans are present with correct scope
	// values (singleton)
	@Test
	public void testPaymentGatewayBeansScope() throws IOException {
		// Load the context using Java-based configuration
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// Retrieve beans from the context by type
		PaymentGateway payPalGateway = context.getBean(PayPalPaymentGateway.class);
		PaymentGateway stripeGateway = context.getBean(StripePaymentGateway.class);

		// Assert the presence of beans
		boolean payPalLoaded = payPalGateway != null;
		boolean stripeLoaded = stripeGateway != null;

		// Verify the scope of the PaymentGateway beans (Singleton vs Prototype)
		boolean payPalSingletonScope = context.getBeanFactory().getBeanDefinition("payPalPaymentGateway").getScope()
				.equals("singleton");
		boolean stripeSingletonScope = context.getBeanFactory().getBeanDefinition("stripePaymentGateway").getScope()
				.equals("singleton");

		// Log the checks for debugging
		System.out.println("PayPal PaymentGateway bean loaded: " + payPalLoaded);
		System.out.println("Stripe PaymentGateway bean loaded: " + stripeLoaded);
		System.out.println("PayPal PaymentGateway scope is Singleton: " + payPalSingletonScope);
		System.out.println("Stripe PaymentGateway scope is Singleton: " + stripeSingletonScope);

		// Auto-grading with yakshaAssert
		yakshaAssert(currentTest(), payPalLoaded && stripeLoaded && payPalSingletonScope && stripeSingletonScope,
				businessTestFile);
	}
}
