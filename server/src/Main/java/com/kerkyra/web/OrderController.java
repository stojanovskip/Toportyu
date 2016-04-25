package com.kerkyra.web;

import com.kerkyra.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Andras.Timar on 4/25/2016.
 */
@RestController
public class OrderController {

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getAllDocuments() {
        return null;
    }
}
