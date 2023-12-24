
package PatientManagement.Clinic;

import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;

import java.util.ArrayList;
import java.util.List;

public class PatientDirectory {
    Clinic clinic;
    ArrayList<Patient> patients;

    PatientDirectory(Clinic clinic) {
        this.clinic = clinic;
        patients = new ArrayList<Patient>();
    }

    public Patient registerPatient(Person p, Clinic clinic) {
        Patient patient = new Patient(p, clinic);
        patients.add(patient);

        return patient;
    }

    public Patient GetPatientBySSN(String ssn) {

        for (var patient : patients) {
            if(patient.getPerson().getPersonId().equals(ssn))
                return patient;
        }
        return null;
    }

    public List<Patient> getAllPatients() {
        return this.patients;
    }

    public int getConfirmedPositiveTotals() {
        int sum = 0;

        for (Patient p : patients) {
            if (p.isConfirmedPositive()) {
                sum = sum + 1;
            }
        }
        return sum;

    }

    public ArrayList<Patient> getAllConfirmedPositives() {
        ArrayList<Patient> temp = new ArrayList<Patient>();
        for (Patient p : patients) {
            if (p.isConfirmedPositive() == true) {
                temp.add(p);
            }
        }


        return temp; // has the list of encounters with confirmed diagnosis
    }

}