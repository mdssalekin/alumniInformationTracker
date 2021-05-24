package app.Controller;

import app.database.UserAuth;
import com.jfoenix.controls.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static app.Controller.Operations.*;

public class Register {

  @FXML
  private JFXTextField registerUsername;

  @FXML
  private JFXTextField registerEmail;

  @FXML
  private JFXPasswordField registerPassword;

  @FXML
  private JFXButton signUpButton;

  @FXML
  private JFXButton goBackLogin;

  @FXML
  private ImageView loadingIcon;

  @FXML
  void initialize() {
    loadingIcon.setStyle("visibility: hidden");
    goBackLogin.setOnAction(actionEvent -> {
      try {
        String viewName = "Login.fxml";
        sceneChange(viewName, goBackLogin);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    signUpButton.setOnAction(actionEvent -> {
      String userName = registerUsername.getText();
      String email = registerEmail.getText();
      String password = registerPassword.getText();
      signUpButton.setDefaultButton(true);
      if (UserAuth.register(userName, email, password)){
        signUpButton.setStyle("visibility: hidden");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                  loadingGifPlay(loadingIcon);;
                }),
                new KeyFrame(Duration.seconds(2.0), event -> {
                  try {
                    sceneChange("AdminDashboard.fxml", signUpButton);
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                })
        );
        timeline.play();
      }
    });
  }

  private void sceneChange(String sceneName, JFXButton button) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/" + sceneName));
    Stage stage = (Stage) button.getScene().getWindow();
    Scene scene = new Scene(loader.load());
    stage.setScene(scene);
    stage.centerOnScreen();
  }
}
