package com.fpt.swd.business.impl;

import com.fpt.swd.business.EmailService;
import com.fpt.swd.database.entity.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import com.fpt.swd.enums.*;


@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender mailSender;
    private final TemplateEngine emailTemplateEngine;
    private final MessageSource messageSource;
    private final String baseUrl;
    private final String emailFrom;
    private final String siteName;


    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine emailTemplateEngine, MessageSource messageSource, @Value("${application.email.base-url}") String baseUrl, @Value("${application.email.from}") String emailFrom, @Value("${application.name}") String siteName) {
        this.mailSender = mailSender;
        this.emailTemplateEngine = emailTemplateEngine;
        this.messageSource = messageSource;
        this.baseUrl = baseUrl;
        this.emailFrom = emailFrom;
        this.siteName = siteName;
    }

    @Override
    public void sendActivationEmail(User user) {
//        Map<String, Object> emailData = new HashMap<>();
//        emailData.put("activationToken", user.getActivationToken().getToken());
//        emailData.put("realName", user.getFirstName());
//        sendEmailTo(user, EmailType.ACTIVATION, emailData);
    }

    /**
     * Sends email to User according to given emailType and emailArguments
     */
    @Override
    public void sendEmailTo(User user, EmailType emailType, Map<String, Object> emailData) {
        Email email = new Email();
        email.setTo(user.getEmail());
        email.setSubject(getSubjectForEmailType(emailType, Language.EN));
        email.setContent(prepareContent(emailType, Language.EN, emailData));
        sendHtmlEmail(email);
    }

    @Override
    public void sendWelcomeEmail(User user) {
        Map<String, Object> emailData = new HashMap<>();
        emailData.put("realName", user.getFirstName());
        sendEmailTo(user, EmailType.WELCOME, emailData);
    }

    @Override
    public void  sendRecoveryEmail(User user) {
        Map<String, Object> emailData = new HashMap<>();
        emailData.put("recoveryToken", user.getRecoveryToken().getToken());
        emailData.put("realName", user.getFirstName());
        sendEmailTo(user, EmailType.RECOVER, emailData);
    }

    /**
     * Gets localized subject according to email type
     */
    private String getSubjectForEmailType(EmailType emailType, Language language, Object... args) {
        return messageSource.getMessage(emailType.getMessageBase() + ".subject", args, Locale.forLanguageTag(language.toString()));
    }

    /**
     * Prepares html content of email according to EmailType, Language, and given email arguments.
     */
    private String prepareContent(EmailType emailType, Language language, Map<String, Object> args) {
        Context context = new Context();
        //put globals into context
        context.setVariable("serverUrl", baseUrl);
        context.setVariable("siteName", siteName);
        args.forEach(context::setVariable);
        String templatePath = "email/" + language.toString().toLowerCase() + "/" + emailType.getTemplate();
        return emailTemplateEngine.process(templatePath, context);
    }

    /**
     * Sends and email with Content-Type: HTML
     */
    private void sendHtmlEmail(Email email) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(emailFrom);
            messageHelper.setTo(email.getTo());
            messageHelper.setSubject(email.getSubject() + " - " + siteName);
            messageHelper.setText(email.getContent(), true);
        };
        try {
            mailSender.send(mimeMessagePreparator);
        } catch (MailException e) {
            logger.error("E-mail could not be sent. Exception message: {]", e.getMessage());
            logger.debug("{}", e);
        }
    }

    @Data
    private static class Email {
        private String to;
        private String subject;
        private String content;
    }

    public enum EmailType {
        ACTIVATION("email-activation"), WELCOME("email-welcome"), RECOVER("email-recover");

        private final String template;
        private final String messageBase;

        EmailType(String template) {
            this.template = template;
            this.messageBase = template.replaceAll("-", ".");
        }

        public String getTemplate() {
            return template;
        }

        public String getMessageBase() {
            return messageBase;
        }
    }
}
