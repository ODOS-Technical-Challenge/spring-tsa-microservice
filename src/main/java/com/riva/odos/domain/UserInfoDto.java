package com.riva.odos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
	private String name;
	private String occupation;
	private String company;
	private String gender;
	private String birthDate;
	private String salary;
	private String email;
}
