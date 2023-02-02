package com.study.panda.configuration;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component // 등록해라!
public class EmailProperties {
	
	private String host, username, password;
	private int port;
}
