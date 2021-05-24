package app.Model;

public class Admin extends User {
  private Organization organization;

  public Admin(){
    super();
  }

  public Admin(
          String firstName,
          String lastName,
          String email,
          String gender,
          Date dateOfBirth,
          String contactNumber,
          String presentAddress,
          String permanentAddress,
          String userType,
          String userName,
          String password,
          Organization organization
  ) {
    super(firstName, lastName, email, gender, dateOfBirth, contactNumber, presentAddress, permanentAddress, userType, userName, password);
    this.organization = organization;
  }


  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  @Override
  public String toString(){
    return new StringBuilder()
            .append(getFirstName())
            .append(":")
            .append(getLastName())
            .append(":")
            .append(getEmail())
            .append(":")
            .append(getGender())
            .append(":")
            .append(getDateOfBirth())
            .append(":")
            .append(getContactNumber())
            .append(":")
            .append(getPresentAddress())
            .append(":")
            .append(getPermanentAddress())
            .append(":")
            .append(getUserType())
            .append(":")
            .append(getUserName())
            .append(":")
            .append(getPassword())
            .append(":")
            .append(getOrganization().getDesignation())
            .append(":")
            .append(getOrganization().getDepartment())
            .append(":")
            .append(getOrganization().getName())
            .append("\n")
            .toString();
  }
}
