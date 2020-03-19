package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.util.LinkedHashMap;

public class SumOfImpactSpeed extends Command {
    public SumOfImpactSpeed(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        int k = 0;
        for (int i = 0; i < humanList.getHumanBeings().size(); i++)
            k += humanList.getHumanBeing(i).getImpactSpeed();
        System.out.println(k);
        return human;
    }
}
