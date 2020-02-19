package Lamp;

import java.util.Objects;

public class Lamp implements Tumbler {
    String name;
    Сondition c;

    public Lamp(String name){
        this.name = name;
        this.c = Сondition.OFF;
    }

    @Override
    public void turnOn() {
        if (c== Сondition.OFF) {
            System.out.println("Лампа включена");
            c = Сondition.ON;
        }
        else System.out.println("Лампа и так включена");
    }

    @Override
    public void turnOff() {
        if (c== Сondition.ON) {
            System.out.println("Лампа выключена");
            c = Сondition.OFF;
        }
        else System.out.println("Лампа и так выключена");
    }

    public String getName() {
        return name;
    }

    public Сondition getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lamp lamp = (Lamp) o;
        return Objects.equals(name, lamp.name) &&
                c == lamp.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, c);
    }

    @Override
    public String toString() {
        return "Lamp{" +
                "name='" + name + '\'' +
                ", c=" + c +
                '}';
    }
}
