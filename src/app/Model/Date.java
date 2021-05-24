package app.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date implements Comparable<Date> {
  private String day;
  private String month;
  private String year;

  public Date(){}

  public Date(String day, String month, String year){
    this.day = day;
    this.month = month;
    this.year = year;
  }
  public Date(String dateOfBirth){
    String[] dateSplit = dateOfBirth.split("-");
    this.year = dateSplit[0];
    this.month = dateSplit[1];
    this.day = dateSplit[2];
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public Date generateCurrentDate(){
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();
    return new Date(dtf.format(now));
  }

  @Override
  public String toString(){
    return new StringBuilder()
            .append(getYear())
            .append("-")
            .append(getMonth())
            .append("-")
            .append(getDay())
            .toString();
  }

  @Override
  public int compareTo(Date date){
    return this.getYear().compareTo(date.getYear());
  }
}
