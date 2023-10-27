package com.example.demo.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NotNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private LocalDateTime dateTime;
	private String issue;
	private boolean error;

}
