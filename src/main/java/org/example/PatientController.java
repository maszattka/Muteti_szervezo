package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.Patient;
import org.example.PatientService;

public class PatientController {

    @FXML
    private TextField tajNumberField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField birthYearField;
    @FXML
    private TextField surgeryTypeField;
    @FXML
    private TextField operatingDoctorField;
    @FXML
    private ListView<String> listView;

    private final PatientService patientService = new PatientService();
    private final ObservableList<String> patientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(patientList);
        loadPatientList();
    }

    @FXML
    private void addPatient(ActionEvent event) {
        String taj = tajNumberField.getText();
        String name = nameField.getText();
        int birthYear = Integer.parseInt(birthYearField.getText());
        String surgeryType = surgeryTypeField.getText();
        String surgeon = operatingDoctorField.getText();

        Patient patient = new Patient();
        patient.setTaj(taj);
        patient.setName(name);
        patient.setBirthYear(birthYear);
        patient.setSurgeryType(surgeryType);
        patient.setSurgeon(surgeon);

        patientService.addPatient(patient);
        loadPatientList();
        showAlert("Success", "Patient added successfully.");
        clearFields();
    }

    @FXML
    private void deletePatient(ActionEvent event) {
        String taj = tajNumberField.getText();
        boolean success = patientService.deletePatient(taj);

        if (success) {
            loadPatientList();
            showAlert("Success", "Patient deleted successfully.");
            clearFields();
        } else {
            showAlert("Error", "Patient not found.");
        }
    }

    @FXML
    private void searchPatient(ActionEvent event) {
        String taj = tajNumberField.getText();
        Patient patient = patientService.getPatientByTaj(taj);

        if (patient != null) {
            nameField.setText(patient.getName());
            birthYearField.setText(String.valueOf(patient.getBirthYear()));
            surgeryTypeField.setText(patient.getSurgeryType());
            operatingDoctorField.setText(patient.getSurgeon());
            showAlert("Success", "Patient found.");
        } else {
            showAlert("Error", "Patient not found.");
        }
    }

    private void loadPatientList() {
        patientList.clear();
        for (Patient patient : patientService.getAllPatients()) {
            patientList.add(patient.getTaj() + " - " + patient.getName());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        tajNumberField.clear();
        nameField.clear();
        birthYearField.clear();
        surgeryTypeField.clear();
        operatingDoctorField.clear();
    }
}
