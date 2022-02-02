package com.riva.odos.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirportWaitTimeDto implements Serializable {
	private static final long serialVersionUID = -9157653549826005763L;
    private String longname;
    private String shortname;
    private List<Long> currentWaitMinutes;

}
