package ie.eoin.sample.personsorter;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class PersonProcessor {

  private List<Person> allPersons;
  public static final String INPUT_FILE_DIRECTORY = "/src/main/resources/input_files/";

  public static final Comparator<Person> GENDER_ORDER = new Comparator<Person>() {
    public int compare(Person p1, Person p2) {
      return p2.getGender().compareTo(p1.getGender());
    }
  };

  public static final Comparator<Person> BIRTH_ORDER = new Comparator<Person>() {
    public int compare(Person p1, Person p2) {
      return p2.getDateOfBirth().compareTo(p1.getDateOfBirth());
    }
  };

  public PersonProcessor() {
  }

  public List<Person> processAll() {
    allPersons = new ArrayList();
    for (String line : getFileAsStringList(INPUT_FILE_DIRECTORY, "comma.txt")) {
      allPersons.add(processPerson(line, ","));
    }
    for (String line : getFileAsStringList(INPUT_FILE_DIRECTORY, "pipe.txt")) {
      allPersons.add(processPerson(line, "\\|"));
    }
    for (String line : getFileAsStringList(INPUT_FILE_DIRECTORY, "space.txt")) {
      allPersons.add(processPerson(line, " "));
    }
    return allPersons;
  }

  public Person processPerson(String input, String delimeter){
    if(delimeter == ","){ 
      Map<String, Integer> orderMap = new HashMap<String, Integer>() {{
          put("firstName", 1);
          put("middleName", -1);
          put("lastName", 0);
          put("gender", 2);
          put("favColor", 3);
          put("date", 4);
      }};
      return processPerson(input, delimeter, "MM/dd/yyyy", orderMap);
    } else if (delimeter == "\\|"){
      Map<String, Integer> orderMap = new HashMap<String, Integer>() {{
          put("firstName", 1);
          put("middleName", 2);
          put("lastName", 0);
          put("gender", 3);
          put("favColor", 4);
          put("date", 5);
      }};
      return processPerson(input, delimeter, "MM-dd-yyyy", orderMap);
    } else  {
      Map<String, Integer> orderMap = new HashMap<String, Integer>() {{
          put("firstName", 1);
          put("middleName", 2);
          put("lastName", 0);
          put("gender", 3);
          put("favColor", 5);
          put("date", 4);
      }};
      return processPerson(input, delimeter, "MM-dd-yyyy", orderMap);
    }
  }

  public Person processPerson(String input, String delimeter, String dateFormat, Map<String, Integer> processOrder){

    String[] data = input.split(delimeter);
    String middleName = "";
    if( processOrder.get("middleName") != -1){
      middleName = data[processOrder.get("middleName")].trim();
    } 
    Date d = new Utils().getDate(data[processOrder.get("date")], dateFormat);
    return new Person( data[processOrder.get("firstName")].trim(), middleName,
      data[processOrder.get("lastName")].trim(), data[processOrder.get("gender")].trim(), data[processOrder.get("favColor")].trim(), d);
  }

  public List<String> output1(){
    Collections.sort(allPersons);
    Collections.sort(allPersons, GENDER_ORDER);
    return outputForPersons(allPersons);
  }

  public List<String> output2(){
    Collections.sort(allPersons);
    Collections.sort(allPersons, Collections.reverseOrder(BIRTH_ORDER));
    return outputForPersons(allPersons);
  }

  public List<String> output3(){
    Collections.sort(allPersons);
    Collections.reverse(allPersons);
    return outputForPersons(allPersons);
  }

  public List<String> outputForPersons(List<Person> persons) {
    ArrayList<String> outputs = new ArrayList();
    for(int i = 0; i < persons.size(); i++) {
      outputs.add(persons.get(i).toString());
    }
    return outputs;
  }

  public List<String> getFileAsStringList(String path, String fileName){
    try {
      String currentDirectory = new File("").getAbsolutePath();
      BufferedReader br = new BufferedReader(new FileReader(currentDirectory + path + fileName));
      String sCurrentLine;
      List<String> list= new ArrayList<String>();
      while ((sCurrentLine = br.readLine()) != null) {
        list.add(sCurrentLine);
      }

      return list;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList();
  }
}
