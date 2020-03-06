package tests.service;

import models.Event;
import models.Person;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import requests.LoadRequest;
import requests.RegisterRequest;
import services.ClearService;
import services.LoadService;
import services.RegisterService;


class LoadServiceTest {

	@Test
	void test_positive() {
		Assertions.assertTrue(ClearService.clear().success);

		LoadRequest load_request = new LoadRequest();

		load_request.users.add(new User("test_person_id0", "test_username0", "test_password0","test_email0", "test_first0", "test_last0", "test_gender0"));
		load_request.users.add(new User("test_person_id1", "test_username1", "test_password1","test_email1", "test_first1", "test_last1", "test_gender1"));
		load_request.users.add(new User("test_person_id2", "test_username2", "test_password2","test_email2", "test_first2", "test_last2", "test_gender2"));

		load_request.events.add(new Event("test_id0", "test_username0", "test_person_id0",
				"latitude0","longitude0","country0", "city0", "type0", "year0"));
		load_request.events.add(new Event("test_id1", "test_username1", "test_person_id1",
				"latitude1","longitude1","country1", "city1", "type1", "year1"));
		load_request.events.add(new Event("test_id2", "test_username2", "test_person_id2",
				"latitude2","longitude2","country2", "city2", "type2", "year2"));

		load_request.persons.add(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0"));
		load_request.persons.add(new Person("test_person_id1", "test_username1", "test_first1", "test_last1", "test_gender1"));
		load_request.persons.add(new Person("test_person_id2", "test_username2", "test_first2", "test_last2", "test_gender2"));

		Assertions.assertTrue(LoadService.load(load_request).success);
	}

	@Test
	void test_negative() {
		Assertions.assertTrue(ClearService.clear().success);

		LoadRequest load_request = new LoadRequest();

		load_request.users.add(null);
		load_request.users.add(null);

		Assertions.assertFalse(LoadService.load(load_request).success);
	}

}