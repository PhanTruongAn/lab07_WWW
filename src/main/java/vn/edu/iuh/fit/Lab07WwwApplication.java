package vn.edu.iuh.fit;

import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.enums.ProductStatus;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.models.Employee;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;
import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Lab07WwwApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab07WwwApplication.class, args);
	}
//	@Autowired
//	private ProductRepository productRepository;
//	@Autowired
//			private CustomerRepository customerRepository;
//	@Autowired
//	private EmployeeRepository employeeRepository;
//	    @Bean
	CommandLineRunner createSampleProducts(){
		return args -> {
			//Data Product
//			Faker faker = new Faker();
//			Device device = faker.device();
//			for(int i = 0;i<100;i++) {
//				Product product = new Product(
//						device.modelName(), faker.lorem().paragraph(30),
//						"piece", device.manufacturer(), ProductStatus.ACTIVE
//				);
//				productRepository.save(product);
//			}
            // Data Customer
//			Faker faker = new Faker();
//			List<Customer> customers = new ArrayList<>();
//			for(int i = 0;i<34;i++) {
//				String name = faker.name().fullName();
//				String email = faker.internet().emailAddress();
//				String phone = faker.phoneNumber().cellPhone();
//				String address = faker.address().fullAddress();
//				Customer customer = new Customer(name,email,phone,address);
//				customers.add(customer);
//				customerRepository.saveAll(customers);
//			}
            // Data Employee
//			Faker faker = new Faker();
//			List<Employee> employees = new ArrayList<>();
//			for(int i = 0;i<40;i++) {
//				String name = faker.name().fullName();
//				LocalDate dob = faker.date().birthday().toLocalDateTime().toLocalDate();
//				String email = faker.internet().emailAddress();
//				String phone = faker.phoneNumber().cellPhone();
//				String address = faker.address().fullAddress();
//				Employee employee = new Employee(name,dob,email,phone,address, EmployeeStatus.ACTIVE);
//				employees.add(employee);
//				employeeRepository.saveAll(employees);
//			}
		};
	}
}
