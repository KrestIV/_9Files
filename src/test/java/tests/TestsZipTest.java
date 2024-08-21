package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TestsZipTest {
    private ClassLoader classLoader = TestsZipTest.class.getClassLoader();

    @Test
    public void zipFileShouldContainDataTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                classLoader.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) checkPDF(zis);
                else if (entry.getName().endsWith(".xlsx")) checkXLSX(zis);
                else if (entry.getName().endsWith(".csv")) checkCSV(zis);
                System.out.println(entry.getName());
            }
        }


    }
    private void checkPDF(ZipInputStream stream) throws Exception{
        PDF pdf = new PDF(stream);
        Assertions.assertTrue(pdf.text.length() > 0);
        Assertions.assertEquals(1,pdf.numberOfPages);
    }

    private void checkXLSX(ZipInputStream stream) throws Exception{
        XLS xls = new XLS(stream);
        Assertions.assertTrue(xls.excel.getNumberOfSheets()>0);
        String actualValueSpendName = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
        Assertions.assertEquals("Статья расходов",actualValueSpendName);
    }

    private void checkCSV(ZipInputStream stream) throws Exception{
        CSVReader csvReader = new CSVReader(new InputStreamReader(stream));

        List<String[]> data = csvReader.readAll();
        Assertions.assertTrue(Integer.valueOf(data.get(1)[1]) > 0);
    }
}
