package PatientManagement.Clinic;

import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

/**
 * We use this class to generate mock data for the clinic. Then shortly, we could look up some records and
 * mock the infectious trend in our system
 */
public class MockDataGenerator {

    public void GenerateMockData(Clinic clinic) {
        clinic.newSite(new Location("12N 24W"));  // for the simple purpose

        var personDirectory = clinic.getPersondirectory();
        var patientDirectory = clinic.getPatientdirectory();

        //Generate person data
        var currentPerson = personDirectory.newPerson("Walter Jake", "228-94-9595", 36);
        currentPerson.setFather(
                new Person("Walter Smith", "228-94-9596", 52)
        );
        currentPerson.setMother(
                new Person("Jessica Whatscott", "228-34-9591", 50)
        );
        currentPerson.addSibling(
                new Person("Stark Iva", "228-94-9595", 29)
        );

        var patient = patientDirectory.registerPatient(currentPerson, clinic);

        var encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Walter Jake", "216-90-8872", 25);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        encounter.newDiagnosis("HIV", true);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Dibbert Ryan", "529-83-5843", 24);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Cronin Antwon", "543-92-6733", 34);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Sawayn Lindsey", "402-39-4730", 12);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        encounter.newDiagnosis("HIV", true);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Strosin Annamarie", "518-30-0684", 34);
        patient =  patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Towne Herman", "043-16-9119", 45);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        encounter.newDiagnosis("HIV", true);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Kautzer Korey", "307-08-9971", 78);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Schumm Marisol", "228-94-9390", 34);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Durgan Leda", "228-94-9595", 34);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("King Nickolas", "307-08-9971", 67);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        encounter.newDiagnosis("HIV", true);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson = personDirectory.newPerson("Muyun Ji", "250-09-6367", 28);
        patient = patientDirectory.registerPatient(currentPerson, clinic);
        encounter = new Encounter(patient,
                "Feel not well",
                new Event(new Site(new Location("W25 N25")),
                        "000"),
                clinic);
        patient.getEncounterHistory().getEncounterList().add(encounter);

        currentPerson.setMother(new Person("Guixiang Tang", "444-05-5883", 52));
        currentPerson.setFather(new Person("Zhiming Ji", "008-01-1538", 56));
        currentPerson.setFather(new Person("Weiyang Zhang", "228-94-1295", 35));

    }
}
