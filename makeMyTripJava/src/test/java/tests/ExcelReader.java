package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static void main(String[] args) throws IOException {
		// Open the Excel file
		FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir") + "/EmployeeData.xlsx"));

		// Create a Workbook instance
		Workbook workbook = new XSSFWorkbook(file);

		// Get the sheet (assuming the first sheet)
		Sheet sheet = workbook.getSheetAt(0);

		// Iterate through all the rows
		for (Row row : sheet) {
			// For each row, iterate through all the cells
			for (Cell cell : row) {
				// Print the value of the cell
				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue() + "\t");
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t");
					break;
				default:
					System.out.print("Unknown" + "\t");
				}
			}
			System.out.println(); // New line after each row
		}

		// Close the file and workbook
		workbook.close();
		file.close();
	}
}
