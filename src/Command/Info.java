package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class Info extends Command {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Info(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b) {
        System.out.println("Type: LinkedHashMap\n" + "Initialization date: " + humanList.getCreationDate().format(formatter) + "\n"
                + "Number of elements: " + humanList.getHumanBeings().size());
        return human;
    }
}
