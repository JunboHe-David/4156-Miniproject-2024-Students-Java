package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;




/**
 *  This class contains all the unit test for the course class.
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
    assertEquals(true, testCourse.isCourseFull(), "Student count 251 > capacity 250");
    testCourse.setEnrolledStudentCount(249);
    assertEquals(false, testCourse.isCourseFull(), "Student count 249 < capacity 250");
  }

  @Test
  public void enrollTest() {
    testCourse.setEnrolledStudentCount(249);
    assertEquals(false, testCourse.isCourseFull(), "Student count 249 < capacity 250");
    testCourse.enrollStudent();
    assertEquals(true, testCourse.isCourseFull(), "enrollment++ Student count 250 == capacity 250");
    assertEquals(false, testCourse.enrollStudent(),
            "student count reach capacity cannot enroll anymore students");
  }

  @Test
  public void dropTest() {
    testCourse.setEnrolledStudentCount(250);
    assertEquals(true, testCourse.isCourseFull(), "Student count 250 == capacity 250");
    testCourse.dropStudent();
    assertEquals(false, testCourse.isCourseFull(),
            "enrollment--, Student count 249 < capacity 250");
    testCourse.setEnrolledStudentCount(0);
    assertEquals(false, testCourse.dropStudent(),
            "student count is 0 cannot drop anymore students");
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

