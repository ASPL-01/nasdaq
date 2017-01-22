package com.allstate.services;

import com.allstate.models.Stock;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {
    public Stock quote(String symbol){
        RestTemplate rest = new RestTemplate();
        return  rest.getForObject("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol=" + symbol, Stock.class);
    }
}
