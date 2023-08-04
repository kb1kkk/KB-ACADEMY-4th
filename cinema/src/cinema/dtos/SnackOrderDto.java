package cinema.dtos;

public class SnackOrderDto {
    private int sonum;   // 간식주문번호
    private int popnum;  // 팝콘번호
    private int popcnt;  // 팝콘수량
    private int bnum;    // 음료번호
    private int bcnt;    // 음료수량
    private int stcnt;   // 간식총금액

    public SnackOrderDto() {
    }

    public SnackOrderDto(int sonum, int popnum, int popcnt, int bnum, int bcnt, int stcnt) {
        super();
        this.sonum = sonum;
        this.popnum = popnum;
        this.popcnt = popcnt;
        this.bnum = bnum;
        this.bcnt = bcnt;
        this.stcnt = stcnt;
    }

    public int getSonum() {
        return sonum;
    }
    public void setSonum(int sonum) {
        this.sonum = sonum;
    }

    public int getPopnum() {
        return popnum;
    }
    public void setPonum(int ponum) {
        this.popnum = popnum;
    }
    public int getPopcnt() {
        return popcnt;
    }
    public void setPopcnt(int popcnt) {
        this.popcnt = popcnt;
    }
    public int getBnum() {
        return bnum;
    }
    public void setBnum(int bnum) {
        this.bnum = bnum;
    }
    public int getBcnt() { return bcnt; }
    public void setBcnt(int Bcnt) {
        this.bcnt = bcnt;
    }
    public int getStcnt() {
        return stcnt;
    }
    public void getStcnt(int stcnt) {
        this.stcnt = stcnt;
    }

    @Override
    public String toString() {
        return "SnackOrderDto [sonum=" + sonum + ", popnum=" + popnum + ", popcnt=" + popcnt
                + ", bnum=" + bnum + ", bcnt=" + bcnt + ", stcnt=" + stcnt + "]";
    }
}

