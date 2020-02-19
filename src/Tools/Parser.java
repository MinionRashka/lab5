package Tools;

import Exceptions.HumanValueException;
import MyOwnClasses.HumanList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static Tools.Checker.checkHumanList;


public class Parser{

    private HumanList humanList;
    public void parse() {
        try {
            File file = new File("Exercise.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(HumanList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            HumanList humanList = (HumanList) jaxbUnmarshaller.unmarshal(file);

            this.humanList = humanList;

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < humanList.getHumanBeings().size(); i++) humanList.getHumanBeing(i).setId(i+1);

        try {
            checkHumanList(humanList);
        } catch (HumanValueException e){
            e.printStackTrace();
        }
    }
    public HumanList getHumanList(){
        return humanList;
    }
}
