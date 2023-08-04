package cinema.dtos;

public class TheaterDto {
	private int thnum;
	private int totseat;
	
	public TheaterDto() {
	}

	public TheaterDto(int thnum, int totseat) {
		super();
		this.thnum = thnum;
		this.totseat = totseat;
	}

	public int getThnum() {
		return thnum;
	}

	public void setThnum(int thnum) {
		this.thnum = thnum;
	}

	public int getTotseat() {
		return totseat;
	}

	public void setTotseat(int totseat) {
		this.totseat = totseat;
	}

	@Override
	public String toString() {
		return "thnum=" + thnum + ", totseat=" + totseat;
	}
	
	
	
	
	
	
}
