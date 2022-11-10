import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CompareXml {


    public static void main(String[] args) {

        File sourcesFolder = new File("src/main/resources/SourceXml");
        File[] listOfSourceFiles = sourcesFolder.listFiles();
        Arrays.sort(listOfSourceFiles);
        File targetFolder = new File("src/main/resources/TargetXml");
        File[] listOfTargetFiles = targetFolder.listFiles();
        Arrays.sort(listOfTargetFiles);
        for (int i = 0; i < listOfSourceFiles.length; i++) {
            if (listOfSourceFiles[i].isFile() & listOfTargetFiles[i].isFile()) {
                System.out.println(listOfSourceFiles[i].getPath());
                System.out.println(listOfTargetFiles[i].getPath());
                XMLComparer(listOfSourceFiles[i].getPath(),listOfTargetFiles[i].getPath());

            } else if (listOfSourceFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfSourceFiles[i].getName());
            }
        }



    }

    public static void XMLComparer(String source, String target) {
        XMLUnit.setIgnoreWhitespace(false); // ignore whitespace differences
        XMLUnit.setIgnoreAttributeOrder(true);
        XMLUnit.setIgnoreComments(true);
        try {
            Diff xmlDiff = new Diff(source, target);
            DetailedDiff detailXmlDiff = new DetailedDiff(xmlDiff);
            List diff = detailXmlDiff.getAllDifferences();
            diff.forEach(System.out::println);
            if (diff.size() > 0)
                System.out.printf("Total differences is \"%d\"%n", diff.size());
            else
                System.out.println("Completely matched");
        } catch (SAXException | IOException e) {
        }
    }
}
