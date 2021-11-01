package election;

import java.util.Set;

public class YoungCitizen extends Human implements Comparable<Human>{
    public YoungCitizen(String name, String bornDate) {
        super(name, bornDate);

    }

    @Override
    public String doChoice(Set<Party> partyList) {
        String result = "";
        return result;
    }

    @Override
    public String toString() {
        return "YoungCitizen{" +
                "name='" + name + '\'' +
                ", bornDate=" + bornDate +
                '}';
    }

}
