package election.citizen;


import election.Party;

import java.util.Random;
import java.util.Set;

public class AdultCitizen extends Human implements Comparable<Human>{
    public AdultCitizen(String name, int passportNumber, long innCode, String bornDate, int electionDistrictAttachNumber, boolean militarySpecCitizen, boolean onQuarantineStatus) {
        super(name,passportNumber, innCode, bornDate, electionDistrictAttachNumber, militarySpecCitizen, onQuarantineStatus);
        memberOfParty = "-";
    }

    @Override
    public String doChoice(Set<Party> partyList) {
        String result = "";
        try {
            if (partyList.size()==0) {
                throw new Exception("Error");                                       // Придумать Ексепшин
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        boolean willChoose = new Random().nextBoolean();
        if (!willChoose){
            return result;
        }
        int random = new Random().nextInt(partyList.size());
        int iter = 0;
        for (Party tempParty : partyList){
            if (iter==random){
                result = tempParty.getPartyName();
                break;
            }
            iter++;
        }
        return result;
    }

    @Override
    public String toString() {
        return "AdultCitizen{" +
                "name='" + name + '\'' +
                ", passportNumber=" + passportNumber +
                ", innCode=" + innCode +
                ", bornDate=" + bornDate +
                ", electionDistrictAttachNumber=" + electionDistrictAttachNumber +
                ", militarySpecCitizen=" + militarySpecCitizen +
                ", onQuarantineStatus=" + onQuarantineStatus +
                '}';
    }
}
