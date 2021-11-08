package election.citizen;

import election.Election;
import election.Party;

import java.util.Random;
import java.util.Set;

public class Candidate extends Human implements Comparable<Human>{
    private int primaryPartyID;

    public Candidate(String name, int passportNumber, long innCode, String bornDate, int electionDistrictAttachNumber, boolean militarySpecCitizen, boolean onQuarantineStatus,String memberOfParty) {
        super(name, passportNumber, innCode, bornDate, electionDistrictAttachNumber, militarySpecCitizen, onQuarantineStatus);
        this.memberOfParty = memberOfParty;
        this.primaryPartyID = new Random().nextInt(1000);
    }

    public Candidate(Human citizen, String memberOfParty) {
        super(citizen.name, citizen.passportNumber, citizen.innCode, citizen.bornDate.format(Election.DATE_TIME_FORMATTER), citizen.electionDistrictAttachNumber, citizen.militarySpecCitizen, citizen.onQuarantineStatus);
        this.memberOfParty = memberOfParty;
        this.primaryPartyID = new Random().nextInt(1000);
    }

    @Override
    public String doChoice(Set<Party> partyList) {
        return memberOfParty;
    }

    public int getPrimaryPartyID() {
        return primaryPartyID;
    }

    public void setPrimaryPartyID(int primaryPartyID) {
        this.primaryPartyID = primaryPartyID;
    }

    public String getMemberOfParty() {
        return memberOfParty;
    }

    public void setMemberOfParty(String memberOfParty) {
        this.memberOfParty = memberOfParty;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "primaryPartyID=" + primaryPartyID +
                ", memberOfParty='" + memberOfParty + '\'' +
                ", name='" + name + '\'' +
                ", passportNumber=" + passportNumber +
                ", innCode=" + innCode +
                ", bornDate=" + bornDate +
                ", electionDistrictAttachNumber=" + electionDistrictAttachNumber +
                ", militarySpecCitizen=" + militarySpecCitizen +
                ", onQuarantineStatus=" + onQuarantineStatus +
                '}';
    }
}
