package app.database;

import app.Controller.Operations;
import app.Model.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DatabaseHandler implements Actions {

  public boolean signInUser(User user){
    HashMap<String, User> users = readFileForUser();
    if (users.containsKey(user.getUserName())){
      if (users.get(user.getUserName()).getPassword().equals(user.getPassword())){
        return true;
      }
    }
    return false;
  }

  public boolean signUpUser(User user) {
    writeFileForUser(user);
    return true;
  }

  public boolean createAlumni(Alumni alumni){
    boolean success = checkIfAlumniExists(alumni);
    if (success){
      Operations.userNameExistsError(alumni.getUserName());
    } else {
      writeFileForAlumniOrAdmin(alumni, "./src/app/database/Alumni.txt");
    }
    return true;
  }

  private boolean checkIfAlumniExists(Alumni alumni){
    return readFileForAlumni().containsKey(alumni.getUserName());
  }

  public Alumni searchAlumni(String alumniName){
    HashMap<String, Alumni> temp = readFileForAlumni();
    if (temp.containsKey(alumniName)){
      Alumni alumni = temp.get(alumniName);
      return new Alumni(
              alumni.getFirstName(),
              alumni.getLastName(),
              alumni.getEmail(),
              alumni.getGender(),
              alumni.getPresentAddress(),
              alumni.getContactNumber(),
              alumni.getDepartment(),
              alumni.getStudentID(),
              alumni.getGraduationYear(),
              alumni.getJoinedAsAlumni(),
              alumni.getOrganization()
      );
    } else {
      return null;
    }
  }

  public Alumni searchForUpdateAlumni(String alumniName){
    HashMap<String, Alumni> temp = readFileForAlumni();
    if (temp.containsKey(alumniName)){
      Alumni alumni = temp.get(alumniName);
      return new Alumni(
              alumni.getFirstName(),
              alumni.getLastName(),
              alumni.getEmail(),
              alumni.getGender(),
              alumni.getDateOfBirth(),
              alumni.getContactNumber(),
              alumni.getPresentAddress(),
              alumni.getPermanentAddress(),
              alumni.getUserType(),
              alumni.getUserName(),
              alumni.getPassword(),
              alumni.getDepartment(),
              alumni.getStudentID(),
              alumni.getGraduationYear(),
              alumni.getJoinedAsAlumni(),
              alumni.getOrganization()
      );
    } else {
      return null;
    }
  }

  public boolean updateAlumni(Alumni alumni) throws FileNotFoundException {
    HashMap<String, Alumni> users = readFileForAlumni();
    if(users.containsKey(alumni.getUserName())){
      users.replace(alumni.getUserName(), alumni);
      clearFile("./src/app/database/Alumni.txt");
      for (Map.Entry<String, Alumni> entry : users.entrySet()){
        writeFileForAlumniOrAdmin(entry.getValue(), "./src/app/database/Alumni.txt");
      }
      System.out.println("Alumni update success");
      return true;
    } else {
      return false;
    }
  }

  public boolean deleteAlumni(String alumniName) throws FileNotFoundException {
    HashMap<String, Alumni> temp = readFileForAlumni();
    temp.remove(alumniName);
    clearFile("./src/app/database/Alumni.txt");
    for (Map.Entry<String, Alumni> entry: temp.entrySet()){
      writeFileForAlumniOrAdmin(entry.getValue(), "./src/app/database/Alumni.txt");
    }
    return true;
  }

  public HashMap<String, Alumni> viewAllAlumni(){
    return readFileForAlumni();
  }

  public boolean createAdmin(Admin admin){
    writeFileForAlumniOrAdmin(admin, "./src/app/database/Admin.txt");
    return true;
  }

  private HashMap readFileForUser() {
    HashMap<String, User> users = new HashMap<>();
    try {
      File file = new File("./src/app/database/User credentials.txt");
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        String data = scanner.nextLine();
        String[] arr = data.split(":");
        users.put(arr[0], new User(arr[0], arr[1], arr[2]));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return users;
  }

  private void writeFileForUser(User user){
    File file = new File("./src/app/database/User credentials.txt");
    try {
      Writer output = new BufferedWriter(new FileWriter(file, true));
      output.append(user.printWhenRegister());
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeFileForAlumniOrAdmin(User user, String path){
    File file = new File(path);
    try {
      Writer output = new BufferedWriter(new FileWriter(file, true));
      output.append(user.toString());
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private HashMap<String, Alumni> readFileForAlumni(){
    HashMap<String, Alumni> users = new HashMap<>();
    try {
      File file = new File("./src/app/database/Alumni.txt");
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        String data = scanner.nextLine();
        String[] arr = data.split(":");
        users.put(arr[9],
                new Alumni(
                        arr[0],
                        arr[1],
                        arr[2],
                        arr[3],
                        new Date(arr[4]),
                        arr[5],
                        arr[6],
                        arr[7],
                        arr[8],
                        arr[9],
                        arr[10],
                        arr[11],
                        arr[12],
                        new Date(arr[13]),
                        new Date(arr[14]),
                        new Organization(arr[15], arr[16], arr[17])
                ));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return users;
  }

  private HashMap<String, Admin> readFileForAdmin(){
    HashMap<String, Admin> admins = new HashMap<>();
    try {
      File file = new File("./src/app/database/Admin.txt");
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()){
        String data = scanner.nextLine();
        String[] arr = data.split(":");
        admins.put(arr[9],
                new Admin(
                        arr[0],
                        arr[1],
                        arr[2],
                        arr[3],
                        new Date(arr[4]),
                        arr[5],
                        arr[6],
                        arr[7],
                        arr[8],
                        arr[9],
                        arr[10],
                        new Organization(arr[11], arr[12], arr[13])
                ));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return admins;
  }

  private void clearFile(String path) throws FileNotFoundException {
    File file = new File(path);
    PrintWriter printWriter = new PrintWriter(file);
    printWriter.print("");
    printWriter.close();
  }
}