package app.Model;

public class Alumni extends User {
  private String department;
  private String studentID;
  private Date graduationYear;
  private Date joinedAsAlumni;
  private Organization organization;

  public Alumni(
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
          String department,
          String studentID,
          Date graduationYear,
          Date joinedAsAlumni,
          Organization organization
  ) {
    super(firstName, lastName, email, gender, dateOfBirth, contactNumber, presentAddress, permanentAddress, userType, userName, password);
    this.department = department;
    this.studentID = studentID;
    this.graduationYear = graduationYear;
    this.joinedAsAlumni = joinedAsAlumni;
    this.organization = organization;
  }

  public Alumni(String firstName, String lastName, String email, String gender, String presentAddress, String contactNo, String department, String studentID, Date graduationYear, Date joined, Organization organization){
    super(firstName, lastName, email, gender, presentAddress, contactNo);
    this.department = department;
    this.studentID = studentID;
    this.graduationYear = graduationYear;
    this.joinedAsAlumni = joined;
    this.organization = organization;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getStudentID() {
    return studentID;
  }

  public void setStudentID(String studentID) {
    this.studentID = studentID;
  }

  public Date getGraduationYear() {
    return graduationYear;
  }

  public void setGraduationYear(Date graduationYear) {
    this.graduationYear = graduationYear;
  }

  public Date getJoinedAsAlumni() {
    return joinedAsAlumni;
  }

  public void setJoinedAsAlumni(Date joinedAsAlumni) {
    this.joinedAsAlumni = joinedAsAlumni;
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
            .append(getDepartment())
            .append(":")
            .append(getStudentID())
            .append(":")
            .append(getGraduationYear())
            .append(":")
            .append(getJoinedAsAlumni())
            .append(":")
            .append(getOrganization().getName())
            .append(":")
            .append(getOrganization().getDesignation())
            .append(":")
            .append(getOrganization().getDepartment())
            .append("\n")
            .toString();
  }
}
