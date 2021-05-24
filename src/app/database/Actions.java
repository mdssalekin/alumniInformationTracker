package app.database;

import app.Model.Admin;
import app.Model.Alumni;
import app.Model.User;

import java.io.FileNotFoundException;
import java.util.HashMap;

public interface Actions {
  boolean signInUser(User user);
  boolean signUpUser(User user);

  boolean createAlumni(Alumni alumni);
  Alumni searchAlumni(String alumniName);
  Alumni searchForUpdateAlumni(String alumniName);
  boolean updateAlumni(Alumni alumni) throws FileNotFoundException;
  boolean deleteAlumni(String alumniName) throws FileNotFoundException;
  HashMap<String, Alumni> viewAllAlumni();

  boolean createAdmin(Admin admin);
}