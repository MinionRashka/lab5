package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.util.LinkedHashMap;

public class Clean extends Command {
    public Clean(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        LinkedHashMap<Integer, HumanBeing> linkedHashMap = new LinkedHashMap<Integer, HumanBeing>();
        System.out.println("Очистка произведена");
        return linkedHashMap;
    }
}
