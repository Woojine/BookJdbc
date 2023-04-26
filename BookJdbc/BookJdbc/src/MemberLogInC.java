import dao.BookDAO;
import dao.MemDAO;
import dao.OccupiedBookDAO;
import vo.BookVO;
import vo.OccupiedBookVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class MemberLogInC {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    MemDAO memDAO = new MemDAO();
    OccupiedBookDAO occbDAO = new OccupiedBookDAO();
    BookDAO bookDAO = new BookDAO();


    public void memberLogin() {
        int tmp = memDAO.Login();
        boolean run = true;
        while (run) {
            if (tmp == 1) {
                System.out.println("로그인 성공");
                System.out.println("[1]도서 대여 [2]도서 반납 [3]게시판 [4]마이페이지 [5]초기 메뉴");
                int sel = sc.nextInt();
                switch (sel) {
                    case 1:
                        List<BookVO> booklist = bookDAO.bookSelect();
                        bookDAO.bookSelectPrn(booklist);
                        occbDAO.borrow();
                        break;
                    case 2:
                        occbDAO.returnBook();
                        break;
                    case 3:
                        PostComment.postMenuSelect();
                        break;
                    case 4:
                        boolean run2 = true;
                        while (run2) {
                            System.out.println("[1]내 도서대출목록 [2]회원정보수정 [3]회원 탈퇴 [4]이전 메뉴");
                            int sel2 = sc.nextInt();
                            switch (sel2) {
                                case 1:
                                    List<OccupiedBookVO> occblist = occbDAO.personalOCCB();
                                    occbDAO.personalOCCBPrn(occblist);
                                    break;
                                case 2:
                                    memDAO.memUpdate();
                                    break;
                                case 3:
                                    memDAO.memDelete();
                                    run2 = false;
                                    run = false;
                                    break;
                                case 4:
                                    run2 = false;
                                    break;
                            }
                        }
                    case 5:
                        run = false;
                        break;
                }
            } else if (tmp == -1) {
                System.out.println("아이디/비밀번호가 일치하지 않습니다.");
                tmp = memDAO.Login();
            }
        }
    }
}

