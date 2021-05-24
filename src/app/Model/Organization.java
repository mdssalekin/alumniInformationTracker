package app.Model;

public class Organization {
  private String name;
  private String designation;
  private String department;

  public Organization(String name, String designation, String department) {
    this.name = name;
    this.designation = designation;
    this.department = department;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public String toString(){
    return new StringBuilder()
            .append(getDesignation())
            .append(", ")
            .append(getDepartment())
            .append(", ")
            .append(getName())
            .toString();
  }
}