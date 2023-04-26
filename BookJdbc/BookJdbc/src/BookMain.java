import dao.BookDAO;
import dao.MemDAO;
import vo.BookVO;

import java.util.List;
import java.util.Scanner;

public class BookMain {
    public static void main(String[] args) {
        AdminLogIn adminLogIn = new AdminLogIn();
        BookDAO bookdao = new BookDAO();
        MemDAO memDAO = new MemDAO();
        MemberLogInC memC = new MemberLogInC();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("[1]회원 가입 [2]회원 로그인 [3]도서 검색 [4]관리자 로그인 [5] 종료");
            System.out.print("메뉴를 선택하세요. : ");
            int sel1 = sc.nextInt();
            switch (sel1) {
                case 1:
                    memDAO.memInsert();
                    break;
                case 2:
                    memC.memberLogin();
                    break;
                case 3:
                    bookdao.indexBook();
                    break;
                case 4:
                    adminLogIn.AdminLogin();
                    break;
                case 5:
                    return;

                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    }
