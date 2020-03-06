package tests.dao_operations;

import dao.UserDao;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDaoTest {

	User u = new User("test_id", "test_name", "test_password","test_email", "test_first", "test_last", "test_gender");

	@BeforeEach
	void before() {
		UserDao.clear();
	}

	@Test
	void insertion_positive() {
		Assertions.assertTrue(UserDao.addUser(u));
		Assertions.assertNotNull(UserDao.getUser(u.userName));
	}
	@Test
	void insertion_negative() {
		User invalid = null;
		Assertions.assertFalse(UserDao.addUser(invalid));
	}

	@Test
	void retrieval_positive() {
		Assertions.assertTrue(UserDao.addUser(u));
		Assertions.assertNotNull(UserDao.getUser(u.userName));
	}
	@Test
	void retrieval_negative() {
		Assertions.assertTrue(UserDao.addUser(u));
		Assertions.assertNull(UserDao.getUser("invalid_id"));
	}

	@Test
	void clear() {
		Assertions.assertTrue(UserDao.addUser(u));
		UserDao.clear();
		Assertions.assertNull(UserDao.getUser(u.userName));
	}

}