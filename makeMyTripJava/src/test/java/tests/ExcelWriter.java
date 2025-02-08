package tests;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	public static void main(String[] args) throws IOException {
		// Create a Workbook
		Workbook workbook = new XSSFWorkbook();

		// Create a sheet
		Sheet sheet = workbook.createSheet("Employee Data");

		// Create the header row
		Row headerRow = sheet.createRow(0);
		String[] columns = { "EMP No", "EMP Name", "EMP Designation", "EMP Salary", "EMP Department" };
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		// Create some sample data rows
		Object[][] employeeData = { { 101, "John Doe", "Manager", 75000, "HR" },
				{ 102, "Jane Smith", "Developer", 65000, "IT" },
				{ 103, "Michael Johnson", "Designer", 55000, "Marketing" },
				{ 104, "Emily White", "Tester", 45000, "QA" } };

		// Fill the sheet with employee data
		int rowNum = 1;
		for (Object[] employee : employeeData) {
			Row row = sheet.createRow(rowNum++);
			for (int colNum = 0; colNum < employee.length; colNum++) {
				Cell cell = row.createCell(colNum);
				cell.setCellValue(employee[colNum].toString());
			}
		}

		// Write the Excel file to the disk
		FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + "/EmployeeData.xlsx");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();

		System.out.println("Excel file created successfully.");
	}

}
