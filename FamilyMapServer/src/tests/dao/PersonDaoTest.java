package tests.dao;

import dao.PersonDao;
import dao.UserDao;
import models.Person;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoTest {

    @BeforeEach
    void before(){
        PersonDao.clear();
    }

    @Test
    void addPerson_positive() {
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0")));
    }
    @Test
    void addPerson_negative() {
        Assertions.assertFalse(PersonDao.addPerson(null));
    }

    @Test
    void getUserPerson_positive() {
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0")));
        UserDao.addUser(new User("test_person_id0", "test_username0", "test_password0","test_email0", "test_first0", "test_last0", "test_gender0"));
        Assertions.assertNotNull(PersonDao.getUserPerson("test_username0"));
    }
    @Test
    void getUserPerson_negative() {
        Assertions.assertNull(PersonDao.getUserPerson("test_username0"));
    }

    @Test
    void getPerson_positive() {
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertNotNull(PersonDao.getPerson("test_person_id0"));
    }
    @Test
    void getPerson_negative() {
        Assertions.assertNull(PersonDao.getPerson("test_person_id0"));
    }

    @Test
    void deleteUserPerson_positive() {
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertNotNull(PersonDao.getPerson("test_person_id0"));
        PersonDao.deleteUserPerson("test_username0");
        Assertions.assertNotNull(PersonDao.getPerson("test_person_id0"));
    }
    @Test
    void deleteUserPerson_positive2() {
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id1", "test_username1", "test_first1", "test_last1", "test_gender1")));
        PersonDao.deleteUserPerson("test_username0");
        PersonDao.deleteUserPerson("test_username1");
        Assertions.assertNotNull(PersonDao.getPerson("test_person_id0"));
        Assertions.assertNotNull(PersonDao.getPerson("test_person_id1"));
    }

    @Test
    void clear_positive() {
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertNotNull(PersonDao.getPerson("test_person_id0"));
        PersonDao.clear();
        Assertions.assertNull(PersonDao.getPerson("test_person_id0"));
    }
    @Test
    void clear_positive2() {
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id0", "test_username0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertTrue(PersonDao.addPerson(new Person("test_person_id1", "test_username1", "test_first1", "test_last1", "test_gender1")));
        PersonDao.clear();
        Assertions.assertNull(PersonDao.getPerson("test_person_id0"));
        Assertions.assertNull(PersonDao.getPerson("test_person_id1"));
    }
}