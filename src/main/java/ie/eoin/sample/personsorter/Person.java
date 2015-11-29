package ie.eoin.sample.personsorter;

import java.text.SimpleDateFormat;
import java.text.Format;
import java.util.Date;

public class Person implements Comparable<Person> {

  private String firstName;
  private String lastName;
  private Gender  gender;
  private String middleName;
  private String favColor;
  private Date dateOfBirth;

  public Person(String firstName, String middleName, String lastName,
    String gender, String favColor, Date dateOfBirth ) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.gender = Gender.getGender(gender);
    this.favColor = favColor;
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
        return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
        return false;
    }
    Person guest = (Person) obj;
    return (firstName == guest.firstName 
        || (firstName != null && firstName.equals(guest.getFirstName())))
      && (lastName == guest.lastName 
        || (lastName != null && lastName.equals(guest.getLastName())))
      && (gender == guest.gender 
        || (gender != null && gender.equals(guest.getGender())))
      && (middleName == guest.middleName 
        || (middleName != null && middleName.equals(guest.getMiddleName())))
      && (favColor == guest.favColor 
       || (favColor != null && favColor.equals(guest.getFavColor())))
      && (dateOfBirth == guest.dateOfBirth 
       || (dateOfBirth != null && dateOfBirth.equals(guest.getDateOfBirth())));
  }

  @Override
  public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result
              + ((firstName == null) ? 0 : firstName.hashCode());
      result = prime * result
              + ((lastName == null) ? 0 : lastName.hashCode());
      result = prime * result
              + ((middleName == null) ? 0 : middleName.hashCode());
      result = prime * result
              + ((gender == null) ? 0 : gender.hashCode());
      result = prime * result
              + ((favColor == null) ? 0 : favColor.hashCode());
      result = prime * result
              + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
      return result;
  }

  @Override
  public String toString() { 
      String result = getLastName()  + " " + getFirstName() + " " + 
       getGender().toString() + " " + convertDateToString(getDateOfBirth()) + " " + getFavColor() ;
      return result;
  }

  public int compareTo(Person n) {
      int lastCmp = lastName.compareTo(n.lastName);
      return (lastCmp != 0 ? lastCmp : firstName.compareTo(n.firstName));
  }

  private String convertDateToString(Date d) {

    Format formatter = new SimpleDateFormat("M/d/yyyy");
    return formatter.format(d);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public Gender getGender() {
    return gender;
  }

  public String getFavColor() {
    return favColor;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }
}
