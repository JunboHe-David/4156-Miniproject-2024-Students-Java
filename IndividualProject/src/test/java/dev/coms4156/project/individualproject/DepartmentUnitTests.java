package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


/**
 *  This class contains all the unit tests for the Department class.
 */
@SpringBootTest
@ContextConfiguration

public class DepartmentUnitTests {
  @BeforeAll
  public static void setupDepartmentForTesting() {
    HashMap<String, Course> courses = new HashMap<>();
    testDepartment = new Department("COMS", courses, "Luca Carloni", 1);
  }

  @Test
  public void dropFromMajorTest() {
    testDepartment.dropPersonFromMajor();
    assertEquals(0, testDepartment.getNumberOfMajors(),
            "number of majors should be 0 by decreasing 1");
    testDepartment.dropPersonFromMajor();
    assertEquals(0, testDepartment.getNumberOfMajors(),
            "number of majors remains 0 as number of majors can not be negative");
  }



  public static Department testDepartment;
}
