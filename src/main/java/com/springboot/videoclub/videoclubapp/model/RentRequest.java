package com.springboot.videoclub.videoclubapp.model;

import java.util.List;

public class RentRequest {

    private String customerId;
    private List<RentItem> rentItems;

    public String getCustomerId() {
        return customerId;
    }

    public void setClienteId(String clienteId) {
        this.customerId = clienteId;
    }

    public List<RentItem> getRentItems() {
        return rentItems;
    }

    public void setRentItems(List<RentItem> rentItems) {
        this.rentItems = rentItems;
    }
}
