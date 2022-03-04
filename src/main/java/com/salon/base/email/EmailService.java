package com.salon.base.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Value("${email.receiver}")
    private String receiver;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    Environment environment;

    /**
     * sendEmail
     * @param messageTemplate
     */
    public void sendEmail(final EmailTemplate messageTemplate) {
        new Thread(() -> {
            try {
                LOGGER.info("Sending email {} to {} ",messageTemplate.getSubject(),messageTemplate.getReceiver());
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();

                MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);

//                mailMsg.setFrom(news InternetAddress("dinhhop21101995@gmail.com","Salon","utf-8"));
                mailMsg.setTo(messageTemplate.getReceiver());
                mailMsg.setSubject(messageTemplate.getSubject());
                mailMsg.setText(messageTemplate.getContent(), true);

                javaMailSender.send(mimeMessage);
            } catch (MessagingException e) {
                LOGGER.error("Error when sending email : ", e);
            }
        }).start();
    }

    /**
     * sendEmail
     * @param receiver
     * @param subject
     * @param content
     */
    public void sendEmail(String receiver, String subject, String content) {
        EmailTemplate emailMessage = new EmailTemplate();
        content = addMetaTag(content);

        emailMessage.setReceiver(receiver);
        emailMessage.setSubject(subject);
        emailMessage.setContent(content);

        sendEmail(emailMessage);
    }

    private String addMetaTag(String content){
        return  "<html>\n" +
                "<head>\n" +
                "  <meta charset=\"SHIFT_JIS\">\n" +
                "</head>\n" +
                "<body>" + content
                + "</body>\n" +
                "</html>";
    }

    /**
     * sendEmail
     * @param subject
     * @param content
     */
    public void sendEmail(String subject, String content) {
        EmailTemplate emailMessage = new EmailTemplate();

        emailMessage.setReceiver(receiver);
        emailMessage.setContent(content);
        emailMessage.setSubject(subject);

        sendEmail(emailMessage);
    }
}
