package tests.dao_operations;

import dao.PersonDao;
import models.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PersonDaoTest {

	Person p = new Person("test_id", "test_username", "test_first", "test_last", "test_gender");

	@BeforeEach
	void before() {
		PersonDao.clear();
	}

	@Test
	void insertion_positive() {
		Assertions.assertTrue(PersonDao.addPerson(p));
		Assertions.assertNotNull(PersonDao.getPerson(p.personID));
	}
	@Test
	void insertion_negative() {
		Person invalid = null;
		Assertions.assertFalse(PersonDao.addPerson(invalid));
	}

	@Test
	void retrieval_positive() {
		Assertions.assertTrue(PersonDao.addPerson(p));
		Assertions.assertNotNull(PersonDao.getPerson(p.personID));
	}
	@Test
	void retrieval_negative() {
		Assertions.assertTrue(PersonDao.addPerson(p));
		Assertions.assertNull(PersonDao.getPerson("invalid_id"));
	}

	@Test
	void clear() {
		PersonDao.addPerson(p);
		PersonDao.clear();
		Assertions.assertNull(PersonDao.getPerson(p.personID));
	}

}