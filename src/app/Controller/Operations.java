package app.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.regex.Pattern;

public class Operations {

  public static void loadingGifPlay(ImageView loadIcon) {
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, actionEvent -> loadIcon.setStyle("visibility: visible")),
            new KeyFrame(Duration.seconds(2.0), actionEvent -> loadIcon.setStyle("visibility: hidden"))
    );
    timeline.play();
  }

  public static boolean isLoginFieldEmpty(String name, String password){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Login Error");
    alert.setHeaderText(null);
    if (name.isEmpty() && password.isEmpty()){
      alert.setContentText("Please enter your credentials!");
      alert.showAndWait();
      return false;
    } else if (name.isEmpty()){
      alert.setContentText("Please enter Username!");
      alert.showAndWait();
      return false;
    } else if (password.isEmpty()){
      alert.setContentText("Please enter Password!");
      alert.showAndWait();
      return false;
    } else {
      return true;
    }
  }

  public static boolean isRegisterFieldEmpty(String userName, String email, String password){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Register Error");
    alert.setHeaderText(null);
    if (userName.isEmpty() && email.isEmpty() && password.isEmpty()){
      alert.setContentText("Enter credentials to register a new account!");
      alert.showAndWait();
      return false;
    } else if (userName.isEmpty() ){
      alert.setContentText("Enter User Name!");
      alert.showAndWait();
      return false;
    } else if (email.isEmpty()){
      alert.setContentText("Enter Email!");
      alert.showAndWait();
      return false;
    } else if (password.isEmpty()){
      alert.setContentText("Enter Password");
      alert.showAndWait();
      return false;
    } else {
      return true;
    }
  }

  public static void loginOrRegistrationUnSuccess(String name){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(name + " Error");
    alert.setHeaderText(null);
    alert.setContentText(name + " error. Please enter correct credentials.");
    if (name.equals("Registration")){
      alert.setContentText("Username already taken. Please try another one.");
    }
    alert.showAndWait();
  }

  public static boolean stringValidationCheck(String name){
    char[] chars = name.toCharArray();
    for (int i = 0; i < chars.length; i++){
      if (chars[i] == 'd'){

      }
    }
    return false;
  }

  public static boolean isValidUserName(String userName){
    if ((userName.length() >= 4) && (userName.indexOf(" ") == -1) && (userName == userName.toLowerCase())){
      return true;
    }
    return false;
  }

  public static boolean isValidPassword(String password){
    Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
    if ((password.length() >= 6) && textPattern.matcher(password).matches() && password.indexOf(" ") != -1){
      return true;
    }
    return false;
  }

  public static void userNameExistsError(String name){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(name + " already exists. Please enter alternate username");
    alert.showAndWait();
  }
}