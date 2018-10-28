package com.clairvoyant.notes.resource;

import com.clairvoyant.notes.email.EmailHtmlSender;
import com.clairvoyant.notes.email.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RequestMapping("/api")
@RestController
public class EmailController {

    @Autowired
    private EmailHtmlSender emailHtmlSender;

    @RequestMapping("/note/email")
    public Object senddEmail() throws Exception {

        Context context = new Context();
        context.setVariable("title", "Gentle Remainder");
        context.setVariable("header", "Header");
        context.setVariable("content", "Content");
        context.setVariable("footer", "Footer");
        EmailStatus emailStatus = emailHtmlSender.send("shoaib16shaikh@gmail.com", "Title of email", "/TShoaib", context);

        return emailStatus;
    }

}
