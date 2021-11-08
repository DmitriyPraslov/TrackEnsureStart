package election.citizen;

import election.Election;
import election.Party;

import java.time.LocalDate;
import java.util.Set;

public abstract class Human implements Comparable<Human>{
    protected String name;
    protected int passportNumber;
    protected long innCode;
    protected LocalDate bornDate;
    protected int electionDistrictAttachNumber;
    protected boolean militarySpecCitizen;
    protected boolean onQuarantineStatus;
    protected String memberOfParty;

    public Human(String name, String bornDate){
        this.name = name;
        this.bornDate = LocalDate.parse(bornDate, Election.DATE_TIME_FORMATTER);
        this.passportNumber = 0;
        this.innCode = 0;
        this.electionDistrictAttachNumber = 0;
        this.militarySpecCitizen = false;
        this.onQuarantineStatus = false;
        this.memberOfParty = "-";
    }

    public Human(String name, int passportNumber, long innCode, String bornDate, int electionDistrictAttachNumber, boolean militarySpecCitizen, boolean onQuarantineStatus) {
        checkINNCode(innCode);
        checkPassportNumber(passportNumber);
        this.name = name;
        this.passportNumber = passportNumber;
        this.innCode = innCode;
        this.bornDate = LocalDate.parse(bornDate,Election.DATE_TIME_FORMATTER);
        this.electionDistrictAttachNumber = electionDistrictAttachNumber;
        this.militarySpecCitizen = militarySpecCitizen;
        this.onQuarantineStatus = onQuarantineStatus;
    }


    public abstract String doChoice(Set<Party> partyList);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        checkPassportNumber(passportNumber);
        this.passportNumber = passportNumber;
    }

    public long getInnCode() {
        return innCode;
    }

    public void setInnCode(long innCode) {
        checkINNCode(innCode);
        this.innCode = innCode;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public int getElectionDistrictAttachNumber() {
        return electionDistrictAttachNumber;
    }

    public void setElectionDistrictAttachNumber(int electionDistrictAttachNumber) {
        this.electionDistrictAttachNumber = electionDistrictAttachNumber;
    }

    public boolean isMilitarySpecCitizen() {
        return militarySpecCitizen;
    }

    public boolean isOnQuarantineStatus() {
        return onQuarantineStatus;
    }

    public int checkPassportNumber(int number){
        try{
            if (Integer.toString(number).length()<6){
                throw new Exception();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return number;
    }

    public long checkINNCode(long innCode){
        try{
            if (Long.toString(innCode).length()<10){
                throw new Exception();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return innCode;
    }

    public String getCitizenInfo(){
        char delimiter = ';';
        StringBuilder build = new StringBuilder("");
        build.append(this.name+delimiter+this.passportNumber+delimiter+innCode+delimiter+this.bornDate.format(Election.DATE_TIME_FORMATTER)+delimiter+electionDistrictAttachNumber+delimiter
                +this.militarySpecCitizen+delimiter+this.onQuarantineStatus+delimiter+memberOfParty);
        return build.toString();
    }

    @Override
    public int compareTo(Human human){
        return this.name.compareTo(human.name)+(this.bornDate.compareTo(human.bornDate)*100);
    }
}
