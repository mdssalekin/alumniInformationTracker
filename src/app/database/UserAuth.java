package app.database;

import app.Model.User;

import static app.Controller.Operations.*;

public class UserAuth {
  private static DatabaseHandler databaseHandler = new DatabaseHandler();
  public static boolean login( String userName, String password) {
    if (isLoginFieldEmpty(userName, password)){
      User user = new User(userName, password);
      boolean success = databaseHandler.signInUser(user);
      if (!success){
        loginOrRegistrationUnSuccess("Login");
      } else {
        return true;
      }
    }
    return false;
  }

  public static boolean register(String userName, String email, String password) {
    if (isRegisterFieldEmpty(userName, email, password)) {
      User regUser = new User(userName, password, email);
      boolean success = databaseHandler.signUpUser(regUser);
      if (!success){
        loginOrRegistrationUnSuccess("Registration");
      } else {
        return true;
      }
    }
    return false;
  }

}