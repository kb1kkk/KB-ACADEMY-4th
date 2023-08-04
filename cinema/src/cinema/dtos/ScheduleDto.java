package cinema.dtos;

public class ScheduleDto {
	private int scnum;
	private String scdate;
	private String sctime;
	private int mnum;
	private int thnum;
	private String mname;

	public ScheduleDto(int scnum, String scdate, String sctime, int mnum, int thnum, String mname) {
		super();
		this.scnum = scnum;
		this.scdate = scdate;
		this.sctime = sctime;
		this.mnum = mnum;
		this.thnum = thnum;
		this.mname = mname;
	}

	public ScheduleDto(int scnum, String scdate, String sctime, int mnum, int thnum) {
		super();
		this.scnum = scnum;
		this.scdate = scdate;
		this.sctime = sctime;
		this.mnum = mnum;
		this.thnum = thnum;
		this.mname = null;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public int getScnum() {
		return scnum;
	}

	public void setScnum(int scnum) {
		this.scnum = scnum;
	}

	public String getScdate() {
		return scdate;
	}

	public void setScdate(String scdate) {
		this.scdate = scdate;
	}

	public String getSctime() {
		return sctime;
	}

	public void setSctime(String sctime) {
		this.sctime = sctime;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public int getThnum() {
		return thnum;
	}

	public void setThnum(int thnum) {
		this.thnum = thnum;
	}

	@Override
	public String toString() {
		return "ScheduleDto [scnum=" + scnum + ", scdate=" + scdate + ", sctime=" + sctime + ", mnum=" + mnum
				+ ", thnum=" + thnum + "]";
	}

}
