package vn.inphic.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoModel {
	private Long id;
	private String title;
	private String url;
	private Long categoryId;
	private Long userId;
	private boolean edit;
}
