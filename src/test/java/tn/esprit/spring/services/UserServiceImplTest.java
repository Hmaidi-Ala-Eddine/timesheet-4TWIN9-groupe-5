package tn.esprit.spring.services;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import tn.esprit.spring.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(OrderAnnotation.class)
 class UserServiceImplTest {
    @Test
    public void testAddUser() {
        String failTest = System.getenv("TIMESHEET_TESTS_FAIL");
        if ("True".equalsIgnoreCase(failTest)) {
            fail("Test échoue car TIMESHEET_TESTS_FAIL est activé !");
        }
    }


}
