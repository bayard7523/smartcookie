package org.smartcookie.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @GetMapping("/panel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAdminPanel(Model model){
        return "admin/panel";
    }


}
