package ie.eoin.sample.personsorter;

import ie.eoin.sample.personsorter.Person;
import ie.eoin.sample.personsorter.PersonProcessor;
import ie.eoin.sample.personsorter.Utils;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class PersonProcessorTest {

  private PersonProcessor proc;
  private List<ArrayList> sampleOutput1;
  private List<ArrayList> sampleOutput2;
  private List<ArrayList> sampleOutput3; 
  private Date testDate1;
  private Date testDate2;
  private Date testDate3;
  private Utils utils;
  
  @Before
  public void setUp() {
    proc = new PersonProcessor();
    sampleOutput1 = new ArrayList(Arrays.asList( 
      new String[] {
        "Hingis Martina Female 4/2/1979 Green",
        "Kelly Sue Female 7/12/1959 Pink",
        "Kournikova Anna Female 6/3/1975 Red",
        "Seles Monica Female 12/2/1973 Black",
        "Abercrombie Neil Male 2/13/1943 Tan",
        "Bishop Timothy Male 4/23/1967 Yellow",
        "Bonk Radek Male 6/3/1975 Green",
        "Bouillon Francis Male 6/3/1975 Blue",
        "Smith Steve Male 3/3/1985 Red"}

    ));
    sampleOutput2 = new ArrayList(Arrays.asList(
      new String[] {
        "Abercrombie Neil Male 2/13/1943 Tan",
        "Kelly Sue Female 7/12/1959 Pink",
        "Bishop Timothy Male 4/23/1967 Yellow",
        "Seles Monica Female 12/2/1973 Black",
        "Bonk Radek Male 6/3/1975 Green",
        "Bouillon Francis Male 6/3/1975 Blue",
        "Kournikova Anna Female 6/3/1975 Red",
        "Hingis Martina Female 4/2/1979 Green",
        "Smith Steve Male 3/3/1985 Red"}
    ));
    sampleOutput3 = new ArrayList(Arrays.asList(
      new String[] {
        "Smith Steve Male 3/3/1985 Red",
        "Seles Monica Female 12/2/1973 Black",
        "Kournikova Anna Female 6/3/1975 Red",
        "Kelly Sue Female 7/12/1959 Pink",
        "Hingis Martina Female 4/2/1979 Green",
        "Bouillon Francis Male 6/3/1975 Blue",
        "Bonk Radek Male 6/3/1975 Green",
        "Bishop Timothy Male 4/23/1967 Yellow",
        "Abercrombie Neil Male 2/13/1943 Tan"}
    ));
    utils = new Utils();

    testDate1 = utils.getDate("2/13/1943","MM/dd/yyyy");
    testDate2 = utils.getDate("3-3-1985","MM-dd-yyyy");
    testDate3 = utils.getDate("6-3-1975","MM-dd-yyyy");

  }

  @Test 
  public void testProcessPersonComma() {   
    Person commaTestPerson = new Person("Neil", "", "Abercrombie", "male", "Tan", testDate1);
    assertEquals(
      commaTestPerson, 
      proc.processPerson("Abercrombie, Neil, male, Tan, 2/13/1943",","));
  }

  @Test 
  public void testProcessPersonPipe() {
    Person pipeTestPerson = new Person("Steve","D","Smith","male", "Red", testDate2);
    assertEquals(
      pipeTestPerson, 
      proc.processPerson("Smith | Steve | D | M | Red | 3-3-1985","\\|"));
  }

  @Test 
  public void testProcessPersonSpace() {
    assertEquals(
      new Person("Anna","F","Kournikova","F","Red", testDate3), 
      proc.processPerson("Kournikova Anna F F 6-3-1975 Red"," "));
  }

  @Test 
  public void testOutput1() {
    proc.processAll();
    assertEquals( sampleOutput1, proc.output1());
  }

  @Test 
  public void testOutput2() {
    proc.processAll();
    assertEquals( sampleOutput2, proc.output2());
  }

  @Test 
  public void testOutput3() {
    proc.processAll();
    assertEquals( sampleOutput3, proc.output3());
  }

}
