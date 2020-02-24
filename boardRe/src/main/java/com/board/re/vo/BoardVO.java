package com.board.re.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

	private int boardnum;
	private String userid;
	private String title;
	private String content;
	private String inputdate;
	private String originalFileName;
	private String savedFileName;
	private int hit;
}
