package cinema.dtos;

public class MovieDto {
	private int mnum;
	private String title;
	private int runtime;
	private String mstartdate;
	private String mclosedate;

	public MovieDto(int mnum, String title, int runtime, String mstartdate, String mclosedate) {
		super();
		this.mnum = mnum;
		this.title = title;
		this.runtime = runtime;
		this.mstartdate = mstartdate;
		this.mclosedate = mclosedate;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getMstartdate() {
		return mstartdate;
	}

	public void setMstartdate(String mstartdate) {
		this.mstartdate = mstartdate;
	}

	public String getMclosedate() {
		return mclosedate;
	}

	public void setMclosedate(String mclosedate) {
		this.mclosedate = mclosedate;
	}

	@Override
	public String toString() {
		return "MovieDto [mnum=" + mnum + ", title=" + title + ", runtime=" + runtime + ", mstartdate=" + mstartdate
				+ ", mclosedate=" + mclosedate + "]";
	}

}
