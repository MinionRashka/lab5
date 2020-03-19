package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.util.*;

public class RemoveLowerKey extends Command {
    public RemoveLowerKey(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        Set<Integer> keys = human.keySet();
        List<Integer> listKeys = new ArrayList<Integer>(keys);
        for (int i = 0; i < listKeys.size(); i++)
            if (listKeys.get(i) < Integer.parseInt(comand.substring(17, comand.length())))
                human.remove(listKeys.get(i));
        return human;
    }
}
