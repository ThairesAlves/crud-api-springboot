package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MessageResponseDTO {

    public String message;

	public static Object builder() {
		return null;
	}

}
