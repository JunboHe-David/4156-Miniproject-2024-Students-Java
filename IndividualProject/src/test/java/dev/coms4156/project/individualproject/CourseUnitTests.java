package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;




/**
 *  This class contains all the unit test for the course project.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }


  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void courseFullTest() {
    testCourse.setEnrolledStudentCount(251);
    assertEquals(false, testCourse.isCourseFull(), "Student count 251 > capacity 250");
    testCourse.setEnrolledStudentCount(249);
    assertEquals(true, testCourse.isCourseFull(), "Student count 249 > capacity 250");
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

