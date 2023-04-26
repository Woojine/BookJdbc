package vo;

import java.math.BigDecimal;

public class MemVO {
    private BigDecimal signNo; // 회원 번호
    private String id; // 회원 아이디
    private String pw; // 회원 비밀번호
    private String name; // 회원 이름
    private String ph; // 전화번호
    private int libNo; //

    public MemVO(BigDecimal signNo, String id, String pw, String name, String ph, int libNo) {
        this.signNo = signNo;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.ph = ph;
        this.libNo =libNo;
    }

    public BigDecimal getSignNo() {
        return signNo;
    }

    public void setSignNo(BigDecimal signNo) {
        this.signNo = signNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }
    public int getlibNo() {
        return libNo;
    }
    public void setlibNo(int libNo) {
        this.libNo = libNo;
    }
}
