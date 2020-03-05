package Command;

import Exceptions.HumanValueException;
import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import Tools.Checker;
import enums.Mood;
import enums.WeaponType;

import java.util.*;

import static Tools.Converter.convertToList;

public class Insert extends Command {
    public Insert(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        try {
            StringTokenizer tokenizer = new StringTokenizer(command);
            tokenizer.nextToken();
            String humanID = tokenizer.nextToken();
            HumanBeing humanBeing = new HumanBeing();
            humanBeing.setId(human.size() + 1);
            humanBeing.setCreationDate();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите имя: ");
            humanBeing.setName(scanner.nextLine());

            System.out.print("Введите координату X: ");
            humanBeing.setCoordinatesX(scanner.nextFloat());

            System.out.print("Введите координату Y: ");
            humanBeing.setCoordinatesY(scanner.nextInt());

            System.out.println("Является ли персонаж настоящим героем? (true/false)");
            humanBeing.setRealHero(scanner.nextBoolean());

            System.out.println("Обладает ли персонаж зубной щеткой? (true/false)");
            humanBeing.setHasToothpick(scanner.nextBoolean());

            System.out.println("Введите скорость удара: ");
            humanBeing.setImpactSpeed(scanner.nextInt());

            System.out.println("Введите оружие персонажа из предложенных (Hammer  Pistol  Shotgun  Rifle  Knife): ");
            scanner.nextLine();
            String str = scanner.nextLine();
            if (str.toUpperCase().equals("HAMMER")) humanBeing.setWeaponType(WeaponType.HAMMER);
            else if (str.toUpperCase().equals("PISTOL")) humanBeing.setWeaponType(WeaponType.PISTOL);
            else if (str.toUpperCase().equals("SHOTGUN")) humanBeing.setWeaponType(WeaponType.SHOTGUN);
            else if (str.toUpperCase().equals("RIFLE")) humanBeing.setWeaponType(WeaponType.RIFLE);
            else if (str.equals("KNIFE")) humanBeing.setWeaponType(WeaponType.KNIFE);
            else throw new HumanValueException("Enter correct data type");

            System.out.println("Введите настроение персонажа из предложенных (Sadness  Sorrow  Gloom  Rage  Frenzy): ");
            str = scanner.nextLine();
            if (str.toUpperCase().equals("SADNESS")) humanBeing.setMood(Mood.SADNESS);
            else if (str.toUpperCase().equals("SORROW")) humanBeing.setMood(Mood.SORROW);
            else if (str.toUpperCase().equals("GLOOM")) humanBeing.setMood(Mood.GLOOM);
            else if (str.toUpperCase().equals("RAGE")) humanBeing.setMood(Mood.RAGE);
            else if (str.toUpperCase().equals("FRENZY")) humanBeing.setMood(Mood.FRENZY);
            else throw new HumanValueException("Enter correct data type");

            System.out.println("По настоящему ли крута машина персонажа (true/false): ");
            humanBeing.setCarCool(scanner.nextBoolean());

            try {
                Checker.checkHuman(humanBeing);
            } catch (HumanValueException e) {
                e.printStackTrace();
            }

            human.put(Integer.parseInt(humanID), humanBeing);
            humanList.setHumanBeings(convertToList(human));
        } catch (InputMismatchException e) { System.out.println("Введите правильный тип данных. Попытайтесь снова, введя команду заново.");
        } catch(NoSuchElementException e) { System.out.println("Вы не ввели id персонажа. Попытайтесь снова, введя команду заново"); }
        return human;
    }
}
