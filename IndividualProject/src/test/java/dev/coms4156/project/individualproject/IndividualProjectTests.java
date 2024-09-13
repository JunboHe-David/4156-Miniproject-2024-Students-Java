package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;




/**
 *  This class contains all the tests for the IndividualProjectApplication class.
 */
@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IndividualProjectTests {
  /**
   *  setup testProject variable and redirect output stream before each test.
   */
  @BeforeEach
  public void setupProjectForTesting() {
    testProject = new IndividualProjectApplication();
    outContent.reset();
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void runTestWithMode1() {
    String[] args = {"setup"};
    testProject.run(args);
    assertTrue(outContent.toString().contains("System Setup"), "should run with mode 1");
  }

  @Test
  public void runTestWithMode0() {
    String[] args = {"any"};
    testProject.run(args);
    assertTrue(outContent.toString().contains("Start up"), "should run with mode 0");
  }

  @Test
  @Order(1)
  public void onTerminationSave() {
    String[] args = {"setup"};
    testProject.run(args);
    testProject.onTermination();
    assertTrue(outContent.toString().contains("Object serialized successfully."),
            "save data will serialize the object and reset database");
  }

  @Test
  @Order(2)
  public void onTerminationNoSave() {
    String[] args = {"start"};
    testProject.run(args);
    testFileDatabase = new MyFileDatabase(0, "./data.txt");
    IndividualProjectApplication.overrideDatabase(testFileDatabase);
    testProject.onTermination();
    assertFalse(outContent.toString().contains("Object serialized successfully."),
            "Not save data will not serialize anything and print nothing additionally");
  }

  /** The test instances used for testing. */
  public static IndividualProjectApplication testProject;
  public static MyFileDatabase testFileDatabase;

  /** Captured io to check status of application. */
  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final PrintStream originalOut = System.out;
}

