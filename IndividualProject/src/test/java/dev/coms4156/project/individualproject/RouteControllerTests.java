package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.*;

import java.util.*;


/**
 *  This class contains all the tests for the RouteController class.
 */
@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RouteControllerTests {
  @BeforeAll
  public static void setupBeforeTesting() {
    testController = new RouteController();
    testProject = new IndividualProjectApplication();
    testProject.run(new String[]{"setup"});
    testProject.onTermination();
    testProject.run(new String[]{"start"});
  }

  @AfterAll
  public static void terminateProject() {
    testProject.onTermination();
  }
  @Test
  public void testRetrieveDepartmentFound() {
    String deptCode = "COMS";
    ResponseEntity<?> response = testController.retrieveDepartment(deptCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    HashMap<String, Department> departmentMapping;
    departmentMapping = IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    assertEquals(departmentMapping.get(deptCode.toUpperCase()).toString(),
            response.getBody().toString());
  }

  @Test
  public void testRetrieveDepartmentNotFound() {
    String deptCode = "AABB";
    ResponseEntity<?> response = testController.retrieveDepartment(deptCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody().toString());
  }

  @Test
  public void testCourseFound() {
    String deptCode = "COMS";
    int courseCode = 3827;
    ResponseEntity<?> response = testController.retrieveCourse(deptCode, courseCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    HashMap<String, Department> departmentMapping;
    departmentMapping = IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    HashMap<String, Course> coursesMapping;
    coursesMapping = departmentMapping.get(deptCode).getCourseSelection();
    assertEquals(coursesMapping.get(Integer.toString(courseCode)).toString(),
            response.getBody().toString());
  }

  @Test
  public void testCourseNotFound() {
    String deptCode = "COMS";
    int courseCode = 0001;
    ResponseEntity<?> response = testController.retrieveCourse(deptCode, courseCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody().toString());
    deptCode = "AABB";
    response = testController.retrieveCourse(deptCode, courseCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody().toString());
  }

  @Test
  public void testCourseIsFullFound() {
    String deptCode = "COMS";
    int courseCode = 3827;
    ResponseEntity<?> response = testController.isCourseFull(deptCode, courseCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("false",
            response.getBody().toString());
  }

  @Test
  public void testCourseIsFullNotFound() {
    String deptCode = "COMS";
    int courseCode = 0001;
    ResponseEntity<?> response = testController.isCourseFull(deptCode, courseCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody().toString());
  }

  @Test
  @Order(1)
  public void testMajorCntFound() {
    String deptCode = "COMS";
    ResponseEntity<?> response = testController.getMajorCtFromDept(deptCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("There are: 2700 majors in the department",
            response.getBody().toString());
  }
  @Test
  public void testMajorCntNotFound() {
    String deptCode = "AABB";
    ResponseEntity<?> response = testController.getMajorCtFromDept(deptCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody().toString());

  }

  @Test
  public void testDeptChairFound() {
    String deptCode = "COMS";
    ResponseEntity<?> response = testController.identifyDeptChair(deptCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Luca Carloni is the department chair.", response.getBody().toString());
  }

  @Test
  public void testDeptChairNotFound() {
    String deptCode = "AABB";
    ResponseEntity<?> response = testController.identifyDeptChair(deptCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody().toString());

  }

  @Test
  public void testFindCourseLocationFound() {
    String deptCode = "COMS";
    int courseCode = 3827;
    ResponseEntity<?> response = testController.findCourseLocation(deptCode, courseCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("207 Math is where the course is located.", response.getBody().toString());
  }

  @Test
  public void testFindCourseLocationNotFound() {
    String deptCode = "COMS";
    int courseCode = 0001;
    ResponseEntity<?> response = testController.findCourseLocation(deptCode, courseCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody().toString());
  }

  @Test
  public void testFindCourseInstructorFound() {
    String deptCode = "COMS";
    int courseCode = 3827;
    ResponseEntity<?> response = testController.findCourseInstructor(deptCode, courseCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Daniel Rubenstein is the instructor for the course.",
            response.getBody().toString());
  }

  @Test
  public void testFindCourseInstructorNotFound() {
    String deptCode = "COMS";
    int courseCode = 0001;
    ResponseEntity<?> response = testController.findCourseInstructor(deptCode, courseCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody().toString());
  }

  @Test
  public void testFindCourseTimeFound() {
    String deptCode = "COMS";
    int courseCode = 3827;
    ResponseEntity<?> response = testController.findCourseTime(deptCode, courseCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("The course meets at: 10:10-11:25.",
            response.getBody().toString());
  }

  @Test
  public void testFindCourseTimeNotFound() {
    String deptCode = "COMS";
    int courseCode = 0001;
    ResponseEntity<?> response = testController.findCourseTime(deptCode, courseCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody().toString());
  }

  @Test
  @Order(2)
  public void testAddMajorToDeptFound() {
    String deptCode = "COMS";
    ResponseEntity<?> response = testController.addMajorToDept(deptCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    HashMap<String, Department> departmentMapping;
    departmentMapping = IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    Department specifiedDept = departmentMapping.get(deptCode);
    assertEquals(2701, specifiedDept.getNumberOfMajors());
    assertEquals("Attribute was updated successfully",
            response.getBody().toString());
    specifiedDept.dropPersonFromMajor(); //revert the difference.
  }

  @Test
  public void testAddMajorToDeptNotFound() {
    String deptCode = "AABB";
    ResponseEntity<?> response = testController.addMajorToDept(deptCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody().toString());

  }

  @Test
  @Order(3)
  public void testRemoveMajorFromDeptFound() {
    String deptCode = "COMS";
    ResponseEntity<?> response = testController.removeMajorFromDept(deptCode);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    HashMap<String, Department> departmentMapping;
    departmentMapping = IndividualProjectApplication.myFileDatabase.getDepartmentMapping();
    Department specifiedDept = departmentMapping.get(deptCode);
    assertEquals(2699, specifiedDept.getNumberOfMajors());
    assertEquals("Attribute was updated or is at minimum",
            response.getBody().toString());
    specifiedDept.addPersonToMajor(); //revert the difference.
  }

  @Test
  public void testRemoveMajorFromDeptNotFound() {
    String deptCode = "AABB";
    ResponseEntity<?> response = testController.removeMajorFromDept(deptCode);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody().toString());

  }


  /** The test instances used for testing. */
  public static RouteController testController;
  public static IndividualProjectApplication testProject;
}
