package com.reyco.test.core.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Aclass {
	
	@Autowired
	private Bclass bclass;
	
	
}
