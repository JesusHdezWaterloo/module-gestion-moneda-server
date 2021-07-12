/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.gestion.moneda.rest.rest_def;

import com.root101.clean.core.app.rest.CRUDRestService;
import com.root101.module.gestion.moneda.core.domain.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 */
public interface MonedaRESTService extends CRUDRestService<MonedaDomain> {

    public MonedaDomain findMonedaBase() throws RuntimeException;
}
