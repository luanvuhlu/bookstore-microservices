package com.luanvv.microservices.bookstore.louisstore.controllers

import com.luanvv.microservices.bookstore.louisstore.services.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam


@Controller
class ProductController(
    val productService: ProductService
) {

    @RequestMapping(value = ["/products"], method = [RequestMethod.GET])
    fun productList(
        @RequestParam(name = "page", defaultValue = "1") page: Int,
        @RequestParam(name = "size", defaultValue = "10") size: Int,
        model: Model,
    ): String {
        val products = productService.listProducts(page, size)
        model.addAttribute("books", products)
        return "product/product-list"
    }

    @RequestMapping(value = ["/products/{id}"], method = [RequestMethod.GET])
    fun productDetail(
        @PathVariable(name = "id") id: String,
        model: Model,
    ): String {
        val product = productService.getProduct(id)
        model.addAttribute("book", product)
        return "product/product-detail"
    }
}