package com.ellachihwa.lapa.controller.web;

import com.ellachihwa.lapa.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public String getNotifications(Model model){
        notificationRepository.updateAllNotifications();
        model.addAttribute("notifications",notificationRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        return "admin/notification/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteNotification(@PathVariable(value = "id") long id) {

        // call delete notification
        notificationRepository.deleteById(id);
        return "redirect:/notifications";
    }
}
