package com.furkanreyhan.addressservice.controller;

import com.furkanreyhan.addressservice.request.CreateAddressRequest;
import com.furkanreyhan.addressservice.response.AddressResponse;
import com.furkanreyhan.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    /* post ederken sadece city ve street vermek için CreateAddressRequest sınıfı oluşturduk,
     * bu post metodu bu CreateAddressRequesti alıyor ve bir AddressResponse döndürüyor. */

    @PostMapping("/create")
    public AddressResponse createAddress (@RequestBody CreateAddressRequest createAddressRequest){
        return addressService.createAddress(createAddressRequest);
    }

    @GetMapping("/getById/{id}")
    public AddressResponse getById(@PathVariable long id){
        return addressService.getById(id);
    }
}
