package Command;

import Exceptions.HumanValueException;
import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import Tools.Checker;
import Tools.Converter;
import enums.Mood;
import enums.WeaponType;

import java.util.*;

public class Update extends Command {
    public Update(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }
    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        StringTokenizer tokenizer = new StringTokenizer(command);
        tokenizer.nextToken();
        int id = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < humanList.getHumanBeings().size(); i++) {
            if (humanList.getHumanBeings().get(i).getId() == id) {
                try{
                    humanList.getHumanBeing(i).setId(id);
                    humanList.getHumanBeing(i).setCreationDate();
                    Scanner scanner = new Scanner (System.in);

                    System.out.print("Введите имя: ");
                    humanList.getHumanBeing(i).setName(scanner.nextLine());

                    System.out.print("Введите координату X: ");
                    humanList.getHumanBeing(i).setCoordinatesX(scanner.nextFloat());

                    System.out.print("Введите координату Y: ");
                    humanList.getHumanBeing(i).setCoordinatesY(scanner.nextInt());

                    System.out.println("Является ли персонаж настоящим героем? (true/false)");
                    humanList.getHumanBeing(i).setRealHero(scanner.nextBoolean());

                    System.out.println("Обладает ли персонаж зубной щеткой? (true/false)");
                    humanList.getHumanBeing(i).setHasToothpick(scanner.nextBoolean());

                    System.out.println("Введите скорость удара: ");
                    humanList.getHumanBeing(i).setImpactSpeed(scanner.nextInt());

                    System.out.println("Введите оружие персонажа из предложенных (Hammer  Pistol  Shotgun  Rifle  Knife): ");
                    scanner.nextLine();
                    String str = scanner.nextLine();
                    if (str.toUpperCase().equals("HAMMER")) humanList.getHumanBeing(i).setWeaponType(WeaponType.HAMMER);
                    else if (str.toUpperCase().equals("PISTOL")) humanList.getHumanBeing(i).setWeaponType(WeaponType.PISTOL);
                    else if (str.toUpperCase().equals("SHOTGUN")) humanList.getHumanBeing(i).setWeaponType(WeaponType.SHOTGUN);
                    else if (str.toUpperCase().equals("RIFLE")) humanList.getHumanBeing(i).setWeaponType(WeaponType.RIFLE);
                    else if (str.equals("KNIFE")) humanList.getHumanBeing(i).setWeaponType(WeaponType.KNIFE);
                    else throw new HumanValueException("Enter correct data type");

                    System.out.println("Введите настроение персонажа из предложенных (Sadness  Sorrow  Gloom  Rage  Frenzy): ");
                    str = scanner.nextLine();
                    if (str.toUpperCase().equals("SADNESS")) humanList.getHumanBeing(i).setMood(Mood.SADNESS);
                    else if (str.toUpperCase().equals("SORROW")) humanList.getHumanBeing(i).setMood(Mood.SORROW);
                    else if (str.toUpperCase().equals("GLOOM")) humanList.getHumanBeing(i).setMood(Mood.GLOOM);
                    else if (str.toUpperCase().equals("RAGE")) humanList.getHumanBeing(i).setMood(Mood.RAGE);
                    else if (str.toUpperCase().equals("FRENZY")) humanList.getHumanBeing(i).setMood(Mood.FRENZY);
                    else throw new HumanValueException("Enter correct data type");

                    System.out.println("По настоящему ли крута машина персонажа (true/false): ");
                    humanList.getHumanBeing(i).setCarCool(scanner.nextBoolean());

                    try{
                        Checker.checkHuman(humanList.getHumanBeing(i));
                    }catch (HumanValueException e){
                        e.printStackTrace();
                    }
                } catch(NoSuchElementException e) { System.out.println("Вы не ввели id персонажа. Попытайтесь снова, введя команду заново"); }
                break;
            }
        }
        return Converter.сonvertToLinkedHashMap(humanList);
    }
}
