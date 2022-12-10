
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member implements Serializable {
	private static final long serialVersionid = 1449132512754742285L;
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	// createDateTime 필드 추가해야하는건가
	private LocalDateTime loginDateTime;

	public Member(String id, String pwd, String name, String phone, String email, LocalDateTime loginDateTime) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.loginDateTime = loginDateTime;
	}

	public Member() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

//	public boolean login(String id, String pwd) {
//		return id.equals(id) && pwd.equals(pwd);
//	}

}
