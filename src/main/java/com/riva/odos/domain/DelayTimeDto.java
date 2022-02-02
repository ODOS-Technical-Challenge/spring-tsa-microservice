package com.riva.odos.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayTimeDto {
	private Integer minEstimatedWaitTime;
	private Integer maxEstimatedWaitTime;
	private Integer minEstimatedPrecheckTime = 0;
	private Integer maxEstimatedPrecheckTime;
}
