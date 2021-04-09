package org.smartcookie.controller;

import org.springframework.ui.Model;

public class LayoutController {
    public String header(Model model){
        return "layout/header";
    }
}
