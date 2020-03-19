package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import Tools.FWriter;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Save extends Command {
    public Save(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        try{
            FWriter.unParse(humanList, path);
        } catch(NoSuchElementException e){
            String newPath = "New_File";
            FWriter.unParse(humanList, newPath);
        }

        return human;
    }
}
