package ie.eoin.sample.personsorter;

public enum Gender {
  Male, Female, NoGender;

  public static Gender getGender(String input){
    if ( input.equals("M") || input.equals("Male") || input.equals("male")){
      return Male;
    } else if ( input.equals("F") || input.equals("Female") || input.equals("female")){
      return Female;
    } else{
      return NoGender;
    }
  }
}