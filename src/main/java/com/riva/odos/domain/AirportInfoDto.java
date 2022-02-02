package com.riva.odos.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirportInfoDto implements Serializable {
	private static final long serialVersionUID = -8027368470680338967L;
	private String name;
	private String shortcode;
	private String city;
	private String state;
	private Long latitude;
	private Long longitude;
	private Integer utc;
	private Boolean dst;
	private Boolean precheck;
	@JsonProperty("checkpoints")
	private List<CheckPointDto> checkpoints;
	private DelayTimeDto delayTimes;
}
