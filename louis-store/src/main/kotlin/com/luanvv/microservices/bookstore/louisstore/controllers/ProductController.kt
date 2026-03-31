package com.luanvv.microservices.bookstore.louisstore.controllers

import com.luanvv.bookstore.product.client.model.Product
import com.luanvv.bookstore.product.client.model.ProductsList
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
    fun productList(
        @RequestParam(name = "page", defaultValue = "1") page: Int?,
        @RequestParam(name = "size", defaultValue = "10") size: Int?,
        model: Model
    ): String {
        val products = productService.listProducts(page, size)
        model.addAttribute("books", products)
        return "product/product-list"
    }
}