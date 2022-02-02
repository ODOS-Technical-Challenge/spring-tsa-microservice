package com.riva.odos.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PredictedWaitTimeDto implements Serializable {
	private static final long serialVersionUID = -9157653545236005763L;
    private String longname;
    private String shortname;
    private Long predictedWaitMinutes;
}
