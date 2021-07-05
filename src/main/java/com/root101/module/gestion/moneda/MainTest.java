/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.gestion.moneda;

import com.root101.json.ConverterServiceJSONImpl;
import com.root101.module.gestion.moneda.core.domain.MonedaDomain;
import com.root101.module.gestion.moneda.core.module.MonedaCoreModule;
import com.root101.module.gestion.moneda.core.usecase_def.MonedaUseCase;
import com.root101.module.gestion.moneda.repo.module.MonedaRepoModule;
import com.root101.module.gestion.moneda.repo.utils.ResourcesMoneda;
import com.root101.module.gestion.moneda.rest.A_ModuleGestionMonedaRESTConfig;
import com.root101.spring.client.RestTemplateUtils;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Yo
 */
public class MainTest {

    public static void main(String args[]) throws Exception {
        ConverterServiceJSONImpl.INSTANCE();
        /*

        MonedaCoreModule.init(MonedaRepoModule.init());
        MonedaUseCase moneda = MonedaCoreModule.getInstance().getImplementation(MonedaUseCase.class);
        System.out.println(moneda.findMonedaBase());
         */

        //System.out.println(A_ModuleGestionMonedaRESTConfig.monedaUC.findAll());
        consume();
    }

    private static void consume() throws Exception {
        RestTemplate rt = new RestTemplate();
        System.out.println(RestTemplateUtils.getForList(rt, "http://localhost:8080/moneda/find_all", MonedaDomain.class));
    }
}
