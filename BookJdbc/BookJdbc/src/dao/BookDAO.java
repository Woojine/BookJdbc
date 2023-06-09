package dao;

import util.Common;
import vo.BookVO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<BookVO> bookSelect() {
        List<BookVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM BOOK";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                BigDecimal isbn = rs.getBigDecimal("ISBN_NO");
                String name = rs.getString("BOOK_NAME");
                String pub = rs.getString("PUBLISHER");
                String auth = rs.getString("AUTHOR");
                String date = rs.getString("PUB_DATE");
                String occ = rs.getString("IS_OCCUPIED");
                int libNo = rs.getInt("LIBNO");
                BookVO vo = new BookVO(isbn, name, pub, auth, date, occ, libNo);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void indexBook() {
        List<BookVO> tmpList = new ArrayList<>();
        boolean run = true;
        while (run) {
            System.out.println("========검색창========");
            System.out.println("[1]도서명 [2]ISBN [3]출판사 [4]지은이 [5]이전 메뉴");
            int sel = sc.nextInt();
            try {
                switch (sel) {
                    case 1:
                        System.out.print("도서명을 입력하세요. : ");
                        sc.nextLine();
                        String bname = sc.nextLine();
                        String indexsql = "SELECT * FROM BOOK WHERE BOOK_NAME = ?";
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(indexsql);
                        pStmt.setString(1, bname);
                        rs = pStmt.executeQuery();
                        while (rs.next()) {
                            BigDecimal isbn = rs.getBigDecimal("ISBN_NO");
                            String name = rs.getString("BOOK_NAME");
                            String pub = rs.getString("PUBLISHER");
                            String auth = rs.getString("AUTHOR");
                            String date = rs.getString("PUB_DATE");
                            String occ = rs.getString("IS_OCCUPIED");
                            int libNo = rs.getInt("LIBNO");
                            BookVO tmpvo = new BookVO(isbn, name, pub, auth, date, occ, libNo);
                            tmpList.add(tmpvo);
                        }
                        IndexPrn(tmpList);
                        break;
                    case 2:
                        System.out.print("ISBN 코드를 입력하세요. : ");
                        sc.nextLine();
                        String isbnindex = sc.nextLine();
                        String indexsql2 = "SELECT * FROM BOOK WHERE ISBN_NO = ?";
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(indexsql2);
                        pStmt.setString(1, isbnindex);
                        rs = pStmt.executeQuery();
                        while (rs.next()) {
                            BigDecimal isbn = rs.getBigDecimal("ISBN_NO");
                            String name = rs.getString("BOOK_NAME");
                            String pub = rs.getString("PUBLISHER");
                            String auth = rs.getString("AUTHOR");
                            String date = rs.getString("PUB_DATE");
                            String occ = rs.getString("IS_OCCUPIED");
                            int libNo = rs.getInt("LIBNO");
                            BookVO tmpvo = new BookVO(isbn, name, pub, auth, date, occ, libNo);
                            tmpList.add(tmpvo);
                        }
                        IndexPrn(tmpList);
                        break;
                    case 3:
                        System.out.print("출판사를 입력하세요. : ");
                        sc.nextLine();
                        String indexpub = sc.nextLine();
                        String indexsql3 = "SELECT * FROM BOOK WHERE PUBLISHER = ?";
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(indexsql3);
                        pStmt.setString(1, indexpub);
                        rs = pStmt.executeQuery();
                        while (rs.next()) {
                            BigDecimal isbn = rs.getBigDecimal("ISBN_NO");
                            String name = rs.getString("BOOK_NAME");
                            String pub = rs.getString("PUBLISHER");
                            String auth = rs.getString("AUTHOR");
                            String date = rs.getString("PUB_DATE");
                            String occ = rs.getString("IS_OCCUPIED");
                            int libNo = rs.getInt("LIBNO");
                            BookVO tmpvo = new BookVO(isbn, name, pub, auth, date, occ, libNo);
                            tmpList.add(tmpvo);
                        }
                        IndexPrn(tmpList);
                        break;
                    case 4:
                        System.out.print("지은이 이름을 입력하세요. : ");
                        sc.nextLine();
                        String indexauth = sc.nextLine();
                        String indexsql4 = "SELECT * FROM BOOK WHERE AUTHOR = ?";
                        conn = Common.getConnection();
                        pStmt = conn.prepareStatement(indexsql4);
                        pStmt.setString(1, indexauth);
                        rs = pStmt.executeQuery();
                        while (rs.next()) {
                            BigDecimal isbn = rs.getBigDecimal("ISBN_NO");
                            String name = rs.getString("BOOK_NAME");
                            String pub = rs.getString("PUBLISHER");
                            String auth = rs.getString("AUTHOR");
                            String date = rs.getString("PUB_DATE");
                            String occ = rs.getString("IS_OCCUPIED");
                            int libNo = rs.getInt("LIBNO");
                            BookVO tmpvo = new BookVO(isbn, name, pub, auth, date, occ, libNo);
                            tmpList.add(tmpvo);
                        }
                        IndexPrn(tmpList);
                        break;
                    case 5:
                        run = false;
                        break;
                }
                Common.close(rs);
                Common.close(stmt);
                Common.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void bookSelectPrn(List<BookVO> list) {
        System.out.println("================================도서 정보===============================");
        System.out.println("      ISBN       도서명        출판사    지은이    발행일    대출여부");
        System.out.println("----------------------------------------------------------------------");
        for (BookVO e : list) {
            System.out.print(e.getIsbn() + " ");
            System.out.print(e.getName() + " | ");
            System.out.print(e.getPub() + " | ");
            System.out.print(e.getAuth() + " | ");
            System.out.print(e.getDate() + " | ");
            System.out.println(e.getOcc());
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    public void IndexPrn(List<BookVO> tmpList) {
        System.out.println("================================도서 정보===============================");
        System.out.println("      ISBN       도서명        출판사    지은이    발행일    대출여부");
        System.out.println("----------------------------------------------------------------------");
        for (BookVO e : tmpList) {
            System.out.print(e.getIsbn() + " ");
            System.out.print(e.getName() + " | ");
            System.out.print(e.getPub() + " | ");
            System.out.print(e.getAuth() + " | ");
            System.out.print(e.getDate() + " | ");
            System.out.println(e.getOcc());
        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    public void bookInsert() {
        System.out.print("13자리의 ISBN CODE를 입력하세요 : ");
        BigDecimal isbn = sc.nextBigDecimal();
        System.out.print("책 제목을 입력하세요. : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("출판사 이름을 입력하세요. : ");
        String pub = sc.next();
        System.out.print("지은이 이름을 입력하세요. : ");
        sc.nextLine();
        String auth = sc.nextLine();
        System.out.print("발행일을 입력하세요.(YYYY-MM-DD) : ");
        String date = sc.next();
        System.out.print("X를 입력하세요. : ");
        String occ = sc.next();

        String sql = "INSERT INTO BOOK VALUES(?,?,?,?,?,?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setBigDecimal(1, isbn);
            pStmt.setString(2, name);
            pStmt.setString(3, pub);
            pStmt.setString(4, auth);
            pStmt.setString(5, date);
            pStmt.setString(6, occ);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void bookDelete() {
        System.out.print("삭제할 책의 이름을 입력 : ");
        String name = sc.nextLine();
        String sql = "DELETE FROM BOOK WHERE BOOK_NAME = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void bookDeleteISBN() {
        System.out.print("삭제하고자 하는 책의 ISBN 코드르 입력하세요(13자리)");
        BigDecimal isbn = sc.nextBigDecimal();
        String sql = "DELETE FROM BOOK WHERE ISBN_NO = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setBigDecimal(1, isbn);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void bookUpdate() {
        System.out.print("수정하고자 하는 도서명을 입력해주세요. : ");
        String name = sc.next();
        System.out.println("수정하려는 도서 정보가 무엇입니까?");
        System.out.println("[1]ISBN코드 [2]도서명 [3]출판사");
        System.out.println("[4]지은이 [5]발행일");
        int sel = sc.nextInt();

        try {
            conn = Common.getConnection();

            switch (sel) {
                case 1:
                    String sql1 = "UPDATE BOOK SET ISBN_NO = ? WHERE BOOK_NAME = ?";
                    pStmt = conn.prepareStatement(sql1);
                    System.out.print("수정하고자 하는 ISBN 13자의 CODE를 입력하세요. : ");
                    BigDecimal isbn = sc.nextBigDecimal();
                    pStmt.setBigDecimal(1, isbn);
                    pStmt.setString(2, name);
                    break;
                case 2:
                    String sql2 = "UPDATE BOOK SET BOOK_NAME = ? WHERE BOOK_NAME = ?";
                    pStmt = conn.prepareStatement(sql2);
                    System.out.print("수정하고자 하는 도서명을 입력하세요. : ");
                    String fixedName = sc.next();
                    pStmt.setString(1, fixedName);
                    pStmt.setString(2, name);
                    break;
                case 3:
                    String sql3 = "UPDATE BOOK SET PUBLISHER = ? WHERE BOOK_NAME = ?";
                    pStmt = conn.prepareStatement(sql3);
                    System.out.print("수정하고자 하는 출판사명을 입력하세요. : ");
                    String pub = sc.next();
                    pStmt.setString(1, pub);
                    pStmt.setString(2, name);
                    break;
                case 4:
                    String sql4 = "UPDATE BOOK SET AUTHOR = ? WHERE BOOK_NAME = ?";
                    pStmt = conn.prepareStatement(sql4);
                    System.out.print("수정하고자 하는 지은이 이름을 입력하세요. : ");
                    String auth = sc.next();
                    pStmt.setString(1, auth);
                    pStmt.setString(2, name);
                    break;
                case 5:
                    String sql5 = "UPDATE BOOK SET PUB_DATE = ? WHERE BOOK_NAME = ?";
                    pStmt = conn.prepareStatement(sql5);
                    System.out.print("수정하고자 하는 발행일을 입력하세요. : ");
                    String date = sc.next();
                    pStmt.setString(1, date);
                    pStmt.setString(2, name);
                    break;
            }
            int update = pStmt.executeUpdate();
            System.out.println(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }
}