package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b) {
        Set<Integer> keys = human.keySet();
        List<Integer> listKeys = new ArrayList<Integer>(keys);
        for (int i = 0; i < listKeys.size(); i++)
            if (listKeys.get(i) > Integer.parseInt(comand.substring(19, comand.length())))
                human.remove(listKeys.get(i));
        return human;
    }
}
