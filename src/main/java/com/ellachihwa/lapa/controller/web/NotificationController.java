package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public String getNotifications(Model model){


        model.addAttribute("notifications",notificationRepository.findAll());
        return "admin/notification/list";
    }
}
