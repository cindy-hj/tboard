package com.example.vo;

import lombok.Data;

@Data
public class Reply {
	private int idx;
	private int boardIdx;
	private int replyIdx;
	private String contents;
}
