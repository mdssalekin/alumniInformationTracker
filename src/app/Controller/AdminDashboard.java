package app.Controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import app.Model.Alumni;
import app.Model.Date;
import app.Model.Organization;
import app.database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class AdminDashboard {
  private static final DatabaseHandler databaseHandler = new DatabaseHandler();
  private static final Date date = new Date();

  @FXML
  private Pane insertPane;

  @FXML
  private Pane updatePane;

  @FXML
  private TextField searchField;

  @FXML
  private TextField updateTextField;

  @FXML
  private TableView tableView;

  @FXML
  private JFXButton updateAlumni;

  @FXML
  private JFXButton deleteAlumni;

  @FXML
  private JFXTextField updateFirstName;

  @FXML
  private JFXTextField updateUserName;

  @FXML
  private JFXTextField updateLastName;

  @FXML
  private JFXTextField updateEmail;

  @FXML
  private JFXTextField updateEmailConfirm;

  @FXML
  private JFXDatePicker updateDOB;

  @FXML
  private JFXTextField updateGender;

  @FXML
  private JFXTextField updateContactNo;

  @FXML
  private JFXTextField updateDepartment;

  @FXML
  private JFXTextField updateStudentID;

  @FXML
  private JFXDatePicker updateGraduationYear;

  @FXML
  private JFXPasswordField updatePassword;

  @FXML
  private JFXTextField updateOName;

  @FXML
  private JFXTextField updateDesignation;

  @FXML
  private JFXTextField updateODepartment;

  @FXML
  private JFXPasswordField updatePasswordConfirm;

  @FXML
  private JFXTextField updatePresentAddress;

  @FXML
  private JFXTextField updatePermanentAddress;

  @FXML
  private JFXDatePicker updateJoinedDate;

  @FXML
  private Label deleteSuccess;

  @FXML
  private Label updateSuccess;

  @FXML
  private Label enterAlumni;

  @FXML
  private JFXTextField registerFirstName;

  @FXML
  private JFXTextField registerLastName;

  @FXML
  private JFXTextField registerEmail;

  @FXML
  private JFXTextField registerEmailConfirm;

  @FXML
  private JFXDatePicker registerDOB;

  @FXML
  private JFXTextField registerGender;

  @FXML
  private JFXTextField registerContactNumber;

  @FXML
  private JFXTextField registerPresentAddress;

  @FXML
  private JFXTextField registerPermanentAddress;

  @FXML
  private JFXTextField registerDepartment;

  @FXML
  private JFXTextField registerStudentID;

  @FXML
  private JFXDatePicker registerGraduationYear;

  @FXML
  private JFXTextField registerUsername;

  @FXML
  private JFXPasswordField registerPassword;

  @FXML
  private JFXPasswordField registerPasswordConfirm;

  @FXML
  private JFXTextField registerOName;

  @FXML
  private JFXTextField registerDesignation;

  @FXML
  private JFXTextField registerODepartment;

  @FXML
  private JFXButton createAlumni;

  @FXML
  private TableColumn<Alumni, String> columnFirstName;
  @FXML
  private TableColumn<Alumni, String> columnLastName;
  @FXML
  private TableColumn<Alumni, String> columnEmail;
  @FXML
  private TableColumn<Alumni, String> columnGender;
  @FXML
  private TableColumn<Alumni, String> columnPresentAddress;
  @FXML
  private TableColumn<Alumni, String> columnContactNo;
  @FXML
  private TableColumn<Alumni, String> columnDepartment;
  @FXML
  private TableColumn<Alumni, String> columnStudentID;
  @FXML
  private TableColumn<Alumni, Date> columnGraduationYear;
  @FXML
  private TableColumn<Alumni, Date> columnJoinedAsAlumni;
  @FXML
  private TableColumn<Alumni, Organization> columnOrganization;

  @FXML
  void initialize() {
    columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    columnPresentAddress.setCellValueFactory(new PropertyValueFactory<>("presentAddress"));
    columnContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
    columnDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
    columnStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
    columnGraduationYear.setCellValueFactory(new PropertyValueFactory<>("graduationYear"));
    columnJoinedAsAlumni.setCellValueFactory(new PropertyValueFactory<>("joinedAsAlumni"));
    columnOrganization.setCellValueFactory(new PropertyValueFactory<>("organization"));

    displayTableContent(databaseHandler.viewAllAlumni());
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      tableView.getItems().clear();
      if (!newValue.equals("")){
        displaySearchItem(newValue);
      } else {
        displayTableContent(databaseHandler.viewAllAlumni());
      }
    });

    createAlumni.setOnAction(actionEvent -> {
      if (!isEmptyInputFields()){
        if (!inputsUnmatched()){
          databaseHandler.createAlumni(new Alumni(
                  registerFirstName.getText(),
                  registerLastName.getText(),
                  registerEmail.getText(),
                  registerGender.getText(),
                  new Date(registerDOB.getValue().toString()),
                  registerContactNumber.getText(),
                  registerPresentAddress.getText(),
                  registerPermanentAddress.getText(),
                  "Alumni",
                  registerUsername.getText(),
                  registerPassword.getText(),
                  registerDepartment.getText(),
                  registerStudentID.getText(),
                  new Date(registerGraduationYear.getValue().toString()),
                  date.generateCurrentDate(),
                  new Organization(registerOName.getText(), registerDesignation.getText(), registerODepartment.getText())
          ));
          tableView.getItems().clear();
          displayTableContent(databaseHandler.viewAllAlumni());
          Timeline timeline = new Timeline(
                  new KeyFrame(Duration.ZERO, event -> {
                    insertPane.setStyle("visibility: hidden");
                    enterAlumni.setText("Alumni information successfully stored");
                  }),
                  new KeyFrame(Duration.seconds(2.0), event -> {
                    enterAlumni.setText("Enter Alumni Information:");
                    insertPane.setStyle("visibility: visible");
                  })
          );
          timeline.play();
          emptyInsertInputFields();
        }
      }
    });

    AtomicReference<Alumni> name = new AtomicReference<>();
    updatePane.setStyle("visibility: hidden");
    updateTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.equals("")){
        Alumni alumni = setUpdateTextField(newValue);
        if (alumni != null){
          name.set(alumni);
          updateSuccess.setStyle("visibility: hidden");
          deleteSuccess.setStyle("visibility: hidden");
          updatePane.setStyle("visibility: visible");
        } else {
          name.set(null);
          emptyUpdateInputFields();
          updatePane.setStyle("visibility: hidden");
          updateSuccess.setStyle("visibility: hidden");
          deleteSuccess.setStyle("visibility: hidden");
        }
      } else {
        updatePane.setStyle("visibility: hidden");
        updateSuccess.setStyle("visibility: hidden");
        deleteSuccess.setStyle("visibility: hidden");
      }
    });
    updateAlumni.setOnAction(actionEvent -> {
      try {
        databaseHandler.updateAlumni(getUpdatedAlumni());
        emptyUpdateInputFields();
        changeLabel(updateSuccess);
        updateTextField.setText("");
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    });
    deleteAlumni.setOnAction(actionEvent -> {
      try {
        if (databaseHandler.deleteAlumni(name.get().getUserName())){
          changeLabel(deleteSuccess);
          updateTextField.setText("");
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    });
  }

  private void displaySearchItem(String name) {
    Alumni alumni = databaseHandler.searchAlumni(name);
    if (alumni == null){
      tableView.setPlaceholder(new Label("No matched item!"));
    } else {
      tableView.getItems().add(
              new Alumni(
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
              )
      );
    }
  }

  private void displayTableContent(HashMap<String, Alumni> hashMap){
    for (Map.Entry<String, Alumni> entry: hashMap.entrySet()){
      tableView.getItems().add(
              new Alumni(
                      entry.getValue().getFirstName(),
                      entry.getValue().getLastName(),
                      entry.getValue().getEmail(),
                      entry.getValue().getGender(),
                      entry.getValue().getPresentAddress(),
                      entry.getValue().getContactNumber(),
                      entry.getValue().getDepartment(),
                      entry.getValue().getStudentID(),
                      entry.getValue().getGraduationYear(),
                      entry.getValue().getJoinedAsAlumni(),
                      entry.getValue().getOrganization()
              )
      );
    }
  }

  private Alumni setUpdateTextField(String alumniName){
    Alumni alumni = databaseHandler.searchForUpdateAlumni(alumniName);
    if (alumni != null){
      updateFirstName.setText(alumni.getFirstName());
      updateLastName.setText(alumni.getLastName());
      updateEmail.setText(alumni.getEmail());
      updateEmailConfirm.setText(alumni.getEmail());
      updateGender.setText(alumni.getGender());
      updateDOB.setValue(
              LocalDate.of(
                      Integer.parseInt(alumni.getDateOfBirth().getYear()),
                      Integer.parseInt(alumni.getDateOfBirth().getMonth()),
                      Integer.parseInt(alumni.getDateOfBirth().getDay())
              )
      );
      updateContactNo.setText(alumni.getContactNumber());
      updateDepartment.setText(alumni.getDepartment());
      updateStudentID.setText(alumni.getStudentID());
      updateUserName.setText(alumni.getUserName());
      updatePassword.setText(alumni.getPassword());
      updatePasswordConfirm.setText(alumni.getPassword());
      updateGraduationYear.setValue(
              LocalDate.of(
                      Integer.parseInt(alumni.getGraduationYear().getYear()),
                      Integer.parseInt(alumni.getGraduationYear().getMonth()),
                      Integer.parseInt(alumni.getGraduationYear().getDay())
              )
      );
      updateJoinedDate.setValue(
              LocalDate.of(
                      Integer.parseInt(alumni.getJoinedAsAlumni().getYear()),
                      Integer.parseInt(alumni.getJoinedAsAlumni().getMonth()),
                      Integer.parseInt(alumni.getJoinedAsAlumni().getDay())
              )
      );
      updatePresentAddress.setText(alumni.getPresentAddress());
      updatePermanentAddress.setText(alumni.getPermanentAddress());
      updateOName.setText(alumni.getOrganization().getName());
      updateODepartment.setText(alumni.getOrganization().getDepartment());
      updateDesignation.setText(alumni.getOrganization().getDesignation());
    } else {
      updatePane.setStyle("visibility: hidden");
    }
    return alumni;
  }

  private Alumni getUpdatedAlumni(){
    Alumni tempAlumni = new Alumni(
            updateFirstName.getText(),
            updateLastName.getText(),
            updateEmail.getText(),
            updateGender.getText(),
            new Date(updateDOB.getValue().toString()),
            updateContactNo.getText(),
            updatePresentAddress.getText(),
            updatePermanentAddress.getText(),
            "Alumni",
            updateUserName.getText(),
            updatePassword.getText(),
            updateDepartment.getText(),
            updateStudentID.getText(),
            new Date(updateGraduationYear.getValue().toString()),
            new Date(updateJoinedDate.getValue().toString()),
            new Organization(updateOName.getText(), updateODepartment.getText(), updateDesignation.getText())
    );
    return tempAlumni;
  }

  private void changeLabel(Label deleteSuccess) {
    tableView.getItems().clear();
    displayTableContent(databaseHandler.viewAllAlumni());
    updatePane.setStyle("visibility: hidden");
    Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, event -> {
              deleteSuccess.setStyle("visibility: visible");
            }),
            new KeyFrame(Duration.seconds(2.0), event -> {
              deleteSuccess.setStyle("visibility: hidden");
            })
    );
    timeline.play();
  }

  private void emptyUpdateInputFields(){
    makeInputFieldsEmpty(
            updateFirstName,
            updateLastName,
            updateEmail,
            updateEmailConfirm,
            updateGender,
            updateDOB,
            updateContactNo,
            updatePresentAddress,
            updatePermanentAddress,
            updateDepartment,
            updateStudentID,
            updateGraduationYear
    );
    updateJoinedDate.setValue(null);
    updateUserName.setText("");
    updatePassword.setText("");
    updatePasswordConfirm.setText("");
    updateDesignation.setText("");
    updateODepartment.setText("");
    updateOName.setText("");
  }

  private void makeInputFieldsEmpty(
          JFXTextField updateFirstName,
          JFXTextField updateLastName,
          JFXTextField updateEmail,
          JFXTextField updateEmailConfirm,
          JFXTextField updateGender,
          JFXDatePicker updateDOB,
          JFXTextField updateContactNo,
          JFXTextField updatePresentAddress,
          JFXTextField updatePermanentAddress,
          JFXTextField updateDepartment,
          JFXTextField updateStudentID,
          JFXDatePicker updateGraduationYear
  ) {
    updateFirstName.setText("");
    updateLastName.setText("");
    updateEmail.setText("");
    updateEmailConfirm.setText("");
    updateGender.setText("");
    updateDOB.setValue(null);
    updateContactNo.setText("");
    updatePresentAddress.setText("");
    updatePermanentAddress.setText("");
    updateDepartment.setText("");
    updateStudentID.setText("");
    updateGraduationYear.setValue(null);
  }

  private void emptyInsertInputFields(){
    makeInputFieldsEmpty(
            registerFirstName,
            registerFirstName,
            registerEmail,
            registerEmailConfirm,
            registerGender,
            registerDOB,
            registerContactNumber,
            registerPresentAddress,
            registerPermanentAddress,
            registerDepartment,
            registerStudentID,
            registerGraduationYear
    );
    registerUsername.setText("");
    registerPassword.setText("");
    registerPasswordConfirm.setText("");
    registerDesignation.setText("");
    registerODepartment.setText("");
    registerOName.setText("");
  }

  private boolean isEmptyInputFields(){
    if (
          registerFirstName.getText().isEmpty() ||
          registerLastName.getText().isEmpty() ||
          registerEmail.getText().isEmpty() ||
          registerEmailConfirm.getText().isEmpty() ||
          registerGender.getText().isEmpty() ||
          registerDOB.getValue().toString().isEmpty() ||
          registerContactNumber.getText().isEmpty() ||
          registerPresentAddress.getText().isEmpty() ||
          registerPermanentAddress.getText().isEmpty() ||
          registerDepartment.getText().isEmpty() ||
          registerStudentID.getText().isEmpty() ||
          registerGraduationYear.getValue().toString().isEmpty() ||
          registerUsername.getText().isEmpty() ||
          registerPassword.getText().isEmpty() ||
          registerPasswordConfirm.getText().isEmpty() ||
          registerOName.getText().isEmpty() ||
          registerODepartment.getText().isEmpty() ||
          registerDesignation.getText().isEmpty()
    ) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Empty inputs");
      alert.setHeaderText(null);
      alert.setContentText("Enter all inputs");
      alert.showAndWait();
      return true;
    } else {
      return false;
    }
  }

  private boolean inputsUnmatched(){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Unmatched inputs");
    alert.setHeaderText(null);
    if (!registerEmail.getText().equals(registerEmailConfirm.getText())){
      alert.setContentText("Emails do not match");
      alert.showAndWait();
      return true;
    } else if (!registerPassword.getText().equals(registerPasswordConfirm.getText())){
      alert.setContentText("Password do not match");
      alert.showAndWait();
      return true;
    } else {
      return false;
    }
  }

}