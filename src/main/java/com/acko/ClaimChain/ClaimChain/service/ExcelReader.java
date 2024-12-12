package com.acko.ClaimChain.ClaimChain.service;
import com.acko.ClaimChain.ClaimChain.constants.HeaderMapping;
import com.acko.ClaimChain.ClaimChain.dto.AutoClaimsDto;
import com.acko.ClaimChain.ClaimChain.dto.ClaimsSuperDto;
import com.acko.ClaimChain.ClaimChain.dto.HealthClaimsDto;
import com.acko.ClaimChain.ClaimChain.dto.LifeClaimsDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class ExcelReader {

    public List<ClaimsSuperDto> readExcel(MultipartFile file) throws IOException, ReflectiveOperationException {
        List<ClaimsSuperDto> claims = new ArrayList<>();

        try {
             Workbook workbook = new XSSFWorkbook(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0); // First sheet
            Iterator<Row> rowIterator = sheet.iterator();

            // Get the header row and create header-index map
            Row headerRow = rowIterator.next();
            Map<String, Integer> headerIndexMap = createHeaderIndexMap(headerRow);

            // Iterate over the rows and populate DTOs
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Determine the claim type from the 'Policy Type' field (for example)
                String policyType = getCellValue(row.getCell(headerIndexMap.get("Policy Type")));
                ClaimsSuperDto claimDto = createClaimDto(policyType);

                populateClaimDTOFromRow(claimDto, row, headerIndexMap);
                claims.add(claimDto);
            }
        }catch (Exception ex){

        }

        return claims;
    }

    // Determine which type of DTO to create based on policy type
    private ClaimsSuperDto createClaimDto(String policyType) {
        switch (policyType) {
            case "Car":
            case "Bike":
                return new AutoClaimsDto();
            case "Health":
                return new HealthClaimsDto();
            case "Life":
                return new LifeClaimsDto();
            default:
                throw  new RuntimeException("no Class found"); // Default or error handling
        }
    }

    // Create a map of headers to their column indexes
    private Map<String, Integer> createHeaderIndexMap(Row headerRow) {
        Map<String, Integer> headerIndexMap = new HashMap<>();
        for (Cell cell : headerRow) {
            String header = cell.getStringCellValue().trim();
            headerIndexMap.put(header, cell.getColumnIndex());
        }
        return headerIndexMap;
    }

    // Populate the DTO from a row, using the header-index map
    private void populateClaimDTOFromRow(ClaimsSuperDto claim, Row row, Map<String, Integer> headerIndexMap) throws ReflectiveOperationException {
        for (Map.Entry<String, String> entry : HeaderMapping.HEADER_TO_FIELD_MAP.entrySet()) {
            String excelHeader = entry.getKey();
            String fieldName = entry.getValue();
            Integer columnIndex = headerIndexMap.get(excelHeader);

            if (columnIndex != null) {
                String cellValue = getCellValue(row.getCell(columnIndex));
                setFieldValue(claim, fieldName, cellValue);
            }
        }
    }

    // Get the value of a cell as a string
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // Dynamically set the value of a DTO's field using reflection
    private void setFieldValue(ClaimsSuperDto claim, String fieldName, String value) throws ReflectiveOperationException {
        try {
            Field field = claim.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);  // Make private field accessible
            Class<?> fieldType = field.getType();

            if (fieldType == String.class) {
                field.set(claim, value);
            } else if (fieldType == Double.class || fieldType == double.class) {
                field.set(claim, value.isEmpty() ? 0 : Double.parseDouble(value));
            } else if (fieldType == Integer.class || fieldType == int.class) {
                field.set(claim, value.isEmpty() ? 0 : Integer.parseInt(value));
            } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                field.set(claim, Boolean.parseBoolean(value));
            } else {
                // Handle other types or throw an exception if unknown type
                throw new IllegalArgumentException("Unsupported field type: " + fieldType);
            }
        } catch (NoSuchFieldException e) {
            throw new ReflectiveOperationException("Field not found: " + fieldName, e);
        }
    }
}
