package vn.inphic.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	private Long id;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@Email
	private String email;
	private String role;
	private boolean edit;
}