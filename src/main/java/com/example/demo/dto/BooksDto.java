package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BooksDto {
	private int serlNo;
	private String bookName;
	private String author;
	private String edition;
	@Override
	public String toString() {
		return "serlNo=" + serlNo + ", bookName=" + bookName + ", author=" + author + ", edition=" + edition
				;
	}
	
	
}
