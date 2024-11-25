package oop.moneymanager.service;


import oop.moneymanager.dao.UserDao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailHandler {
    final static String from = "moneymanager13sp@gmail.com";
    final static String password = "ujmt bvrv rzla vdku";

    public static boolean sendMail(String to, String newPassWord, String username ){
//        mail.smtp.host: Địa chỉ máy chủ SMTP của Gmail (smtp.gmail.com).
//        mail.smtp.port: Cổng sử dụng (587 cho TLS).
//        mail.smtp.auth: Cho phép xác thực.
//        mail.smtp.starttls.enable: Kích hoạt mã hóa TLS.
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        //xác thực thông tin người gửi
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        //Chuẩn bị phiên làm việc giữa ứng dụng và máy chủ SMTP.
        Session session = Session.getInstance(props, auth);

        //Cấu hình nội dung email
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Your new password ");
            msg.setSentDate(new Date());
            String name = UserDao.getInstance().selectByUserName(username).getUserName();
            if(name == null)return false;


            String updatedPassword = "<strong><em>" + newPassWord + "</em></strong>";
            String Username = "<strong><em>" + name + "</em></strong>";

            String emailContent = "Hello " + Username + "," + "<br><br>" +
                    "We have received your request to reset your password. Below is the new password information for you to log into your account:" + "<br><br>" +
                    "New Password: " + updatedPassword + "<br><br>" +
                    "We encourage you to log in using this new password and then change it to a password that is easy for you to remember while still ensuring its security." + "<br><br>" +
                    "If you did not make this password reset request, please contact us immediately so that we can assist you." + "<br><br>" +
                    "Thank you for using our service." + "<br><br>"+
                    "Ta Quoc Bao" + "<br>" +
                    "Nhóm 13" + "<br>" +
                    "nguoivietsungtay54@gmail.com";


            //Đặt nội dung email dưới dạng HTML (text/html).
            msg.setContent(emailContent, "text/html; charset=utf-8");
            //Gửi email
            Transport.send(msg);
            System.out.println("Email sent successfully");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Email sent false");
            return false;
        }
    }

}