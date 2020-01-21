package com.magikarpll.java.sms.module.sms.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@RestController
public class MailController {

//    private static String account = "13006189736@163.com";
//    private static String smtpAddress = "smtp.163.com";
//    private static String password = "leizhen1996";
    private static String smtpAddress = "smtp.gmail.com";
    private static String account = "magikarpllxd@gmail.com";
    private static String smtpPort = "587";

    private static String password = "ftuvgvcqyaqgigav";

    private static String receiver = "3422785094@qq.com";

    private static boolean is_need_ssl = false;
    private static boolean is_need_tls = true;

    private static String ssl_port = "465";

    public static void main(String[] args) throws Exception {
        send163Mail();
    }

    @RequestMapping("/send163Mail")
    public static void send163Mail() throws Exception {
        Properties properties = new Properties();
        if(!is_need_ssl){
            //smtp参数配置
            properties.setProperty("mail.transport.protocol", "smtp");
            properties.setProperty("mail.smtp.host", smtpAddress);
            properties.setProperty("mail.smtp.auth","true");
            if(!StringUtils.isEmpty(smtpPort)){
                properties.setProperty("mail.smtp.port",smtpPort);
            }
            properties.put("mail.smtp.starttls.enable",is_need_tls);
        }else{
            properties.setProperty("mail.smtp.port",smtpAddress);
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.port", ssl_port);
        }

        Session session = Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage message = createMimeMessage(session, account, receiver);
        Transport transport = session.getTransport();
        transport.connect(account, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "昵称", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("主题", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("邮件正文", "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}
