package com.wanghao.task;

import com.wanghao.util.Constant;
import com.wanghao.service.enums.EmailType;
import com.wanghao.task.bean.EmailInfoBean;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Observable;
import java.util.Properties;

/**
 * Created by wanghao on 8/7/17.
 */
public class EmailTask extends Observable implements Runnable {
    private static final Logger logger = Logger.getLogger(EmailTask.class);

    private static final String FROM_SMTP_HOST = "smtp.126.com";

    private static final String FROM_NAME = "fantasybooker";

    private static final String TO_NAME = "用户";

    private static final String FROM_EMAIL = Constant.EMAIL_ACCOUNT;

    private static final String FROM_PASSWORD = Constant.EMAIL_PASSWORD;

    private EmailInfoBean emailInfoBean;

    private static String activationUrl = "";

    private static String passwordUrl = "";

    public EmailTask(EmailInfoBean emailInfoBean) {
        activationUrl = Constant.ACTIVATION_BASE_URL + "?name="
                +emailInfoBean.getUserInfoModel().getNickname()
                +"&code="+emailInfoBean.getUserInfoModel().getUserCode();
        passwordUrl = Constant.PASSWORD_BASE_URL + "?name="
                +emailInfoBean.getUserInfoModel().getNickname()
                +"&code="+emailInfoBean.getUserInfoModel().getUserCode();
        this.emailInfoBean = emailInfoBean;
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param from 发件人邮箱
     * @param to 收件人邮箱
     * @param emailType 邮件类型
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String from, String to, int emailType) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(from, FROM_NAME, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to, TO_NAME, "UTF-8"));

        String subject = "";
        String content = "";
        if(emailType == EmailType.REGISTER_SUCCESS.value()){//邮件类型为注册成功激活邮件
            subject = "【fantasybooker】恭喜您注册成功，请激活您的账户";
            content = "尊敬的用户您好, 感谢您对fantasybooker支持，点击链接激活您的账户："+activationUrl;
        }
        if(emailType == EmailType.FIND_PASSWORD.value()){//邮件类型为找回密码邮件
            subject = "【fantasybooker】找回密码";
            content = "尊敬的用户您好, 感谢您对fantasybooker支持，点击链接找回您的密码："+passwordUrl;
        }
        if(emailType == EmailType.ACTIVATION_NOTIFY.value()){//用户注册后未激活，提醒激活邮件
            subject = "【fantasybooker】提醒激活";
            content = "尊敬的用户您好, 感谢您对fantasybooker支持，系统检测到您尚未激活账户，为了不影响您的正常使用，请点击链接激活您的账户："
            + activationUrl;
        }

        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(content, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    @Override
    public void run() {
        Transport transport = null;
        try {
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", FROM_SMTP_HOST);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);
            // 设置为debug模式, 可以查看详细的发送 log
            session.setDebug(true);

            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, FROM_EMAIL,
                    emailInfoBean.getUserInfoModel().getEmail(), emailInfoBean.getEmailType());

            // 4. 根据 Session 获取邮件传输对象
            transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(FROM_EMAIL, FROM_PASSWORD);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e){
            logger.info("通知观察者重启线程");
            //处理异常，自动重启该线程
            setChanged();
            //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
            notifyObservers(emailInfoBean);
        } finally {
            // 7. 关闭连接
            if(transport != null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }
    }
}
