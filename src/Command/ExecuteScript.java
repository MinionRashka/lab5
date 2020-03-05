package Command;

import Exceptions.RepeatException;
import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import static Tools.Converter.convertToList;

public class ExecuteScript extends Command {
    public ExecuteScript(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        CommandShell commandShell = new CommandShell();
        try{
            StringTokenizer tokenizer = new StringTokenizer(command);
            String scriptPath;
            do {
                scriptPath = tokenizer.nextToken();
            } while (tokenizer.hasMoreTokens());

            InputStream inputStream = new FileInputStream(scriptPath);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready() || b) {
                String cmd;
                if (bufferedReader.ready()) cmd = bufferedReader.readLine();
                else break;

                StringTokenizer executeChecker = new StringTokenizer(cmd);
                if (executeChecker.nextToken().toLowerCase().equals("execute_script"))
                {
                    StringTokenizer values = new StringTokenizer(command);
                    String checker = executeChecker.nextToken();
                    while (values.hasMoreTokens())
                        if (values.nextToken().equals(checker))
                            throw new RepeatException("Script is recursively called");
                    String command_and_paths = "execute_script";
                    values = new StringTokenizer(command);
                    values.nextToken();
                    while (values.hasMoreTokens())
                        command_and_paths += " " + values.nextToken();
                    executeChecker = new StringTokenizer(cmd);
                    executeChecker.nextToken();
                    command_and_paths += " " + executeChecker.nextToken();
                    commandShell.Analyze(humanList, human, command_and_paths, path, b);
                }

                else {
                    human = commandShell.Analyze(humanList, human, cmd, path, b);
                    humanList.setHumanBeings(convertToList(human));
                }
            }
        } catch (FileNotFoundException e){ System.out.println("Данного файла не существует");
        } catch (IOException e) { System.out.println("Не удалось считать из файла");
        } catch (RepeatException e) { System.out.println("Произошел рекурсивный вызов скрипта"); }
        return human;
    }
}
