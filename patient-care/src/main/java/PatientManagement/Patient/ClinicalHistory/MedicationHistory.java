
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;
import java.util.List;

public class MedicationHistory {

    private List<Medication> list = new ArrayList<Medication>();

    public void RecordMedication(Medication medication)
    {
        this.list.add(medication);
    }
}
