package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.util.LinkedHashMap;

public class Exit extends Command {
    public Exit(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        System.out.println("Конец программы");
        System.exit(0);
        return human;
    }
}
