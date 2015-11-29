package ie.eoin.sample.personsorter;

public class Runner {

  public static void main(String[] strArgs){  
    PersonProcessor personProcessor = new PersonProcessor();
    personProcessor.processAll();
    System.out.println("Output 1");
    System.out.println(personProcessor.output1());
    System.out.println("Output 2");
    System.out.println(personProcessor.output2());
    System.out.println("Output 3");
    System.out.println(personProcessor.output3());
  }
}
