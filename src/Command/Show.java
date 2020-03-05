package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.util.LinkedHashMap;

public class Show extends Command {
    public Show(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        System.out.print("Коллекция: ");
        System.out.println(humanList.getHumanBeings().toString());
        return human;
    }
}
