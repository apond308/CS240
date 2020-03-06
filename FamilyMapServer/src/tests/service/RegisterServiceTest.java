package tests.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.RegisterRequest;
import services.ClearService;
import services.RegisterService;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {

	@Test
	void test_positive(){
		Assertions.assertTrue(ClearService.clear().success);
		RegisterRequest register_request = new RegisterRequest();
		register_request.userName = "test_username";
		register_request.firstName = "test";
		register_request.lastName = "username";
		register_request.gender = "m";
		register_request.email = "asdf@gmail.com";
		register_request.password = "password";
		Assertions.assertTrue(RegisterService.register(register_request).success);
	}

	@Test
	void test_negative(){
		Assertions.assertTrue(ClearService.clear().success);
		RegisterRequest register_request = new RegisterRequest();
		register_request.userName = "test_username";
		register_request.firstName = "test";
		register_request.lastName = "username";
		register_request.gender = "m";
		register_request.email = "asdf@gmail.com";
		register_request.password = "password";
		Assertions.assertTrue(RegisterService.register(register_request).success);

		// Should reject duplicate registration
		Assertions.assertFalse(RegisterService.register(register_request).success);
	}

}