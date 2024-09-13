package dev.coms4156.project.individualproject;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 *  This class contains all the tests for the MyFileDatabase class.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseTests {
  @BeforeEach
  public void setupTestFileDatabase() {
    testFileDatabase = new MyFileDatabase(0, "./data.txt");

  }

  @Test
  public void toStringTest() {
    String expectedResult = """
            For the ELEN department:\s
            ELEN 3082:\s
            Instructor: Kenneth Shepard; Location: 1205 MUDD; \
            Time: 4:10-6:40; Department Chair: Ioannis Kymissis
            ELEN 1201:\s
            Instructor: David G Vallancourt; Location: 301 PUP; \
            Time: 4:10-5:25; Department Chair: Ioannis Kymissis
            ELEN 3401:\s
            Instructor: Keren Bergman; Location: 829 MUDD; \
            Time: 2:40-3:55; Department Chair: Ioannis Kymissis
            ELEN 4510:\s
            Instructor: Mohamed Kamaludeen; Location: 903 SSW; \
            Time: 7:00-9:30; Department Chair: Ioannis Kymissis
            ELEN 3331:\s
            Instructor: David G Vallancourt; Location: 203 MATH; \
            Time: 11:40-12:55; Department Chair: Ioannis Kymissis
            ELEN 4830:\s
            Instructor: Christine P Hendon; Location: 633 MUDD; \
            Time: 10:10-12:40; Department Chair: Ioannis Kymissis
            ELEN 3701:\s
            Instructor: Irving Kalet; Location: 333 URIS; \
            Time: 2:40-3:55; Department Chair: Ioannis Kymissis
            ELEN 4702:\s
            Instructor: Alexei Ashikhmin; Location: 332 URIS;\
             Time: 7:00-9:30; Department Chair: Ioannis Kymissis
            For the CHEM department:\s
            CHEM 1403:\s
            Instructor: Ruben M Savizky; Location: 309 HAV; \
            Time: 6:10-7:25; Department Chair: Laura J. Kaufman
            CHEM 3080:\s
            Instructor: Milan Delor; Location: 209 HAV; \
            Time: 10:10-11:25; Department Chair: Laura J. Kaufman
            CHEM 1500:\s
            Instructor: Joseph C Ulichny; Location: 302 HAV; \
            Time: 6:10-9:50; Department Chair: Laura J. Kaufman
            CHEM 2444:\s
            Instructor: Christopher Eckdahl; Location: 309 HAV; \
            Time: 11:40-12:55; Department Chair: Laura J. Kaufman
            CHEM 4102:\s
            Instructor: Dalibor Sames; Location: 320 HAV; \
            Time: 10:10-11:25; Department Chair: Laura J. Kaufman
            CHEM 2045:\s
            Instructor: Luis M Campos; Location: 209 HAV; \
            Time: 1:10-2:25; Department Chair: Laura J. Kaufman
            CHEM 2494:\s
            Instructor: Talha Siddiqui; Location: 202 HAV; \
            Time: 1:10-5:00; Department Chair: Laura J. Kaufman
            CHEM 4071:\s
            Instructor: Jonathan S Owen; Location: 320 HAV; \
            Time: 8:40-9:55; Department Chair: Laura J. Kaufman
            For the PHYS department:\s
            PHYS 4040:\s
            Instructor: James C Hill; Location: 214 PUP; \
            Time: 4:10-5:25; Department Chair: Dmitri N. Basov
            PHYS 1602:\s
            Instructor: Kerstin M Perez; Location: 428 PUP; \
            Time: 10:10-11:25; Department Chair: Dmitri N. Basov
            PHYS 3008:\s
            Instructor: William A Zajc; Location: 329 PUP; \
            Time: 10:10-11:25; Department Chair: Dmitri N. Basov
            PHYS 1201:\s
            Instructor: Eric Raymer; Location: 428 PUP; \
            Time: 2:40-3:55; Department Chair: Dmitri N. Basov
            PHYS 4003:\s
            Instructor: Frederik Denef; Location: 214 PUP; \
            Time: 4:10-5:25; Department Chair: Dmitri N. Basov
            PHYS 1001:\s
            Instructor: Szabolcs Marka; Location: 301 PUP; \
            Time: 2:40-3:55; Department Chair: Dmitri N. Basov
            PHYS 4018:\s
            Instructor: James W McIver; Location: 307 PUP; \
            Time: 2:40-3:55; Department Chair: Dmitri N. Basov
            PHYS 2802:\s
            Instructor: Yury Levin; Location: 329 PUP; \
            Time: 10:10-12:00; Department Chair: Dmitri N. Basov
            For the PSYC department:\s
            PSYC 4493:\s
            Instructor: Jennifer Blaze; Location: 200 SCH; \
            Time: 2:10-4:00; Department Chair: Nim Tottenham
            PSYC 1610:\s
            Instructor: Christopher Baldassano; Location: 200 SCH; \
            Time: 10:10-11:25; Department Chair: Nim Tottenham
            PSYC 2235:\s
            Instructor: Katherine T Fox-Glassman; Location: 501 SCH; \
            Time: 11:40-12:55; Department Chair: Nim Tottenham
            PSYC 2620:\s
            Instructor: Jeffrey M Cohen; Location: 303 URIS; \
            Time: 1:10-3:40; Department Chair: Nim Tottenham
            PSYC 3445:\s
            Instructor: Mariam Aly; Location: 405 SCH; \
            Time: 2:10-4:00; Department Chair: Nim Tottenham
            PSYC 1001:\s
            Instructor: Patricia G Lindemann; Location: 501 SCH; \
            Time: 1:10-2:25; Department Chair: Nim Tottenham
            PSYC 3212:\s
            Instructor: Mayron Piccolo; Location: 200 SCH; \
            Time: 2:10-4:00; Department Chair: Nim Tottenham
            PSYC 4236:\s
            Instructor: Trenton Jerde; Location: 405 SCH; \
            Time: 6:10-8:00; Department Chair: Nim Tottenham
            For the COMS department:\s
            COMS 3827:\s
            Instructor: Daniel Rubenstein; Location: 207 Math; \
            Time: 10:10-11:25; Department Chair: Luca Carloni
            COMS 1004:\s
            Instructor: Adam Cannon; Location: 417 IAB; \
            Time: 11:40-12:55; Department Chair: Luca Carloni
            COMS 3203:\s
            Instructor: Ansaf Salleb-Aouissi; Location: 301 URIS; \
            Time: 10:10-11:25; Department Chair: Luca Carloni
            COMS 4156:\s
            Instructor: Gail Kaiser; Location: 501 NWC; \
            Time: 10:10-11:25; Department Chair: Luca Carloni
            COMS 3157:\s
            Instructor: Jae Lee; Location: 417 IAB; \
            Time: 4:10-5:25; Department Chair: Luca Carloni
            COMS 3134:\s
            Instructor: Brian Borowski; Location: 301 URIS; \
            Time: 4:10-5:25; Department Chair: Luca Carloni
            COMS 3251:\s
            Instructor: Tony Dear; Location: 402 CHANDLER; \
            Time: 1:10-3:40; Department Chair: Luca Carloni
            COMS 3261:\s
            Instructor: Josh Alman; Location: 417 IAB; \
            Time: 2:40-3:55; Department Chair: Luca Carloni
            For the ECON department:\s
            ECON 1105:\s
            Instructor: Waseem Noor; Location: 309 HAV; \
            Time: 2:40-3:55; Department Chair: Michael Woodford
            ECON 2257:\s
            Instructor: Tamrat Gashaw; Location: 428 PUP; \
            Time: 10:10-11:25; Department Chair: Michael Woodford
            ECON 3412:\s
            Instructor: Thomas Piskula; Location: 702 HAM; \
            Time: 11:40-12:55; Department Chair: Michael Woodford
            ECON 3213:\s
            Instructor: Miles Leahey; Location: 702 HAM; \
            Time: 4:10-5:25; Department Chair: Michael Woodford
            ECON 3211:\s
            Instructor: Murat Yilmaz; Location: 310 FAY; \
            Time: 4:10-5:25; Department Chair: Michael Woodford
            ECON 4840:\s
            Instructor: Mark Dean; Location: 142 URIS; \
            Time: 2:40-3:55; Department Chair: Michael Woodford
            ECON 4710:\s
            Instructor: Matthieu Gomez; Location: 517 HAM; \
            Time: 8:40-9:55; Department Chair: Michael Woodford
            ECON 4415:\s
            Instructor: Evan D Sadler; Location: 309 HAV;\
             Time: 10:10-11:25; Department Chair: Michael Woodford
            For the IEOR department:\s
            IEOR 3404:\s
            Instructor: Christopher J Dolan; Location: 303 MUDD; \
            Time: 10:10-11:25; Department Chair: Jay Sethuraman
            IEOR 2500:\s
            Instructor: Uday Menon; Location: 627 MUDD; \
            Time: 11:40-12:55; Department Chair: Jay Sethuraman
            IEOR 4540:\s
            Instructor: Krzysztof M Choromanski; Location: 633 MUDD; \
            Time: 7:10-9:40; Department Chair: Jay Sethuraman
            IEOR 4102:\s
            Instructor: Antonius B Dieker; Location: 209 HAM; \
            Time: 10:10-11:25; Department Chair: Jay Sethuraman
            IEOR 4511:\s
            Instructor: Michael Robbins; Location: 633 MUDD; \
            Time: 9:00-11:30; Department Chair: Jay Sethuraman
            IEOR 4106:\s
            Instructor: Kaizheng Wang; Location: 501 NWC; \
            Time: 10:10-11:25; Department Chair: Jay Sethuraman
            IEOR 4405:\s
            Instructor: Yuri Faenza; Location: 517 HAV; \
            Time: 11:40-12:55; Department Chair: Jay Sethuraman
            IEOR 3658:\s
            Instructor: Daniel Lacker; Location: 310 FAY; \
            Time: 10:10-11:25; Department Chair: Jay Sethuraman
            """;
    assertEquals(expectedResult, testFileDatabase.toString());
  }

  @Test
  public void deserializeFail() throws IOException {
    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./broken.txt"));
    out.writeObject(new ArrayList<>());
    testFileDatabase = new MyFileDatabase(2, "./broken.txt"); // just change the path of data file.
    Exception exception = assertThrows(IllegalArgumentException.class,
            () -> testFileDatabase.deSerializeObjectFromFile());
    assertEquals("Invalid object type in file.", exception.getMessage(),
            "should be hashmap inside the data file");
  }

  public static MyFileDatabase testFileDatabase;
}
