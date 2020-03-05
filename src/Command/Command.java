package Command;

import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import sun.awt.image.ImageWatched;

import java.util.LinkedHashMap;
import java.util.StringTokenizer;

public abstract class Command {
    LinkedHashMap<Integer, HumanBeing> human;
    String comand;
    HumanList humanList;
    StringTokenizer tokenizer;

    public Command(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        this.human = human;
        this.comand = comand;
        this.humanList = humanList;
        tokenizer = new StringTokenizer(comand);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        return human;
    }

    public LinkedHashMap<Integer, HumanBeing> getHuman() { return human; }
    public String getComand() { return comand; }
    public void setHuman(LinkedHashMap<Integer, HumanBeing> human) { this.human = human; }
    public void setComand(String comand) { this.comand = comand; }
    StringTokenizer getTokenizer() { return tokenizer; }

    @Override
    public String toString() {
        return "Comand{" +
                "human=" + human +
                ", comand='" + comand + '\'' +
                '}';
    }
}
