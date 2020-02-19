import MyOwnClasses.Comands;
import MyOwnClasses.HumanBeing;
import MyOwnClasses.HumanList;
import Tools.Parser;
import enums.Mood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.*;
import java.util.jar.JarFile;

import static Tools.Converter.convertToList;
import static Tools.Converter.сonvertToLinkedHashMap;

public class Main {
    public static void main(String []args) throws IOException {
        Instrumentation instrumentation = new Instrumentation() {
            @Override
            public void addTransformer(ClassFileTransformer transformer, boolean canRetransform) {

            }

            @Override
            public void addTransformer(ClassFileTransformer transformer) {

            }

            @Override
            public boolean removeTransformer(ClassFileTransformer transformer) {
                return false;
            }

            @Override
            public boolean isRetransformClassesSupported() {
                return false;
            }

            @Override
            public void retransformClasses(Class<?>... classes) throws UnmodifiableClassException {

            }

            @Override
            public boolean isRedefineClassesSupported() {
                return false;
            }

            @Override
            public void redefineClasses(ClassDefinition... definitions) throws ClassNotFoundException, UnmodifiableClassException {

            }

            @Override
            public boolean isModifiableClass(Class<?> theClass) {
                return false;
            }

            @Override
            public Class[] getAllLoadedClasses() {
                return new Class[0];
            }

            @Override
            public Class[] getInitiatedClasses(ClassLoader loader) {
                return new Class[0];
            }

            @Override
            public long getObjectSize(Object objectToSize) {
                return 0;
            }

            @Override
            public void appendToBootstrapClassLoaderSearch(JarFile jarfile) {

            }

            @Override
            public void appendToSystemClassLoaderSearch(JarFile jarfile) {

            }

            @Override
            public boolean isNativeMethodPrefixSupported() {
                return false;
            }

            @Override
            public void setNativeMethodPrefix(ClassFileTransformer transformer, String prefix) {

            }
        };
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        Parser Parser = new Parser();
        Parser.parse();
        LinkedHashMap<Integer, HumanBeing> human = сonvertToLinkedHashMap((Parser.getHumanList()));
        HumanList humanList = new HumanList();
        humanList.setHumanBeings(convertToList(human));
        boolean b = true;
        while (b || reader.ready()) {
            String comand = bufferedReader.readLine();
            try {

                if (comand.substring(0,4).equals("help")) Comands.help();

                if (comand.substring(0,4).equals("save")) {
                    humanList.setHumanBeings(convertToList(human));
                    Comands.save(humanList);
                }

                if (comand.substring(0, 4).equals("exit")) b = Comands.exit();

                if (comand.substring(0, 4).equals("show")) Comands.show(human);

                if (comand.substring(0, 5).equals("clean")) human = Comands.clean();

                if (comand.substring(0, 6).equals("update")) {
                    humanList.setHumanBeings(convertToList(human));
                    System.out.println("Ввод данных");
                    try{
                        Comands.update(humanList, Integer.parseInt(comand.substring(7,comand.length())));
                    }catch (InputMismatchException e){
                        System.out.println("Попробуйте еще раз, введя правильные данные.");
                    }
                }

                if (comand.substring(0, 6).equals("insert")){
                    HumanBeing humanBeing = new HumanBeing();
                    try{
                        Comands.setHumanBeing(humanBeing, humanList.getHumanBeings().size()+1);
                    }catch (InputMismatchException e){
                        System.out.println("Попробуйте еще раз, введя правильные данные.");
                    }
                    human.put(Integer.parseInt(comand.substring(7, comand.length())), humanBeing);
                }

                if (comand.substring(0, 10).equals("remove_key")){
                    human.remove(Integer.parseInt(comand.substring(11,comand.length())));
                    humanList.setHumanBeings(convertToList(human));
                }

                if (comand.substring(0, 14).equals("execute_script")) {
                    human = Comands.ReadScript(comand.substring(15,comand.length()), humanList, human);
                    humanList.setHumanBeings(convertToList(human));
                }

                if (comand.substring(0, 15).equals("replace_if_lowe")) {
                    HumanBeing humanBeing = new HumanBeing();
                    try{
                        Comands.setHumanBeing(humanBeing, humanList.getHumanBeings().size()+1);
                    }catch (InputMismatchException e){
                        System.out.println("Попробуйте еще раз, введя правильные данные.");
                    }
                    if (instrumentation.getObjectSize(humanBeing)>instrumentation.getObjectSize(human.get(Integer.parseInt(comand.substring(16,comand.length()))))){
                        int key = Integer.parseInt(comand.substring(16,comand.length()));
                        human.replace(key, human.get(key), humanBeing);
                        humanList.setHumanBeings(convertToList(human));
                    }
                }

                if (comand.substring(0, 16).equals("remove_lower_key")){
                    Set<Integer> keys = human.keySet();
                    List<Integer> listKeys = new ArrayList<Integer>( keys );
                    for (int i = 0; i < listKeys.size(); i++)   if (listKeys.get(i) < Integer.parseInt(comand.substring(17,comand.length()))) human.remove(listKeys.get(i));
                }

                if (comand.substring(0, 18).equals("remove_greater_key")){
                    Set<Integer> keys = human.keySet();
                    List<Integer> listKeys = new ArrayList<Integer>( keys );
                    for (int i = 0; i < listKeys.size(); i++)   if (listKeys.get(i) > Integer.parseInt(comand.substring(19,comand.length()))) human.remove(listKeys.get(i));
                }

                if (comand.substring(0, 19).equals("sum_of_impact_speed")){
                    int k = 0; //сумма всех скоростей удара
                    for (int i = 0; i < humanList.getHumanBeings().size(); i++) k += humanList.getHumanBeing(i).getImpactSpeed();
                    System.out.println(k);
                }

                if (comand.substring(0, 23).equals("average_of_impact_speed")){
                    int k = 0; //сумма всех скоростей удара
                    for (int i = 0; i < humanList.getHumanBeings().size(); i++) k += humanList.getHumanBeing(i).getImpactSpeed();
                    System.out.println(k/humanList.getHumanBeings().size());
                }

                if (comand.substring(0, 27).equals("print_field_descending_mood")){
                    Set<Mood> set = new TreeSet<>(Comparator.comparing(Mood::toString));
                    for (int i = 0; i < humanList.getHumanBeings().size(); i++) set.add(humanList.getHumanBeing(i).getMood());
                    System.out.println(set);
                }

            }catch(StringIndexOutOfBoundsException e){}
        }
    }
}