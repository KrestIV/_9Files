package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class TestsZipTest {
    private ClassLoader classLoader = TestsZipTest.class.getClassLoader();

    @Test
    public void pdfInZipFileShouldContainDataTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                classLoader.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text.length()).isGreaterThan(0);
                    assertThat(pdf.numberOfPages).isEqualTo(1);
                }
            }
        }


    }

    @Test
    public void xlsxInZipFileShouldContainDataTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                classLoader.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    String actualValueSpendName = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    assertThat(actualValueSpendName).isEqualTo("Статья расходов");
                    assertThat(xls.excel.getNumberOfSheets()).isGreaterThan(0);
                }
            }
        }


    }

    @Test
    public void csvInZipFileShouldContainDataTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                classLoader.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    assertThat(Integer.valueOf(data.get(1)[1])).isGreaterThan(0);
                }
            }
        }


    }
}
