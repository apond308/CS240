package tests.dao;

import dao.AuthTokenDao;
import dao.EventDao;
import models.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EventDaoTest {

    @BeforeEach
    void before(){
        EventDao.clear();
    }

    @Test
    void addEvent_positive() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id0", "test_username0", "test_person_id0",
                "latitude0","longitude0","country0", "city0", "type0", "year0")));
    }
    @Test
    void addEvent_negative() {
        Assertions.assertFalse(EventDao.addEvent(null));
    }

    @Test
    void getEvent_positive() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id0", "test_username0", "test_person_id0",
                "latitude0","longitude0","country0", "city0", "type0", "year0")));
        Assertions.assertNotNull(EventDao.getEvent("test_id0"));
    }
    @Test
    void getEvent_negative() {
        Assertions.assertNull(EventDao.getEvent("test_id0"));
    }

    @Test
    void getAllEvents_positive() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id0", "test_username0", "test_person_id0",
                "latitude0","longitude0","country0", "city0", "type0", "year0")));
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id1", "test_username1", "test_person_id1",
                "latitude1","longitude1","country1", "city1", "type1", "year1")));
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id2", "test_username2", "test_person_id2",
                "latitude2","longitude2","country2", "city2", "type2", "year2")));
        Assertions.assertNotNull(EventDao.getAllEvents("test_username0"));
    }
    @Test
    void getAllEvents_negative() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id2", "test_username2", "test_person_id2",
                "latitude2","longitude2","country2", "city2", "type2", "year2")));
        Assertions.assertNotNull(EventDao.getAllEvents("test_username0"));
    }

    @Test
    void deleteUserEvents_positive() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id0", "test_username0", "test_person_id0",
                "latitude0","longitude0","country0", "city0", "type0", "year0")));
        EventDao.deleteUserEvents("test_username0");
        Assertions.assertEquals(EventDao.getAllEvents("test_username0"), new ArrayList<>());
    }
    @Test
    void deleteUserEvents_negative() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id0", "test_username0", "test_person_id0",
                "latitude0","longitude0","country0", "city0", "type0", "year0")));
        EventDao.deleteUserEvents("test_username1");
        Assertions.assertNotNull(EventDao.getAllEvents("test_username0"));
    }

    @Test
    void clear_positive() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id0", "test_username0", "test_person_id0",
                "latitude0","longitude0","country0", "city0", "type0", "year0")));
        EventDao.clear();
        Assertions.assertNull(EventDao.getEvent("test_id0"));
    }
    @Test
    void clear_positive2() {
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id0", "test_username0", "test_person_id0",
                "latitude0","longitude0","country0", "city0", "type0", "year0")));
        Assertions.assertTrue(EventDao.addEvent(new Event("test_id1", "test_username1", "test_person_id1",
                "latitude1","longitude1","country1", "city1", "type1", "year1")));
        EventDao.clear();
        Assertions.assertNull(EventDao.getEvent("test_id0"));
        Assertions.assertNull(EventDao.getEvent("test_id1"));
    }
}