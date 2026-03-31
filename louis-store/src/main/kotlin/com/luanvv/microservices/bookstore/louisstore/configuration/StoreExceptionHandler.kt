package com.luanvv.microservices.bookstore.louisstore.configuration

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver

@ControllerAdvice
class StoreExceptionHandler: DefaultHandlerExceptionResolver() {


}