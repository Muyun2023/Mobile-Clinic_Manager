
package PatientManagement.Patient.Encounters;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Patient;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


// vorder = encounter.newVaccinationOrder();
// vacorder.newVaccination();

public class Encounter {
    LocalDate encounterDate;
    Clinic clinic;
    Patient patient;
    String chiefComplaint;
    Diagnosis diagnosis;
    Event event;
    VitalSigns vitalSigns;
    // vital signs
    // orders: assessmentorders, ....

    public static int counter = 0;
    public Encounter(Patient p, String cc, Event ev, Clinic c) { // event is the date when the check was made
        counter += 1;
        chiefComplaint = cc;
        event = ev;
        patient = p;

        if(counter % 3 ==0)
            encounterDate = LocalDate.now().plusDays(-1);
        else
            encounterDate = LocalDate.now();
        clinic = c;
        vitalSigns = new VitalSigns(patient, this);
    }

    public void newDiagnosis(String diseasetype, boolean confirmed) {
        diagnosis = new Diagnosis(diseasetype, confirmed);
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public LocalDate getRecordDate()
    {

        return this.encounterDate;
    }

    public Limits getVitalSignLimits(int age, String name) {
        VitalSignsCatalog vsc = clinic.getVitalSignsCatalog();
        return vsc.findVitalSignLimits(age, name);
    }

    public String getChiefComplaint() {
        return this.chiefComplaint;
    }

    public VitalSignMetric addNewVitals(String name, int value) {
        return vitalSigns.addNewVitals(name, value);
    }
}