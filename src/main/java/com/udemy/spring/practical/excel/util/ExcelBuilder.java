package com.udemy.spring.practical.excel.util;

import com.udemy.spring.practical.excel.vo.Book;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

// Apache POI 라이브러리를 이용하여 Excel 파일을 생성하는 클래스
public class ExcelBuilder extends AbstractXlsView {

    /**
     * @param model    Model 객체
     * @param workbook
     * @param request
     * @param response
     * @throws Exception
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        System.out.println("ExcelBuilder.buildExcelDocument() called ...");

        List<Book> books = (List<Book>) model.get("books"); // Model 객체에서 "books" List 객체를 꺼내옴

        // Excel Sheet 만들기
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("책 목록");
        sheet.setDefaultColumnWidth(20); // Cell 기본 너비

        // Cell(열) 스타일 지정
        CellStyle style = workbook.createCellStyle();

        // 폰트 설정
        Font font = workbook.createFont();
        font.setFontName("맑은고딕");
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());

        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(font);

        // 첫 번째 Row(행) 추가
        HSSFRow title = sheet.createRow(0);
        title.createCell(0).setCellValue("ISBN");
        title.getCell(0).setCellStyle(style);
        title.createCell(1).setCellValue("제목");
        title.getCell(1).setCellStyle(style);
        title.createCell(2).setCellValue("저자");
        title.getCell(2).setCellStyle(style);
        title.createCell(3).setCellValue("출간일");
        title.getCell(3).setCellStyle(style);
        title.createCell(4).setCellValue("가격");
        title.getCell(4).setCellStyle(style);

        // 데이터 Row(행) 추가
        int rowCnt = 1;

        for (Book book : books) {
            HSSFRow row = sheet.createRow(rowCnt++);
            row.createCell(0).setCellValue(book.getIsbn());
            row.createCell(1).setCellValue(book.getSubject());
            row.createCell(2).setCellValue(book.getAuthor());
            row.createCell(3).setCellValue(book.getPublishDate());
            row.createCell(4).setCellValue(book.getPrice());
        }
    }
}
