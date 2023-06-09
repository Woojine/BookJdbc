package dao;

import util.Common;
import vo.MemVO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    private static String logInid = "";
    private static String pw = "";

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getLogInid() {
        return logInid;
    }

    public void setLogInid(String logInid) {
        this.logInid = logInid;
    }

    public int Login() {
        System.out.println("로그인할 정보를 입력하세요.");
        System.out.print("아이디를 입력하세요 : ");
        logInid = sc.next();
        setLogInid(logInid);
        System.out.print("비밀번호를 입력하세요 : ");
        pw = sc.next();
        setPw(pw);
        String sql = "SELECT USER_PW FROM MEMBER WHERE USER_ID = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, logInid);
            rs = pStmt.executeQuery();
            rs.next();
            if (rs.getString("USER_PW").equals(pw)) {
                return 1;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);
        return 0;
    }

    public List<MemVO> memSelect() {
        List<MemVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SIGN_NO, USER_ID, RPAD(SUBSTR(USER_PW, 1, 2), 18, '*') AS \"USER_PW\", USER_NAME, PHONE, LIBNO FROM MEMBER";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                BigDecimal signNo = rs.getBigDecimal("SIGN_NO");
                String memSid = rs.getString("USER_ID");
                String pw = rs.getString("USER_PW");
                String name = rs.getString("USER_NAME");
                String phone = rs.getString("PHONE");
                int libNo = rs.getInt("LIBNO");
                MemVO vo = new MemVO(signNo, memSid, pw, name, phone,libNo);
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

    public void memSelectPrn(List<MemVO> list) {
        System.out.println("회원번호  회원아이디  회원비밀번호  회원이름  전화번호");
        System.out.println("---------------------------------------------");
        for (MemVO e : list) {
            System.out.print(e.getSignNo() + " ");
            System.out.print(e.getId() + " ");
            System.out.print(e.getPw() + " ");
            System.out.print(e.getName() + " ");
            System.out.print(e.getPh() + " ");
            System.out.println();
        }
        System.out.println("---------------------------------------------");
    }

    // 로그인 기능 구현


    // 회원가입
    public void memInsert() {
        System.out.println("회원 가입할 정보를 입력하세요.");
        System.out.print("회원번호(10자리) : ");
        BigDecimal signNo = sc.nextBigDecimal();
        String id = null;
        try {
            while (true) {
                System.out.print("회원아이디 : ");
                id = sc.next();
                String sql = "SELECT USER_ID FROM MEMBER WHERE USER_ID = ?";
                conn = Common.getConnection();
                pStmt = conn.prepareStatement(sql);
                pStmt.setString(1, id);
                rs = pStmt.executeQuery();
                if (rs.next()) {
                    if (rs.getString(1).equalsIgnoreCase(id)) {
                        System.out.println("이미 사용중인 아이디입니다.");
                        return;
                    } else System.out.println("사용가능한 아이디 입니다.");
                }
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        System.out.print("회원비밀번호 : ");
        String pw = sc.next();
        System.out.print("회원이름 : ");
        String name = sc.next();
        System.out.print("전화번호 : ");
        String number = sc.next();
        String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?)";
        int libNo = 1;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setBigDecimal(1, signNo);
            pStmt.setString(2, id);
            pStmt.setString(3, pw);
            pStmt.setString(4, name);
            pStmt.setString(5, number);
            pStmt.setInt(6, libNo);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
        System.out.println(name + "님의 회원가입이 성공적으로 완료되었습니다.");
    }

    // 회원 탈퇴
    public void memDelete() {
        System.out.print("삭제할 회원의 이름을 입력 하세요 : ");
        String name = sc.next();
        System.out.print("삭제할 회원의 아이디를 입력 하세요 : ");
        String id = sc.next();
        System.out.print("삭제할 회원의 비밀번호를 입력 하세요 : ");
        String pw = sc.next();
        String sql = "DELETE FROM MEMBER WHERE USER_NAME = ? AND USER_ID = ? AND USER_PW = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, id);
            pStmt.setString(3, pw);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        System.out.println("회원탈퇴가 완료되었습니다.");
    }

    public void memUpdate() {
        System.out.print("변경할 회원의 이름을 입력 하세요 : ");
        String name = sc.next();
        System.out.println("수정하려는 회원의 정보를 선택하세요 : [1]회원아이디 [2]회원비밀번호 [3]회원전화번호 ");
        int sel = sc.nextInt();
        try {
            conn = Common.getConnection();
            switch (sel) {
                case 1:
                    String sql = "UPDATE MEMBER SET USER_ID = ? WHERE USER_NAME = ?";
                    pStmt = conn.prepareStatement(sql);
                    System.out.print("새로운 아이디를 입력하세요 : ");
                    String id = sc.next();
                    pStmt.setString(1, id);
                    pStmt.setString(2, name);
                    System.out.println("아이디가 수정되었습니다 : " + id);
                    break;
                case 2:
                    String sql1 = "UPDATE MEMBER SET USER_PW = ? WHERE USER_NAME = ?";
                    pStmt = conn.prepareStatement(sql1);
                    System.out.print("새로운 비밀번호를 입력하세요 : ");
                    String pw = sc.next();
                    pStmt.setString(1, pw);
                    pStmt.setString(2, name);
                    System.out.println("비밀번호가 수정되었습니다 : " + pw);
                    break;
                case 3:
                    String sql2 = "UPDATE MEMBER SET PHONE = ? WHERE USER_NAME = ?";
                    pStmt = conn.prepareStatement(sql2);
                    System.out.print("새로운 전화번호를 입력하세요 : ");
                    String phone = sc.next();
                    pStmt.setString(1, phone);
                    pStmt.setString(2, name);
                    System.out.println("전화번호가 수정되었습니다 : " + phone);
                    break;
            }
            pStmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);

    }
}
