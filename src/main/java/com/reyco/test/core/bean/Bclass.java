package com.reyco.test.core.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bclass {
	
	@Autowired
	private Aclass aclass;
}
