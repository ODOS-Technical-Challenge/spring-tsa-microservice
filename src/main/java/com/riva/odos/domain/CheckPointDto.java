package com.riva.odos.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckPointDto implements Serializable {
	private static final long serialVersionUID = -9157653549826005763L;
	private Integer id;
    private String longname;
    private String shortname;
}

