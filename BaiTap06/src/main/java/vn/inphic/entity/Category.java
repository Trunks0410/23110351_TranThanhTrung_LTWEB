package vn.inphic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	@Column(name = "category_name", length = 50)
	@NotEmpty(message = "Không được để trống")
	private String name;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "status")
	private Integer status;
}
