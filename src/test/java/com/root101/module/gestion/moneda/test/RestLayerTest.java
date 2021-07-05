/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.gestion.moneda.test;

import com.root101.json.ConverterServiceJSONImpl;
import com.root101.module.gestion.moneda.core.domain.MonedaDomain;
import com.root101.module.gestion.moneda.rest.ModuleGestionMonedaRESTConstants;
import com.root101.spring.client.RestTemplateUtils;
import com.root101.spring.server.RESTUrlConstants;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 */
public class RestLayerTest {

    public RestLayerTest() {
        ConverterServiceJSONImpl.INSTANCE();
    }

    @Test
    public void consumeRestTest() {
        System.out.println("Testing Rest Service Consumer...");

        RestTemplate rt = new RestTemplate();

        String URL_GENERAL = "http://localhost:8080";
        String URL_TEST = URL_GENERAL + ModuleGestionMonedaRESTConstants.MONEDA_GENERAL_PATH + RESTUrlConstants.FIND_ALL_PATH;

        System.out.println(RestTemplateUtils.getForList(rt, URL_TEST, MonedaDomain.class));

        System.out.println("Finish Testing Rest Service Consumer");
    }
}
