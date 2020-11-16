package com.redclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

	@Autowired
	private final TemplateEngine templateEngine;
	
	public String build(String message)
	{
		Context context = new Context();
		context.setVariable("message", message);
		return templateEngine.process("mailTemplate",context);
	}

	public MailContentBuilder(TemplateEngine templateEngine) {
		super();
		this.templateEngine = templateEngine;
	}
	
}
