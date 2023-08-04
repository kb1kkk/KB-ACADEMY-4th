package cinema.dtos;

public class CustomerDto {
    private int cnum;
    private	String cid, cpw, cname, ctel;
    
    // 생성자
    public CustomerDto() {}
    
    public CustomerDto(int cnum, String cid, String cpw, String cname, String ctel) {
        super();
        this.cnum = cnum;
        this.cid = cid;
        this.cpw = cpw;
        this.cname = cname;
        this.ctel = ctel;
    }

    // getter,setter
    public int getCnum() {
        return cnum;
    }

    public void setId(int cnum) {
        this.cnum = cnum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCpw() {
        return cpw;
    }

    public void setCpw(String cpw) {
        this.cpw = cpw;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getCtel() {
        return ctel;
    }

    public void setCtel(String ctel) {
        this.ctel = ctel;
    }

    // toString()
    @Override
    public String toString() {
        return "cnum=" + cnum + ", cid=" + cid + ", cpw=" + cpw + ", cname=" + cname +", ctel=" + ctel;
    }

    
    
}