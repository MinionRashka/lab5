package MyOwnClasses;

import javax.xml.bind.annotation.XmlElement;

public class Car {
    private boolean cool = true;

    public Car () {}

    @XmlElement
    public boolean isCool() { return cool; }
    public void setCool(boolean cool) { this.cool = cool; }
}
