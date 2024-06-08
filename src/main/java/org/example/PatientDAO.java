package org.example;

import org.example.DatabaseConfig;
import org.example.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patients (taj, name, birth_year, surgery_type, surgeon) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getTaj());
            stmt.setString(2, patient.getName());
            stmt.setInt(3, patient.getBirthYear());
            stmt.setString(4, patient.getSurgeryType());
            stmt.setString(5, patient.getSurgeon());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patient getPatientByTaj(String taj) {
        String sql = "SELECT * FROM patients WHERE taj = ?";
        Patient patient = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, taj);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient(
                        rs.getLong("id"),
                        rs.getString("taj"),
                        rs.getString("name"),
                        rs.getInt("birth_year"),
                        rs.getString("surgery_type"),
                        rs.getString("surgeon")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    public boolean deletePatient(String taj) {
        String sql = "DELETE FROM patients WHERE taj = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, taj);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM patients";
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getLong("id"),
                        rs.getString("taj"),
                        rs.getString("name"),
                        rs.getInt("birth_year"),
                        rs.getString("surgery_type"),
                        rs.getString("surgeon")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
}
