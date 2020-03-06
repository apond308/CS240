package tests.dao_operations;

import dao.AuthTokenDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthTokenDaoTest {

	@BeforeEach
	void before() {
		AuthTokenDao.clear();
	}

	@Test
	void insertion_positive() {
		Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
	}
	@Test
	void insertion_negative() {
		Assertions.assertFalse(AuthTokenDao.updateToken(null, null));
	}

	@Test
	void retrieval_positive() {
		Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
		Assertions.assertNotNull(AuthTokenDao.getUserFromToken("test_token"));
	}
	@Test
	void retrieval_negative() {
		Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
		Assertions.assertNull(AuthTokenDao.getUserFromToken("invalid_token"));
	}

	@Test
	void clear() {
		Assertions.assertTrue(AuthTokenDao.updateToken("test_name", "test_token"));
		AuthTokenDao.clear();
		Assertions.assertNull(AuthTokenDao.getUserFromToken("test_token"));
	}
}