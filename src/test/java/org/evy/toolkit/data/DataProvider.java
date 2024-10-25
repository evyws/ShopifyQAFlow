package org.evy.toolkit.data;

import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public final class DataProvider {

    private static final Faker faker=new Faker();

    private static final File file = new File(System.getProperty("user.dir") + "/src/test/resources/data.xlsx");

    private DataProvider(){}


    private static Object[][] getData(String sheetName) {
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[i - 1][j] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                // Convert numeric values to string
                                data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                // Handle formula cells
                                data[i - 1][j] = cell.getCellFormula();
                                break;
                            case BLANK:
                            default:
                                data[i - 1][j] = "";
                                break;
                        }
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error reading data from Excel file: " + e.getMessage(), e);
        }

        return data;
    }

    @org.testng.annotations.DataProvider(name = "productCategoriesData")
    public static Object[][]getProductCategoriesData(){
        return getData("productCategories");
    }

    @org.testng.annotations.DataProvider(name = "productNameData")
    public static Object[][]getProductNameData(){
        return getData("productName");
    }

    @org.testng.annotations.DataProvider(name = "addProductToCartData")
    public static Object[][]getAddToCartData(){
        return getData("addProductToCart");
    }

    @org.testng.annotations.DataProvider(name = "removeProductFromCartData")
    public static Object[][]getRemoveProductFromCartData(){
        return getData("removeProductFromCart");
    }

    @org.testng.annotations.DataProvider(name="loginData")
    public static Object[][]getLoginData(){
        return getData("loginData");
    }

    @org.testng.annotations.DataProvider(name = "registrationData")
    public static Object[][]getRegistrationData(){
        String password=faker.internet().password(12,14,true,true,true);
        return new Object[][]{
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),password,password,"valid","Thank you for registering"},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"passwords","passwords","invalid","Minimum of different classes of characters in password is 3"},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"123456789","123456789","invalid","Minimum of different classes of characters in password is 3"},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"password12345","password12345","invalid","Minimum of different classes of characters in password is 3"},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),"X1z","X1z","invalid","Minimum length of this field must be equal or greater than 8 symbols"},
                {faker.name().firstName(),faker.name().lastName(),"@email.com",password,password,"invalid","Please enter a valid email address"},
                {faker.name().firstName(),faker.name().lastName(),"user@.com",password,password,"invalid","Please enter a valid email address"},
                {" ",faker.name().lastName(),faker.internet().emailAddress(),password,password,"invalid","This is a required field."},
                {faker.name().firstName()," ",faker.internet().emailAddress(),password,password,"invalid","This is a required field."},
                {faker.name().firstName(),faker.name().lastName()," ",password,password,"invalid","This is a required field."},
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress()," ",password,"invalid","This is a required field."},





        };
    }

    @org.testng.annotations.DataProvider(name= "endToEndData")
    public static Object[][]getEndToEndData(){
        String password=faker.internet().password(12,14,true,true,true);
        return new Object[][]{
                {faker.name().firstName(),faker.name().lastName(),faker.internet().emailAddress(),password,password,"Men","Tops","Jackets","Montana Wind Jacket","L","Black","1"
                ,faker.name().firstName(),faker.name().lastName(),faker.address().fullAddress(),faker.address().city(),faker.address().zipCode(),faker.address().country()
                        ,faker.phoneNumber().cellPhone(),"Thank you for your purchase!"}
        };
    }





}
