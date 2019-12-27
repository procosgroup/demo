package com.procosgroup.demo.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter @Setter
public class ImportExcelService {	 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private int batchSize;
	
	public void getBuildings() {
		jdbcTemplate.queryForList("select * from building");
	}
	

	public void importFromExcel(FileInputStream file) { 
		String tableName = null;
		List<Map<String, Object>> data = readDataFromExcel(tableName, file);
		
		saveData(tableName, data); 
	}


	private void saveData(String tableName, List<Map<String, Object>> data) { 
		for (Map<String, Object> record : data) {
			 
		}
		
		// jdbcTemplate.batchUpdate(sql, batchArgs);		
	}


	private List<Map<String, Object>> readDataFromExcel(String tableName, FileInputStream file) {
		try {
			Workbook workbook = new XSSFWorkbook(file);			
			Sheet sheet = workbook.getSheetAt(0);
 
			// read the header
			Iterator<Row> rowIterator = sheet.rowIterator(); 
			
			List<String> fieldNames = new ArrayList<>();
			
			List<Map<String, Object>> data = new ArrayList<>(); 
			
			if (rowIterator.hasNext()) {
				for (Cell cell : rowIterator.next()) { 
					if (tableName == null) {
						tableName = getTableName(cell.getStringCellValue());
					}
					fieldNames.add(getFieldName(cell.getStringCellValue()));
				} 

				while (rowIterator.hasNext()) {
					Map<String, Object> record = new HashMap<>();
					int i=0;
					for (Cell cell : rowIterator.next()) { 
						record.put(fieldNames.get(i), getCellValue(cell)); 
						i++;
					}
					data.add(record);
				}
			}  

			workbook.close();
			
			return data;

		} catch (IOException e) {
			throw new RuntimeException("Error");
		}
	}
	
	
	private String getTableName(String name) {
		if (name.startsWith("#") && name.contains(".")) {
			return name.substring(1, name.indexOf('.') - 1);
		}
		return null;
	}
	
	private String getFieldName(String name) {
		if (name.startsWith("#") && name.contains(".")) {
			return name.substring(name.indexOf('.') + 1, name.length());
		} else {
			return name;
		} 
	}

	private Object getCellValue(Cell cell) { 
		if (DateUtil.isCellDateFormatted(cell)) {
			return cell.getDateCellValue();
		} else if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if  (cell.getCellType() == CellType.NUMERIC) {
			return cell.getNumericCellValue();
		} 

		return null;
	}

}
