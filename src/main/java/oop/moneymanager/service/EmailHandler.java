package oop.moneymanager.service;


import oop.moneymanager.dao.UserDao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.mail.Session;

public class EmailHandler {
    final static String from = "nguoivietsungtay54@gmail.com";
    final static String password = "qgmb qspa rphy lygg";


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
            msg.setSubject("Mật khẩu mới của bạn ");
            msg.setSentDate(new Date());
            String name = UserDao.getInstance().selectByUserName(username).getUserName();
            if(name == null)return false;


            String updatedPassword = "<strong><em>" + newPassWord + "</em></strong>";
            String Username = "<strong><em>" + name + "</em></strong>";

            String emailContent = "Chào " + Username + "," + "<br><br>" +
                    "Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn. Dưới đây là thông tin mật khẩu mới để bạn đăng nhập vào tài khoản của mình:" + "<br><br>" +
                    "Mật khẩu mới: " + updatedPassword + "<br><br>" +
                    "Chúng tôi khuyến khích bạn đăng nhập bằng mật khẩu mới này và sau đó đổi lại mật khẩu thành một mật khẩu mà bạn dễ nhớ nhưng cũng đảm bảo an toàn." + "<br><br>" +
                    "Nếu bạn không thực hiện yêu cầu đặt lại mật khẩu này, vui lòng liên hệ với chúng tôi ngay lập tức để chúng tôi có thể hỗ trợ bạn." + "<br><br>" +
                    "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi." + "<br><br>";

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