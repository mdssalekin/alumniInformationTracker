package app.Controller;

import app.database.DatabaseHandler;
import app.database.UserAuth;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

import static app.Controller.Operations.loadingGifPlay;

public class Login {
  private static DatabaseHandler databaseHandler = new DatabaseHandler();

  @FXML
  private JFXTextField loginUserName;

  @FXML
  private JFXPasswordField loginPassword;

  @FXML
  private JFXButton loginButton;

  @FXML
  private JFXButton signUpButton;

  @FXML
  private ImageView loadingIcon;

  @FXML
  void initialize() {
    loadingIcon.setStyle("visibility: hidden");
    loginButton.setOnAction(actionEvent -> {
      String userName = loginUserName.getText();
      String password = loginPassword.getText();
      if (UserAuth.login(userName, password)){
        loginButton.setStyle("visibility: hidden;");
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                  loadingGifPlay(loadingIcon);;
                }),
                new KeyFrame(Duration.seconds(1.0), event -> {
                  try {
                    sceneChange("AdminDashboard.fxml", loginButton);
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                })
        );
        timeline.play();
      }
    });

    signUpButton.setOnAction(actionEvent -> {
      try {
        sceneChange("Register.fxml", signUpButton);
      } catch (IOException e) {
        e.printStackTrace();
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