
package PatientManagement.Patient;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Patient.ClinicalHistory.AlergyHistory;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Persona.Person;
import java.util.ArrayList;


public class Patient {
    Clinic clinic;
    EncounterHistory encounterhistory;
    VaccinationHistory vaccinateionHistory;
    Person person;
    AlergyHistory alergyhistory;



    public Patient(Person p, Clinic clinic) {
        vaccinateionHistory = new VaccinationHistory();
        encounterhistory = new EncounterHistory(this, clinic); // link this patient object back
        person = p;
        this.clinic = clinic;
    }

    public Person getPerson() {
        return this.person;
    }


    public VaccinationHistory getVaccinationHistory() {return this.vaccinateionHistory;}
    public EncounterHistory getEncounterHistory() {
        return encounterhistory;
    }
    // the below method will return the encounter where the infection occured
    // from the returned encounter you pull the event, the site, etc.

    public Encounter getConfirmedEncounter() {
        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag.isConfirmed()) {
                return currentencounter;// send back the whole encounter so we extract event and site
            }
        }
        return null;
    }

    public boolean isConfirmedPositive() {

        ArrayList<Encounter> patientencounterlist = encounterhistory.getEncounterList();

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if(diag == null) return false;
            return diag.isConfirmed();

        }
        return false;
    }

}
