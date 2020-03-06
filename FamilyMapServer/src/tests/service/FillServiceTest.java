package tests.service;

import dao.AuthTokenDao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import models.Event;
import models.Person;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.FillRequest;
import requests.RegisterRequest;
import responses.FillResult;
import services.ClearService;
import services.FillService;
import services.RegisterService;

import static org.junit.jupiter.api.Assertions.*;

class FillServiceTest {

	@Test
	void test_positive() {
		Assertions.assertTrue(ClearService.clear().success);
		RegisterRequest register_request = new RegisterRequest();
		register_request.userName = "test_username";
		register_request.firstName = "test";
		register_request.lastName = "username";
		register_request.gender = "m";
		register_request.email = "asdf@gmail.com";
		register_request.password = "password";
		Assertions.assertTrue(RegisterService.register(register_request).success);

		FillRequest fill_request = new FillRequest(register_request.userName, 4);
		Assertions.assertTrue(FillService.fill(fill_request).success);
	}

	@Test
	void test_negative() {
		Assertions.assertTrue(ClearService.clear().success);
		RegisterRequest register_request = new RegisterRequest();
		register_request.userName = "test_username";
		register_request.firstName = "test";
		register_request.lastName = "username";
		register_request.gender = "m";
		register_request.email = "asdf@gmail.com";
		register_request.password = "password";
		Assertions.assertTrue(RegisterService.register(register_request).success);

		FillRequest fill_request = new FillRequest(register_request.userName, 0);
		Assertions.assertFalse(FillService.fill(fill_request).success);
	}

}