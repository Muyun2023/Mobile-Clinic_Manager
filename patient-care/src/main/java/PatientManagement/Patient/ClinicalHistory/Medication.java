
package PatientManagement.Patient.ClinicalHistory;

import java.util.Date;


public class Medication {

    private String name;     // The name of the Medication

    private Date DateTaken;  // The date that the patient took the medicine.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
