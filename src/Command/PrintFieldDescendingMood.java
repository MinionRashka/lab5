package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import enums.Mood;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

public class PrintFieldDescendingMood extends Command {
    public PrintFieldDescendingMood(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        Set<Mood> set = new TreeSet<>(Comparator.comparing(Mood::toString));
        for (int i = 0; i < humanList.getHumanBeings().size(); i++)
            set.add(humanList.getHumanBeing(i).getMood());
        System.out.println(set);
        return human;
    }
}
