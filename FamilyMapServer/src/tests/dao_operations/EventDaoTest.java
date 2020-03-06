package tests.dao_operations;

import dao.EventDao;
import models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventDaoTest {

	@BeforeEach
	void before() {
		EventDao.clear();
	}

	@Test
	void insertion_positive() {
		Event e = new Event("test_id", "test_username", "test_person_id",
				"latitude","longitude","country", "city", "type", "year");
		Assertions.assertTrue(EventDao.addEvent(e));
	}
	@Test
	void insertion_negative() {
		Event invalid = null;
		Assertions.assertFalse(EventDao.addEvent(invalid));
	}

	@Test
	void retrieval_positive() {
		Event e = new Event("test_id", "test_username", "test_person_id",
				"latitude","longitude","country", "city", "type", "year");
		Assertions.assertTrue(EventDao.addEvent(e));
		Assertions.assertNotNull(EventDao.getEvent(e.eventID));
	}
	@Test
	void retrieval_negative() {
		Event e = new Event("test_id", "test_username", "test_person_id",
				"latitude","longitude","country", "city", "type", "year");
		Assertions.assertTrue(EventDao.addEvent(e));
		Assertions.assertNull(EventDao.getEvent("invalid_id"));
	}

	@Test
	void clear() {
		Event e = new Event("test_id", "test_username", "test_person_id",
				"latitude","longitude","country", "city", "type", "year");
		Assertions.assertTrue(EventDao.addEvent(e));
		EventDao.clear();
		Assertions.assertNull(EventDao.getEvent("test_id"));
	}

}