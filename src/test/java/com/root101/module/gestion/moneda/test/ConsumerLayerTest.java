/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.gestion.moneda.test;

import com.root101.json.ConverterServiceJSONImpl;
import com.root101.module.gestion.moneda.consume.module.MonedaConsumeCoreModule;
import com.root101.module.gestion.moneda.consume.usecase_def.MonedaUseCaseClient;
import org.junit.Test;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 */
public class ConsumerLayerTest {

    public ConsumerLayerTest() {
        ConverterServiceJSONImpl.INSTANCE();
    }

    @Test
    public void consumeRestTest() {
        System.out.println("Testing Rest Service Consumer...");

        MonedaUseCaseClient ucClient = MonedaConsumeCoreModule.getInstance().getImplementation(MonedaUseCaseClient.class);
        System.out.println(ucClient.findAll());
        
        System.out.println("Finish Testing Rest Service Consumer");
    }
}
