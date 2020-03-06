package tests.dao;

import dao.AuthTokenDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthTokenDaoTest {

    @Test
    void getUserFromToken_positive() {
        Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
        Assertions.assertNotNull(AuthTokenDao.getUserFromToken("test_token"));
    }
    @Test
    void getUserFromToken_negative() {
        Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
        Assertions.assertNull(AuthTokenDao.getUserFromToken("invalid"));
    }

    @Test
    void updateToken_positive() {
        Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
    }
    @Test
    void updateToken_negative() {
        Assertions.assertFalse(AuthTokenDao.updateToken(null, "test_token"));
    }

    @Test
    void clear_positive() {
        Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
        AuthTokenDao.clear();
        Assertions.assertNull(AuthTokenDao.getUserFromToken("test_token"));
    }
    @Test
    void clear_positive2() {
        Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
        Assertions.assertTrue(AuthTokenDao.updateToken("test_name2", "test_token2"));
        Assertions.assertNotNull(AuthTokenDao.getUserFromToken("test_token"));
        AuthTokenDao.clear();
        Assertions.assertNull(AuthTokenDao.getUserFromToken("test_token2"));
    }
}