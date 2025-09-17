package vn.inphic.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
	private Long id;
	@NotEmpty(message = "Name is not allowed to be empty")
	private String name;
	private String description;
	private Integer status;
	private boolean edit; 
}
