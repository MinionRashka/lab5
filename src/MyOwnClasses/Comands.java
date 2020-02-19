package MyOwnClasses;

import Exceptions.HumanValueException;
import Tools.Checker;
import Tools.FWriter;
import enums.Mood;
import enums.WeaponType;

import java.io.*;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.*;
import java.util.jar.JarFile;

import static Tools.Converter.convertToList;

public class Comands {
    private static Instrumentation instrumentation = new Instrumentation() {
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

    public static void help(){
        System.out.println(
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert key {element} : добавить новый элемент с заданным ключом\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key key : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "replace_if_lowe key {element} : заменить значение по ключу, если новое значение меньше старого\n" +
                "remove_greater_key key : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                "remove_lower_key key : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n" +
                "sum_of_impact_speed : вывести сумму значений поля impactSpeed для всех элементов коллекции\n" +
                "average_of_impact_speed : вывести среднее значение поля impactSpeed для всех элементов коллекции\n" +
                "print_field_descending_mood mood : вывести значения поля mood в порядке убывания"
        );
    }

    public static boolean exit(){
        return false;
    }

    public static LinkedHashMap<Integer, HumanBeing> clean () {
        LinkedHashMap<Integer, HumanBeing> linkedHashMap = new LinkedHashMap<Integer, HumanBeing>();
        System.out.println("Очистка произведена");
        return linkedHashMap;
    }

    public static void save (HumanList humanList){
        FWriter.unParse(humanList);
        System.out.println("Коллекция сохранена");
    }

    public static void show (LinkedHashMap<Integer, HumanBeing> humans) {
        System.out.print("Коллекция: ");
        System.out.println(humans);
    }

    public static void update(HumanList humanList, long id){
        for (int i = 0;i < humanList.getHumanBeings().size(); i++){
            if (humanList.getHumanBeings().get(i).getId() == id) {
                setHumanBeing(humanList.getHumanBeing(i),humanList.getHumanBeings().size()+1);
                break;
            }
        }
    }

    public static LinkedHashMap<Integer, HumanBeing> ReadScript(String str, HumanList humanList, LinkedHashMap<Integer, HumanBeing> human){
        try{
            InputStream inputStream = new FileInputStream(str);
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            boolean b = true;
            while (b || bufferedReader.ready()) {
                String comand;
                if (bufferedReader.ready()) comand = bufferedReader.readLine();
                else break;
                try {
                    if (comand.substring(0,4).equals("help")) Comands.help();

                    if (comand.substring(0,4).equals("save")) {
                        humanList.setHumanBeings(convertToList(human));
                        Comands.save(humanList);
                    }

                    if (comand.substring(0,4).equals("exit")) System.exit(1);

                    if (comand.substring(0,4).equals("show")) Comands.show(human);

                    if (comand.substring(0,5).equals("clean")) human = Comands.clean();

                    if (comand.substring(0,6).equals("update")) {
                        humanList.setHumanBeings(convertToList(human));
                        try{
                            Comands.update(humanList, Integer.parseInt(comand.substring(7,comand.length())));
                        }catch (InputMismatchException e){
                            System.out.println("Попробуйте еще раз, введя правильные данные.");
                        }
                    }

                    if (comand.substring(0,6).equals("insert")){
                        HumanBeing humanBeing = new HumanBeing();
                        Comands.setHumanBeing(humanBeing, humanList.getHumanBeings().size()+1);
                        human.put(Integer.parseInt(comand.substring(7, comand.length())), humanBeing);
                        humanList.setHumanBeings(convertToList(human));
                    }

                    if (comand.substring(0,10).equals("remove_key")){
                        human.remove(Integer.parseInt(comand.substring(11,comand.length())));
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
                System.out.println();
            }
        } catch (FileNotFoundException e){
            System.out.println("Данного файла не существует");
        } catch (IOException e) {
            System.out.println("Не удалось считать из файла");
        }
        return human;
    }

    public static void setHumanBeing(HumanBeing humanBeing, int id) {
        Scanner scanner = new Scanner(System.in);

        humanBeing.setId(id);

        System.out.print("Введите имя: ");
        humanBeing.setName(scanner.nextLine());

        System.out.print("Введите координату x: ");
        humanBeing.setCoordinatesX(scanner.nextFloat());

        System.out.print("Введите координату y: ");
        humanBeing.setCoordinatesY(scanner.nextInt());

        System.out.println("Является ли персонаж настоящим героем? (true/false)");
        humanBeing.setRealHero(scanner.nextBoolean());

        System.out.println("Есть ли у персонажа зубочистка? (true/false)");
        humanBeing.setHasToothpick(scanner.nextBoolean());

        System.out.println("Введите скорость удара: ");
        humanBeing.setImpactSpeed(scanner.nextInt());

        System.out.print("Введите вид оружия: ");
        scanner.nextLine();
        String c = scanner.nextLine().toUpperCase();
        if (c.equals("HAMMER")) humanBeing.setWeaponType(WeaponType.HAMMER);
        if (c.equals("PISTOL")) humanBeing.setWeaponType(WeaponType.PISTOL);
        if (c.equals("SHOTGUN")) humanBeing.setWeaponType(WeaponType.SHOTGUN);
        if (c.equals("RIFLE")) humanBeing.setWeaponType(WeaponType.RIFLE);
        if (c.equals("KNIFE")) humanBeing.setWeaponType(WeaponType.KNIFE);

        System.out.print("Введите настроение персонажа: ");
        c = scanner.nextLine().toUpperCase();
        if (c.equals("SADNESS")) humanBeing.setMood(Mood.SADNESS);
        if (c.equals("SORROW")) humanBeing.setMood(Mood.SORROW);
        if (c.equals("GLOOM")) humanBeing.setMood(Mood.GLOOM);
        if (c.equals("RAGE")) humanBeing.setMood(Mood.RAGE);
        if (c.equals("FRENZY")) humanBeing.setMood(Mood.FRENZY);

        System.out.println("Является ли машина персонажа крутой? (true/false)");
        humanBeing.setCarCool(scanner.nextBoolean());

        try{
            Checker.checkHuman(humanBeing);
        }catch (HumanValueException e){
            e.printStackTrace();
        }
    }
}
