package Tools;

import MyOwnClasses.HumanList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class FWriter {
    public FWriter(){}
    public static void write(String str){
        try{
            OutputStreamWriter filewriter = new OutputStreamWriter(new FileOutputStream("Answer.xml"));
            filewriter.write(str);
            filewriter.close();
        } catch (IOException e){
            System.out.println("File write failed: " + e.toString());
        }
    }
    public static void unParse(HumanList humanList){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(HumanList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(humanList, sw);
            String xmlContent = sw.toString();
            write( xmlContent );
        } catch(JAXBException e){
            e.printStackTrace();
        }

    }
}
