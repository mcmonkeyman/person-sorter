package ie.eoin.sample.personsorter;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Utils {

  public Date getDate(String dateString, String formatString){
    Date dob = new Date();
    try {
      SimpleDateFormat f = new SimpleDateFormat(formatString);
      dob = f.parse(dateString);
    }
    catch(ParseException pe) {
          System.out.println("ERROR: Cannot parse \"" + dateString + "\"");
    }
    return dob;
  }
}