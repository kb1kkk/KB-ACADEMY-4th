package cinema.dtos;

public class SnackDto {
    private int snum;      // 간식번호
    private String stype;  // 간식종류
    private String sname;  // 간식이름
    private int sprice;    // 간식가격

    public SnackDto() {
    }

    public SnackDto(int snum, String stype, String sname, int sprice) {
        super();
        this.snum = snum;
        this.stype = stype;
        this.sname = sname;
        this.sprice = sprice;
    }

    public int getSnum() {
        return snum;
    }
    public void setSnum(int snum) {
        this.snum = snum;
    }
    public String getStype() {
        return stype;
    }
    public void setStype(String stype) {
        this.stype = stype;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public int getSprice() {
        return sprice;
    }
    public void setSorice() {
        this.sprice = sprice;
    }

    @Override
    public String toString() {
        return "SnackDto [snum=" + snum + ", stype=" + stype + ", sname=" + sname
                + ", sprice=" + sprice + "]";
    }
}
