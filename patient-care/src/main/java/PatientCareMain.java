
import PatientManagement.Clinic.*;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class PatientCareMain {

    private Clinic clinic =  new Clinic("Northeastern University Clinic.");;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void RegisterSite() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the GPS of clinic (N 40 44.9064, W 73 59.0735)\n");
        String gps = scanner.nextLine();

        clinic.newSite(new Location(gps));
        clinic.SetCurrentLocation(gps);

        System.out.println("Location registration completed!");

        System.out.println("\nPress any key to continue ...");
        System.in.read();
    }

    public void RegisterPatient() throws IOException
    {
        var personDirectory = clinic.getPersondirectory();
        var patientDirectory = clinic.getPatientdirectory();

        var scanner = new Scanner(System.in);
        System.out.println("\nPlease the patient's name (John Doe)\n");
        String name = scanner.nextLine();

        System.out.println("\nPlease the patient's SSN (eg 821-897-0898)\n");
        String ssn = scanner.nextLine();

        System.out.println("\nPlease the patient's age (eg 23)\n");
        int age = scanner.nextInt();

        Person p = personDirectory.newPerson(name, ssn, age);

        patientDirectory.registerPatient(p, this.clinic);

        System.out.println("Patient registration completed!");
        System.out.println("\nPress any key to continue ...");
        System.in.read();
    }

    public void RegisterEncounter() throws IOException {
        var personDirectory = clinic.getPersondirectory();

        var scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the patient's SSN\n");
        String ssn = scanner.nextLine();

        if (personDirectory.findPerson(ssn) == null) {
            System.out.println("\nThe patient has not been registered in the system yet, please register this patient first.\n");
            return;
        }

        var patientDic = clinic.getPatientdirectory();
        Patient patient = patientDic.GetPatientBySSN(ssn);

        //A chief complaint is the primary reason a patient seeks medical attention, typically described in their own words.
        // It is the most significant symptom or concern that leads a person to consult a healthcare professional, such as a doctor or nurse.
        System.out.println("\nPlease enter the patient's chief complaint \n");
        String chiefComplaint = scanner.nextLine();

        System.out.println("\nPlease enter the patient's budgetCode \n");
        String budgetCode = scanner.nextLine();

        Encounter encounter = patient.getEncounterHistory().newEncounter(patient,chiefComplaint, new Event(new Site(new Location(this.clinic.getCurrentSite())),budgetCode));

        System.out.println("\nPlease enter your check result on the patient for HIV. Yes means Positive, otherwise means negative.\n");
        boolean isHIVPositive = scanner.nextLine().equalsIgnoreCase("Yes");

        encounter.newDiagnosis("HIV", isHIVPositive);

        System.out.println("\nPlease enter the medicine name for the patient.\n");
        String medicineName = scanner.nextLine();

        System.out.println("\nPlease enter the limit for the medicine.\n");
        int limit = scanner.nextInt();
        encounter.addNewVitals(medicineName, limit);

        System.out.println("Encounter registration completed!");

        System.out.println("\nPress any key to continue ...");
        System.in.read();
    }

    public void LookupRecords() throws IOException {
        System.out.println("\nPlease enter the patient's SSN\n");
        var scanner = new Scanner(System.in);
        String ssn = scanner.nextLine();

        var patient = clinic.getPatientdirectory().GetPatientBySSN(ssn);
        if( patient == null) {

            System.out.println("\nThe system doesn't have this patient's records \n");
            return;
        }
        var history = patient.getEncounterHistory();

        if(history.getEncounterList() == null)
        {
            System.out.println("\nThe system doesn't have encounter for this patient. \n");
            return;
        }

        String leftAlignFormat = "%10s %30s %20s %5s %5s";
        int repeatTimes = 100;
        System.out.println(String.valueOf('-').repeat(repeatTimes));
        System.out.format(leftAlignFormat, "%10s %30s %20s %5s %5s", "Record Date", "Chief Complaint", "Category Name", "Is Confirmed ");
        System.out.println();
        System.out.println(String.valueOf('-').repeat(repeatTimes));

        for (var encounter : history.getEncounterList()) {
            System.out.format(leftAlignFormat,
                    encounter.getRecordDate().toString(),
                    encounter.getChiefComplaint(),
                    encounter.getDiagnosis().getCategory(),
                    (encounter.getDiagnosis().isConfirmed() ? "Yes" : "No"));
        }
        System.out.println(String.valueOf('-').repeat(repeatTimes));

        System.out.println("\nPress any key to continue ...");
        System.in.read();
    }

    public void showAllPatients() throws IOException {
        var pd = this.clinic.getPatientdirectory();

        String leftAlignFormat = "%30s %30s %20s %15s %25s\n";
        int repeatTimes = 150;
        System.out.println(String.valueOf('-').repeat(repeatTimes));
        System.out.format(leftAlignFormat, "Name", "SSN", "Age", "Encounter(s)", "Last Visited Date");
        System.out.println(String.valueOf('-').repeat(repeatTimes));

        for (var patient : pd.getAllPatients()) {
            System.out.format(leftAlignFormat,
                    patient.getPerson().getName(),
                    patient.getPerson().getPersonId(),
                    patient.getPerson().getAge(),
                    patient.getEncounterHistory().getEncounterList().size(),
                    patient.getEncounterHistory().getEncounterList().get(
                            patient.getEncounterHistory().getEncounterList().size() -1).getRecordDate());
        }
        System.out.println(String.valueOf('-').repeat(repeatTimes));

        System.out.println("Press any key to continue ...");
        System.in.read();
    }



    public int showWelcomeScreen() throws InterruptedException {

        // clear the console
        System.out.print("\033[H\033[2J");

        // print the menu
        System.out.println("\n************************************************************\n");
        System.out.println("*   Welcome to use the PatientCare Management System       *");
        System.out.println("*   Location: " + this.clinic.getCurrentSite());
        System.out.println("\n************************************************************\n");
        System.out.println("Please choose the one of following number to start. \n(Press 0 to exit the system)\n");
        System.out.println("-------------------------");

        System.out.println("1. Set current location");
        System.out.println("2. Register Patient");
        System.out.println("3. Lookup Records.");
        System.out.println("4. Register Encounter");
        System.out.println("5. Display Registered Patients.");
        System.out.println("6. Show Number of Infection Incidents by Location");
        System.out.println("7. Display Infected People By A patient");
        System.out.println("8. Show Infectious Trend.");
        System.out.println("9. Request Help from Partner Clinics.");
        System.out.println("0. Exit.");
        System.out.println("-------------------------");

        System.out.print("\nPlease enter the number => :");
        // read the user's input
        Scanner scanner = new Scanner(System.in);
        int choice = 0;  //initialize the variable
        try {
            choice = scanner.nextInt();
            // The number the user entered is not correct, we create a new Exception, so the catch statement can execute some
            // business logical code.
            if(choice < 0 ||choice > 9)
                 throw new InputMismatchException();

        } catch (InputMismatchException ex){

            System.err.println("Please enter a correct number. ");
            Thread.sleep(1000 * 3);
            showWelcomeScreen();
        }

        return choice;
    }

    public void disPlayInfectedByPatient() throws IOException {
        System.out.print("\nPlease enter the patient's SSN => :");
        // read the user's input
        Scanner scanner = new Scanner(System.in);
        String ssn = scanner.nextLine();
        Patient p = clinic.getPatientdirectory().GetPatientBySSN(ssn);
        if(p == null) {
            System.out.print("\nThe system doesn't have records for this patient.");
            return;
        }
        List<Patient> infectedPeople = clinic.getPatientsByIntectedPatient(p);

        System.out.println("Patient Name:" + p.getPerson().getName());
        System.out.println("Patient's SSN:" + p.getPerson().getPersonId());

        System.out.println("\nNnfected the following person or people:\n");

        String leftAlignFormat = "%30s %30s %20s %25s\n";
        int repeatTimes = 120;
        System.out.println(String.valueOf('-').repeat(repeatTimes));
        System.out.format(leftAlignFormat, "Name", "SSN", "Age", "Last Visited Date");
        System.out.println(String.valueOf('-').repeat(repeatTimes));

        for (var patient : infectedPeople) {
            System.out.format(leftAlignFormat,
                    patient.getPerson().getName(),
                    patient.getPerson().getPersonId(),
                    patient.getPerson().getAge(),
                    patient.getEncounterHistory().getEncounterList().get(
                            patient.getEncounterHistory().getEncounterList().size() -1).getRecordDate());
        }
        System.out.println(String.valueOf('-').repeat(repeatTimes));

        System.out.println("Press any key to continue ...");
        System.in.read();

    }

    public void ShowInfectedNumberByLocation() throws IOException {
        System.out.println("Available locations:");
        System.out.println("---------------------------------");
        for(var loc : this.clinic.getLocationList().getAllLocations()) {
            System.out.println(loc.getGps());
        }
        System.out.println("---------------------------------");
        System.out.print("\nPlease enter the location => :");
        // read the user's input
        Scanner scanner = new Scanner(System.in);
        String loc = scanner.nextLine();

        System.out.println("\nThe infected number is:" + clinic.getInfectedNumber());

        System.out.println("Press any key to continue ...");
        System.in.read();
    }


    public void showTrend() throws IOException {
        List<LocalDate> dates = clinic.getAllDates();

        String leftAlignFormat = "%10s %10s %-50s %10s\n";
        int repeatTimes = 120;
        System.out.println(String.valueOf('-').repeat(repeatTimes));
        System.out.format(leftAlignFormat, "Date", "Number", "Bar", "Trend");
        int min = 0;
        int max = 50;
        Random random = new Random();
        int lastValue = 5;
        for(var currentDate : dates) {
            int randomInt = random.nextInt(max - min + 1) + min;
            String trendString = "";

            if(randomInt < lastValue)
                trendString = Character.toString(8595);
            else if (randomInt > lastValue)
                trendString = Character.toString(8593);
            else
                trendString = "  - ";
            System.out.format(leftAlignFormat,
                    currentDate,
                    randomInt+"",
                    String.valueOf('|').repeat(randomInt),
                    trendString);
            lastValue = randomInt;
        }

        System.out.println(String.valueOf('-').repeat(repeatTimes));
        System.out.println("\nPress any key to continue ...");
        System.in.read();
    }

    public void requestHelpFromClinics() throws IOException {

        Faker fake = new Faker();

        String leftAlignFormat = "%20s %60s %15s %20s \n";
        int repeatTimes = 120;
        System.out.println(String.valueOf('-').repeat(repeatTimes));
        System.out.format(leftAlignFormat, "Clinic Name", "Address", "Availability", "Responded Date");
        System.out.println(String.valueOf('-').repeat(repeatTimes));
        System.out.format(leftAlignFormat, "Cegis Clinic", fake.address().fullAddress(),"Available", "04/21/2023");
        System.out.format(leftAlignFormat, "UofA Clinic", fake.address().fullAddress(), "Busy", "04/21/2023");
        System.out.format(leftAlignFormat, "Wuyun Clinic", fake.address().fullAddress(), "Busy", "04/21/2023");
        System.out.format(leftAlignFormat, "UCAL Clinic", fake.address().fullAddress(), "Busy", "04/21/2023");
        System.out.format(leftAlignFormat, "Massi Clinic", fake.address().fullAddress(), "Available", "04/20/2023");
        System.out.format(leftAlignFormat, "Jesse Clinic", fake.address().fullAddress(), "Available", "04/19/2023");
        System.out.format(leftAlignFormat, "Gama Clinic", fake.address().fullAddress(), "Busy", "04/21/2023");

        System.out.println(String.valueOf('-').repeat(repeatTimes));

        System.out.println("Press any key to continue ...");
        System.in.read();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // PersonDirectory pd = new PersonDirectory();
        // Person p = pd.newPerson("ssn"); // the focus or root of the family
        // Person mom = pd.newPerson("ssn2");
        // Person dad = pd.newPerson("ssn3");
        // Person sis = pd.newPerson("ssn4");
        // Person bro = pd.newPerson("ssn5");

        // link a persons into a family

        // p.setFather(dad);
        // p.setMother(mom);
        // p.addSibling(sis);
        // p.addSibling(bro);
        // Person momsfather = pd.newPerson("ssn6");
        // mom.setFather(momsfather);
        // Person momsmom = pd.newPerson("SSN7)");
        // mom.setMother(momsmom);

        // Clinic clinic = new Clinic("");
        // ...
        var generator = new MockDataGenerator();

        PatientCareMain main = new PatientCareMain();
        generator.GenerateMockData(main.clinic);

        int userchoice = main.showWelcomeScreen();

        while(true)
        {
            switch (userchoice){
                case 0:        //Switch clinic
                    System.exit(0);
                    break;
                case 1:        // Create Clinic
                    main.RegisterSite();
                    break;
                case 2:        // Register Patient
                    main.RegisterPatient();
                    break;
                case 3:        //Lookup Records
                    main.LookupRecords();
                    break;
                case 4:       //Register Encounter
                    main.RegisterEncounter();
                    break;
                case 5:       //Show all registered patient
                    main.showAllPatients();
                    break;

                case 6:
                    main.ShowInfectedNumberByLocation();
                    break;

                case 7:
                    main.disPlayInfectedByPatient();
                    break;

                case 8:
                    main.showTrend();
                    break;
                case 9:
                    main.requestHelpFromClinics();
                    break;
                default:
                    return;
            }
            userchoice = main.showWelcomeScreen();
        }
    }
}
