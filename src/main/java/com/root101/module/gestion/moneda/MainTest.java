/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.gestion.moneda;

import com.root101.json.ConverterServiceJSONImpl;
import com.root101.module.gestion.moneda.core.module.MonedaCoreModule;
import com.root101.module.gestion.moneda.core.usecase_def.MonedaUseCase;
import com.root101.module.gestion.moneda.repo.module.MonedaRepoModule;
import com.root101.module.gestion.moneda.repo.utils.ResourcesMoneda;

/**
 *
 * @author Yo
 */
public class MainTest {

    public static void main(String args[]) throws Exception {
        ConverterServiceJSONImpl.INSTANCE();
        /*MonedaCoreModule.init(MonedaRepoModule.init());

        MonedaUseCase monedaUC = MonedaCoreModule.getInstance().getImplementation(MonedaUseCase.class);

        System.out.println(monedaUC.findAll());*/

        MonedaCoreModule.init(MonedaRepoModule.init());
        MonedaUseCase moneda = MonedaCoreModule.getInstance().getImplementation(MonedaUseCase.class);
        System.out.println(moneda.findMonedaBase());
        
    }
}
