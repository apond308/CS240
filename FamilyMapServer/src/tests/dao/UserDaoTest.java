package tests.dao;

import dao.EventDao;
import dao.UserDao;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.attribute.UserDefinedFileAttributeView;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @BeforeEach
    void before(){
        UserDao.clear();
    }

    @Test
    void addUser_positive() {
        Assertions.assertTrue(UserDao.addUser(new User("test_person_id0", "test_username0", "test_password0","test_email0", "test_first0", "test_last0", "test_gender0")));
    }
    @Test
    void addUser_negative() {
        Assertions.assertFalse(UserDao.addUser(null));
    }

    @Test
    void getUser_positive() {
        Assertions.assertTrue(UserDao.addUser(new User("test_person_id0", "test_username0", "test_password0","test_email0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertNotNull(UserDao.getUser("test_username0"));
    }
    @Test
    void getUser_negative() {
        Assertions.assertNull(UserDao.getUser("test_username0"));
    }

    @Test
    void clear_positive() {
        Assertions.assertTrue(UserDao.addUser(new User("test_person_id0", "test_username0", "test_password0","test_email0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertNotNull(UserDao.getUser("test_username0"));
        UserDao.clear();
        Assertions.assertNull(UserDao.getUser("test_username0"));
    }
    @Test
    void clear_positive2() {
        Assertions.assertTrue(UserDao.addUser(new User("test_person_id0", "test_username0", "test_password0","test_email0", "test_first0", "test_last0", "test_gender0")));
        Assertions.assertTrue(UserDao.addUser(new User("test_person_id1", "test_username1", "test_password1","test_email1", "test_first1", "test_last1", "test_gender1")));
        Assertions.assertNotNull(UserDao.getUser("test_username0"));
        Assertions.assertNotNull(UserDao.getUser("test_username1"));
        UserDao.clear();
        Assertions.assertNull(UserDao.getUser("test_username0"));
        Assertions.assertNull(UserDao.getUser("test_username1"));
    }
}