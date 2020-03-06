package tests.service;

import dao.AuthTokenDao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import models.AuthToken;
import models.Event;
import models.Person;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ClearService;

import static org.junit.jupiter.api.Assertions.*;

class ClearServiceTest {

	@Test
	void test_positive() {
		Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));

		Event e = new Event("test_id", "test_username", "test_person_id",
				"latitude","longitude","country", "city", "type", "year");
		Assertions.assertTrue(EventDao.addEvent(e));

		Person p = new Person("test_id", "test_username", "test_first", "test_last", "test_gender");
		Assertions.assertTrue(PersonDao.addPerson(p));

		User u = new User("test_id", "test_name", "test_password","test_email", "test_first", "test_last", "test_gender");
		Assertions.assertTrue(UserDao.addUser(u));

		ClearService.clear();

		Assertions.assertNull(AuthTokenDao.getUserFromToken("test_token"));
		Assertions.assertNull(EventDao.getEvent(e.eventID));
		Assertions.assertNull(PersonDao.getPerson(p.personID));
		Assertions.assertNull(UserDao.getUser(u.userName));
	}

	@Test
	void test_positive2() {
		Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));

		Event e = new Event("test_id", "test_username", "test_person_id",
				"latitude","longitude","country", "city", "type", "year");
		Assertions.assertTrue(EventDao.addEvent(e));

		ClearService.clear();

		Assertions.assertNull(AuthTokenDao.getUserFromToken("test_token"));
		Assertions.assertNull(EventDao.getEvent(e.eventID));
	}

}