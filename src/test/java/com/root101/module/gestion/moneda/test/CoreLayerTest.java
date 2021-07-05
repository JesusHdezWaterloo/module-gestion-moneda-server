/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.gestion.moneda.test;

import com.root101.json.ConverterServiceJSONImpl;
import com.root101.module.gestion.moneda.core.module.MonedaCoreModule;
import com.root101.module.gestion.moneda.core.usecase_def.MonedaUseCase;
import com.root101.module.gestion.moneda.repo.module.MonedaRepoModule;
import org.junit.Test;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 */
public class CoreLayerTest {

    public CoreLayerTest() {
        ConverterServiceJSONImpl.INSTANCE();
        
        MonedaCoreModule.init(MonedaRepoModule.init());
    }

    @Test
    public void coreTest() {
        System.out.println("Testing Core...");

        MonedaUseCase uc = MonedaCoreModule.getInstance().getImplementation(MonedaUseCase.class);
        System.out.println(uc.findAll());

        System.out.println("Finish Testing Core...");
    }

}
