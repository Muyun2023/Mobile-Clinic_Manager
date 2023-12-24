
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;


public class AlergyHistory {
    ArrayList<Alergy> alergies;

    public AlergyHistory() {
        alergies = new ArrayList<Alergy>();
    }

    public void AddAlgery(Alergy alergy) {
        alergies.add(alergy);
    }
}
