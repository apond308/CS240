package tests.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.LoginRequest;
import requests.RegisterRequest;
import services.ClearService;
import services.LoginService;
import services.RegisterService;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

	@Test
	void test(){
		Assertions.assertTrue(ClearService.clear().success);
		RegisterRequest register_request = new RegisterRequest();
		register_request.userName = "test_username";
		register_request.firstName = "test";
		register_request.lastName = "username";
		register_request.gender = "m";
		register_request.email = "asdf@gmail.com";
		register_request.password = "password";
		Assertions.assertTrue(RegisterService.register(register_request).success);

		LoginRequest login_request = new LoginRequest();
		login_request.userName = "test_username";
		login_request.password = "password";
		Assertions.assertTrue(LoginService.login(login_request).success);
	}

}