package com.example.springbootsendmail.service;

import com.example.springbootsendmail.vo.MailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 功能描述：
 * 邮件服务
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/22 18:14
 **/
@Service
public class MailService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 发送邮件
     * @param mailVo
     * @return
     */
    public MailVo sendMail(MailVo mailVo) {
        try {
            //1.检测邮件
            checkMail(mailVo);
            //2.发送邮件
            sendMimeMail(mailVo);
            //3.保存邮件
            return saveMail(mailVo);
        } catch (Exception e) {
            //打印错误信息
            logger.error("发送邮件失败:", e);
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }

    }

    /**
     * 检测邮件信息类
     */
    private void checkMail(MailVo mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 构建复杂邮件信息类
     * @param mailVo
     */
    private void sendMimeMail(MailVo mailVo) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(),true);
            //邮件发信人从配置项读取
            mailVo.setFrom(getMailSendFrom());
            // 邮件发信人
            messageHelper.setFrom(mailVo.getFrom());
            //邮件收信人
            messageHelper.setTo(mailVo.getTo().split(","));
            // 邮件主题
            messageHelper.setSubject(mailVo.getSubject());
            // 邮件内容
            messageHelper.setText(mailVo.getText());
            //抄送
            if (!StringUtils.isEmpty(mailVo.getCc())) {
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            //密送
            if (!StringUtils.isEmpty(mailVo.getBcc())) {
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            //添加邮件附件
            if (mailVo.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            //发送时间
            if (StringUtils.isEmpty(mailVo.getSentDate())) {
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            //正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            mailVo.setStatus("ok");
            logger.info("发送邮件成功：{}->{}", mailVo.getFrom(), mailVo.getTo());
        } catch (Exception e) {
            //发送失败
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存邮件
     * @param mailVo
     * @return
     */
    private MailVo saveMail(MailVo mailVo) {
        //将邮件保存到数据库..
        return mailVo;
    }

    /**
     * 获取邮件发信人
     */
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }
}
