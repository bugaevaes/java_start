package test.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import test.addressbook.model.ContactData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        new JCommander(generator, args);
        generator.run();
        //int count = Integer.parseInt(args[0]);
        //File file = new File(args[1]);
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Неопознанный формат " + format);
        }
    }

    private List<ContactData> generateContacts (int count){
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withName(String.format("Владимир%s", i))
                    .withLastname(String.format("Тестовый%s", i)).withAddress(String.format("Тестовая улица д.%s", i))
            .withEmail(String.format("vladimir%s@ya.ru", i)).withHomePhone(String.format("123000%s", i)));
        }
        return contacts;
    }

    private void saveAsCsv (List<ContactData> contacts, File file) throws IOException {
        //System.out.println(new File(".").getAbsolutePath());
        try(Writer writer = new FileWriter(file);){
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getLastname(), contact.getAddress(),
             contact.getEmail(), contact.getHomePhone()));}
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file);) {
            writer.write(xml);
        }
    }

}
