package com.luanvv.microservices.bookstore.louisstore.controllers

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping




@Controller
class MyErrorController: ErrorController {

    @RequestMapping("/error")
    fun handleError(): String {
        return "common/errors/error"
    }
}