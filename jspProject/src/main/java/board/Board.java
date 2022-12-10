package board;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board implements Serializable {
	private static final long serialVersionid = 1449132512754742285L;

	private int bno;
	private String btype;
	private String title;
	private String content;
	private String id;
	private Date regDate;
	private int readCount;

	public Board(int bno,String btype, String title, String content, String id, Date regDate, int readCount) {
		super();
		this.bno = bno;
		this.btype = btype;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regDate = regDate;
		this.readCount = readCount;
	}

	public Board() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}
