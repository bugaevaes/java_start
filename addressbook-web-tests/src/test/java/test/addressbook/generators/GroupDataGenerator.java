package test.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.thoughtworks.xstream.XStream;
import test.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        new JCommander(generator, args);
        generator.run();
        //int count = Integer.parseInt(args[0]);
        //File file = new File(args[1]);
    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveAsCsv(groups, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(groups, new File(file));
        } else {
            System.out.println("Неопознанный формат " + format);
        }
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i)).withFooter(String.format("footer %s", i)));
        }
        return groups;
    }

    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        //System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file);) {
            for (GroupData group : groups) {
                writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
            }
        }
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        try (Writer writer = new FileWriter(file);) {
            writer.write(xml);
        }
    }

}
