/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.module.gestion.contabilidad.rest;

import com.root101.spring.server.RESTServiceTemplate;
import static com.root101.module.gestion.contabilidad.rest.ModuleGestionContabilidadRESTConstants.*;
import com.root101.module.gestion.contabilidad.core.domain.*;
import com.root101.module.gestion.contabilidad.core.usecase_def.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@RestController
@RequestMapping(value = LIQUIDACION_GENERAL_PATH)
public class LiquidacionRESTService extends RESTServiceTemplate<LiquidacionDomain> implements LiquidacionUseCase {

    private final LiquidacionUseCase liquicadionUC = A_ModuleGestionContabilidadRESTConfig.liquicadionUC;

    public LiquidacionRESTService() {
        setUseCase(liquicadionUC);
    }

    /**
     * Use findAll(@PathVariable(CUENTA) Integer IdCuentaBancaria) para
     * lightweight
     *
     * @param cuenta
     * @return
     * @throws RuntimeException
     * @deprecated
     */
    @Override
    @Deprecated
    public List<LiquidacionDomain> findAll(CuentaBancariaDomain cuenta) throws RuntimeException {
        return liquicadionUC.findAll(cuenta);
    }

    @Override
    @GetMapping(LIQUIDACION_FIND_ALL_PATH)
    public List<LiquidacionDomain> findAll(@PathVariable(CUENTA) Integer IdCuentaBancaria) throws RuntimeException {
        return liquicadionUC.findAll(IdCuentaBancaria);
    }

    /**
     * Use getLiquidacion(@PathVariable(CUADRE) Integer idCuadre) para
     * lightweight
     *
     * @param cuadre
     * @return
     * @throws RuntimeException
     */
    @Override
    @Deprecated
    public LiquidacionDomain getLiquidacion(CuadreDomain cuadre) throws RuntimeException {
        return liquicadionUC.getLiquidacion(cuadre);
    }

    @Override
    @GetMapping(LIQUIDACION_GET_PATH)
    public LiquidacionDomain getLiquidacion(@PathVariable(CUADRE) Integer idCuadre) throws RuntimeException {
        return liquicadionUC.getLiquidacion(idCuadre);
    }
}
