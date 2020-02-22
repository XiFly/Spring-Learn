package com.example.springbootsendmail.web;

import com.example.springbootsendmail.service.MailService;
import com.example.springbootsendmail.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 功能描述：
 * 邮件前端控制器
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/22 18:28
 **/
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件的主界面
     */
    @GetMapping("/")
    public ModelAndView index() {
        //打开发送邮件的页面
        ModelAndView mv = new ModelAndView("mail/sendMail");
        //邮件发信人
        mv.addObject("from", mailService.getMailSendFrom());
        return mv;
    }
    /**
     * 发送邮件
     */
    @PostMapping("/mail/send")
    public MailVo sendMail(MailVo mailVo, MultipartFile[] files) {
        mailVo.setMultipartFiles(files);
        //发送邮件和附件
        return mailService.sendMail(mailVo);
    }
}
