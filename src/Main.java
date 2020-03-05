/**
 * ConsoleApp which allows to control the collection of Human Beings
 * Class Main
 *
 * @author Aleksandr Provotorov, P3110
 */

import Exceptions.ExistanceException;
import Exceptions.RightException;
import Command.CommandShell;
import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import Tools.ConvertToUTF;
import Tools.Parser;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static Tools.Converter.convertToList;
import static Tools.Converter.сonvertToLinkedHashMap;

public class Main {
    public static void main(String []args) throws IOException {
        CommandShell commandShell = new CommandShell();
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        Scanner scanner = new Scanner(System.in);
        //In this block first we parse xml to HumanList and then to LinkedHashMap
        boolean b = true;
        String path = "";

        try {
            //ConvertToUTF.Convert(args[0]);
            Parser Parser = new Parser();
            path = args[0];
            if (!(path == "")) Parser.parse(path);
            System.out.println("Файл был найден и конвертирован в коллекцию");
        } catch (ArrayIndexOutOfBoundsException e) {
            File file = new File("New_File");
            path = file.getName();
            System.out.println("Название файла было введено неправильно или такого файла не существует, файл \"" + path +"\" был создан");
        } catch (RightException e) {
            File file = new File("New_File");
            path = "New_File";
            System.out.println("Возникла ошибка с правами доступа к файлу, файл \"" + path + "\" был создан");
        } catch (JAXBException e) {
            System.out.println("XML файл был сгенерирован неверно");
        }
        LinkedHashMap<Integer, HumanBeing> human = new LinkedHashMap<Integer, HumanBeing>();

        try{
            human = сonvertToLinkedHashMap((Parser.getHumanList()));
        } catch (NullPointerException e){}
        HumanList humanList = new HumanList();
        humanList.creationDate_Now();
        humanList.setHumanBeings(convertToList(human));

        while (b || reader.ready()) {
            System.out.print("Введите команду: ");
            String command = bufferedReader.readLine(); //read command
            human = commandShell.Analyze(humanList, human, command, path, b);
            humanList.setHumanBeings(convertToList(human)); //LinkedHashMap -> HumanList
        }
    }
}