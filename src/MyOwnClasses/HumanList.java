package MyOwnClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="humanList")
public class HumanList {
    private List<HumanBeing> humanBeings = new ArrayList<HumanBeing>(); //список людей (HumanBeing)

    @XmlElement(name="humanBeing")
    public List<HumanBeing> getHumanBeings() {  //getter для списка людей
        return humanBeings;
    }

    public HumanBeing getHumanBeing(int i){ //getter для конкретного человека
        return humanBeings.get(i);
    }

    public void setHumanBeings(List<HumanBeing> humanBeings) {
        this.humanBeings = humanBeings;
    }
}
