
package PatientManagement.Clinic;

import PatientManagement.Catalogs.DrugCatalog;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.PersonDirectory;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Clinic {
    PatientDirectory patientdirectory;
    PersonDirectory persondirectory;
    SiteCatalog sitelist;
    LocationList locationlist;
    DrugCatalog drugcatalog;
    EventSchedule eventschedule;
    VitalSignsCatalog vitalSignsCatalog;
    String name; // name of the clinic


    String currentSite = "";

    public Clinic(String n) {
        eventschedule = new EventSchedule();
        sitelist = new SiteCatalog();
        persondirectory = new PersonDirectory();
        patientdirectory = new PatientDirectory(this);
        vitalSignsCatalog = new VitalSignsCatalog();
        locationlist = new LocationList();
        name = n;
    }

    public SiteCatalog getSiteCatalog() {
        return sitelist;
    }

    public LocationList getLocationList() {
        return locationlist;
    }

    public String getCurrentSite()
    {
        return currentSite;
    }

    public void SetCurrentLocation(String gps)
    {
        this.currentSite = gps;
    }

    public Site newSite(Location location) {

        Site s = sitelist.newSite(location);

        locationlist.addNewLocation(location);
        return s;
    }

    public int getInfectedNumber() {
        int number = 0;
        for(var patient : patientdirectory.getAllPatients()) {
            if(patient.isConfirmedPositive()) number ++;
        }
        return number;
    }

    public List<Patient> getPatientsByIntectedPatient(Patient p) {

        List<Patient> infectedPeople = new ArrayList<Patient>();

        var encounterTimes = p.getEncounterHistory().getEncounterList().size();
        LocalDate lastVisitedDate =  p.getEncounterHistory().getEncounterList().get(encounterTimes -1).getRecordDate();

        for(var patient : patientdirectory.getAllPatients()) {
            /*
            encounterTimes = patient.getEncounterHistory().getEncounterList().size();
            //get the last encounter date
            LocalDate currentLastVisitedDate =  patient.getEncounterHistory().getEncounterList().get(encounterTimes -1).getRecordDate();

            if(lastVisitedDate == currentLastVisitedDate)
                infectedPeople.add(patient);*/
            if(patient.isConfirmedPositive())
                infectedPeople.add(patient);
        }

        return infectedPeople;
    }


    public List<LocalDate> getAllDates()
    {
        List<LocalDate> dates = new ArrayList<LocalDate>();

        for(int i=0 ; i < 15; i ++)
        {
            dates.add(LocalDate.now().plusDays(-i));
        }

        return dates;
    }

    public VitalSignsCatalog getVitalSignsCatalog() {
        return vitalSignsCatalog;
    }

    public PersonDirectory getPersondirectory() {
        return persondirectory;
    }

    public EventSchedule getEventschedule() {
        return eventschedule;
    }

    public PatientDirectory getPatientdirectory() {
        return patientdirectory;
    }

    public void setEventschedule(EventSchedule eventschedule) {
        this.eventschedule = eventschedule;
    }
}
