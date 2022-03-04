package com.salon.custom.service;

import com.salon.custom.dto.point.point_user.PointUserDTO;
import com.salon.custom.dto.review.ReviewDTO;
import com.salon.custom.dto.user.UserDTO;
import com.salon.custom.util.DateUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class ExcelExporterService {
    static String[] HEADER_USERS = {"ID", "ユーザー名", "ふりがな", "電話番号", "登録日", "住所", "性別", "店舗", "メール"};
    static String SHEET_USERS = "Users";
    static String[] HEADER_REVIEWS = {"会員ID", "スタッフ名", "お客様名", "店舗", "コメントの日付", "利用日", "コメント",
            "コリへの的確さ", "力強さ", "施術中の説明", "術後のスッキリ感", "言葉や態度", "スタッフの清潔感", "平均点"};
    static String SHEET_REVIEWS = "Reviews";
    static String[] HEADER_HISTORY = {"ID", "ユーザー名", "担当スタッフ", "取得月日", "種類", "ポイント数", "レビュー評価点"};
    static String SHEET_HISTORY = "HistoryVisit";


    public static ByteArrayInputStream usersToExcel(List<UserDTO> userDTOS) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_USERS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADER_USERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADER_USERS[col]);
            }

            int rowIdx = 1;
            for (UserDTO userDTO : userDTOS) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(userDTO.getCustomerId());
                row.createCell(1).setCellValue(userDTO.getName());
                row.createCell(2).setCellValue(userDTO.getFuriganaName());
                row.createCell(3).setCellValue(userDTO.getPhoneNumber());
                String createdTime = DateUtils.formatDateJapanToString(userDTO.getCreatedTime());
                row.createCell(4).setCellValue(createdTime);
                row.createCell(5).setCellValue(userDTO.getAddress());
                String sex = userDTO.getSex();
                switch (sex) {
                    case "male":
                        sex = "男性";
                        break;
                    case "female":
                        sex = "女性";
                        break;
                    default:
                        sex = "";
                }
                row.createCell(6).setCellValue(sex);
                row.createCell(7).setCellValue(userDTO.getStoreName());
                row.createCell(8).setCellValue(userDTO.getEmail());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream reviewsToExcel(List<ReviewDTO> reviewDTOS) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_REVIEWS);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADER_REVIEWS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADER_REVIEWS[col]);
            }

            int rowIdx = 1;
            for (ReviewDTO reviewDTO : reviewDTOS) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(reviewDTO.getCustomerId());
                row.createCell(1).setCellValue(reviewDTO.getStaffName());
                row.createCell(2).setCellValue(reviewDTO.getCustomerName());
                row.createCell(3).setCellValue(reviewDTO.getStoreName());
                row.createCell(4).setCellValue(DateUtils.formatDateJapanToString(reviewDTO.getDateReview()));
                row.createCell(5).setCellValue(DateUtils.formatDateJapanToString(reviewDTO.getDateVisit()));
                row.createCell(6).setCellValue(reviewDTO.getComment());
                row.createCell(7).setCellValue(reviewDTO.getRating1());
                row.createCell(8).setCellValue(reviewDTO.getRating2());
                row.createCell(9).setCellValue(reviewDTO.getRating3());
                row.createCell(10).setCellValue(reviewDTO.getRating4());
                row.createCell(11).setCellValue(reviewDTO.getRating5());
                row.createCell(12).setCellValue(reviewDTO.getRating6());
                DecimalFormat df = new DecimalFormat("#.#");
                row.createCell(13).setCellValue(df.format(reviewDTO.getRatingAverage()));
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream historyVisitToExcel(List<PointUserDTO> pointUserDTOS) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET_HISTORY);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADER_HISTORY.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADER_HISTORY[col]);
            }

            int rowIdx = 1;
            for (PointUserDTO pointUserDTO : pointUserDTOS) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(pointUserDTO.getCustomerId());
                row.createCell(1).setCellValue(pointUserDTO.getCustomerName());
                row.createCell(2).setCellValue(pointUserDTO.getStaffName());
                row.createCell(3).setCellValue(DateUtils.formatDateJapanToString(pointUserDTO.getTimeConfirm()));
                String type = pointUserDTO.getPointType();
                switch (type) {
                    case "check_in_point":
                        type = "来店ポイント";
                        break;
                    case "use_point":
                        type = "利用ポイント";
                        break;
                    case "gacha_point":
                        type = "抽選ポイント";
                        break;
                    case "review_point":
                        type = "レビューポイント";
                        break;
                    default:
                        type = "";
                        break;
                }
                row.createCell(4).setCellValue(type);
                String numberOfPoints = String.valueOf(pointUserDTO.getNumberOfPoints());
                if (numberOfPoints.equals("null")) {
                    numberOfPoints = "";
                }
                String ratingAvg = String.valueOf(pointUserDTO.getTotalRating());
                if (ratingAvg.equals("null")) {
                    ratingAvg = "";
                }
                row.createCell(5).setCellValue(numberOfPoints);
                row.createCell(6).setCellValue(ratingAvg);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }


}
