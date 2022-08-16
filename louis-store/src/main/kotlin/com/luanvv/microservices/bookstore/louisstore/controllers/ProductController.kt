package com.luanvv.microservices.bookstore.louisstore.controllers

import com.luanvv.microservices.bookstore.louisstore.services.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


@Controller
class ProductController(
    val productService: ProductService
) {

    @RequestMapping(value = ["/products"], method = [RequestMethod.GET])
    fun personList(
        @RequestParam("page") page: Int?,
        @RequestParam("size") size: Int?,
        model: Model
    ): String? {
        val products = productService.listProducts(page, size)
        model.addAttribute("books", products)
        return "product/product-list"
    }
}