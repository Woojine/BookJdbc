import dao.PostDAO;
import vo.PostVO;

import java.util.List;
import java.util.Scanner;

    public class PostComment {
//    public static void main(String[] args) {
//        postMenuSelect();
//    }

        public static void postMenuSelect() {
            Scanner sc = new Scanner(System.in);
            // 게시판, 댓글 테이블 객체 생성
            PostDAO postDAO = new PostDAO();
            boolean run = true;
            while (run) {
                // 1번 기능: 게시판 전체 글 + 메뉴 선택 보여주기 2,3,10번 기능 선택
                List<PostVO> pList = postDAO.postSelect();
                postDAO.postSelectPrn(pList);
                System.out.println("---------------------------------------------------------");
                System.out.print("[1] 게시글 작성하기, [2] 게시글 조회하기, [3] 메인 메뉴로 돌아가기 : ");
                int num1 = sc.nextInt();
                switch (num1) {
                    case 1: // 2번 기능: 게시글 작성하기 메뉴
                        postDAO.postInsert();
                        break;
                    case 2: // 3번 기능: 게시글 조회하기 메뉴 4,7,8,9,10,11번 기능 선택
                        postDAO.postShow(pList);
                        System.out.print("[1] 게시글 수정하기, [2] 게시글 삭제하기, [3] 댓글 작성하기, [4] 댓글 수정하기, [5] 댓글 삭제하기 [6] 게시판으로 이동: ");
                        int num2 = sc.nextInt();
                        switch (num2) {
                            case 1: // 4번 기능: 게시글 수정하기 메뉴, 5,6번 기능 선택
                                System.out.println("수정할 부분을 선택하세요.");
                                System.out.print("[1] 글 제목, [2] 글 내용 : ");
                                int num3 = sc.nextInt();
                                switch (num3) {
                                    case 1: // 5번 기능: 글 제목 수정하기
                                        postDAO.postTitleUpdate();
                                        break;
                                    case 2: // 6번 기능: 글 내용 수정하기
                                        postDAO.postContentUpdate();
                                        break;
                                }
                                break;
                            case 2: // 7번 기능: 게시글 삭제하기 메뉴
                                postDAO.postDelete();
                                break;
                            case 3: // 8번 기능: 댓글 작성하기 메뉴
                                postDAO.commentMake();
                                break;
                            case 4: // 9번 기능: 댓글 수정하기 메뉴
                                postDAO.commentUpdate();
                                break;
                            case 5: // 10번 기능: 댓글 삭제하기 메뉴
                                postDAO.commentDelete();
                            case 6: // 11번 기능: 게시판 이동
                                break;
                        }
                        break;
                    case 3: // 12번 기능: 메인 메뉴 화면으로 이동(게시판, 댓글 테이블 이외의 다른 테이블 메뉴로 이동)
                        run = false;
                        break; // 이전 메뉴로
                }
            }
        }
    }
