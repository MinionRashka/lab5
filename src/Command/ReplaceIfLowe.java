package Command;

import Bin.Commands;
import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import Tools.CompareHumanBeings;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;

public class ReplaceIfLowe extends Command {

    public ReplaceIfLowe(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        HumanBeing humanBeing = new HumanBeing();
        int key = Integer.parseInt(comand.substring(16, comand.length()));
        try {
            Commands.setHumanBeing(humanBeing, humanList.getHumanBeings().size() + 1);
        } catch (InputMismatchException e) {
            System.out.println("Попробуйте еще раз, введя правильные данные.");
        }
        if (CompareHumanBeings.compare(humanBeing, human.get(key))) {
                human.replace(key, human.get(key), humanBeing);
        }
        return human;
    }
}
