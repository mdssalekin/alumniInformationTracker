package app.Model;

public class User {
  private String firstName;
  private String lastName;
  private String email;
  private String gender;
  private Date dateOfBirth;
  private String contactNumber;
  private String presentAddress;
  private String permanentAddress;
  private String userName;
  private String password;
  private String userType;

  public User(
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
          String password
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.contactNumber = contactNumber;
    this.presentAddress = presentAddress;
    this.permanentAddress = permanentAddress;
    this.userName = userName;
    this.password = password;
    this.userType = userType;
  }

  public User(String userName, String password, String email){
    this.userName = userName;
    this.email = email;
    this.password = password;
  }

  public User(String userName, String password){
    this.userName = userName;
    this.password = password;
  }

  public User(String firstName, String lastName, String email, String gender, String presentAddress, String contactNumber){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.presentAddress = presentAddress;
    this.contactNumber = contactNumber;
  }

  public User() {

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getPresentAddress() {
    return presentAddress;
  }

  public void setPresentAddress(String presentAddress) {
    this.presentAddress = presentAddress;
  }

  public String getPermanentAddress() {
    return permanentAddress;
  }

  public void setPermanentAddress(String permanentAddress) {
    this.permanentAddress = permanentAddress;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String printWhenRegister(){
    return new StringBuilder()
            .append(getUserName())
            .append(":")
            .append(getPassword())
            .append(":")
            .append(getEmail())
            .append("\n")
            .toString();
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
            .append("\n")
            .toString();
  }
}
