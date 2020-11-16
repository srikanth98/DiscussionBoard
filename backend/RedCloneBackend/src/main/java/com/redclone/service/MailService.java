package com.redclone.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.redclone.model.NotificationEmail;





@Service
public class MailService {
	@Autowired
	private  JavaMailSender mailSender;
	@Autowired
	private  MailContentBuilder mailBuilder;
	
	@Async
	public void sendMail(NotificationEmail email)
	{
		MimeMessagePreparator messagePrep = mimeMessage->{
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("noreply@srikanth.com");
			messageHelper.setTo(email.getReceipent());
			messageHelper.setText(mailBuilder.build(email.getBody()));
			messageHelper.setSubject(email.getSubject());
		
		};
		try
		{
			mailSender.send(messagePrep);
		System.out.println("Signup Mail Send");
		}
		catch(MailException ex)
		{
			ex.printStackTrace();
		}
	}

}
