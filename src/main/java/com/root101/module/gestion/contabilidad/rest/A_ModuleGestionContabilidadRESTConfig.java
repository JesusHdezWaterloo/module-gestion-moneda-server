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

import com.root101.module.gestion.contabilidad.core.module.ContabilidadCoreModule;
import com.root101.module.gestion.contabilidad.core.usecase_def.*;
import com.root101.module.gestion.contabilidad.core.usecase_impl.init.MonedaInitializer;
import com.root101.module.gestion.contabilidad.repo.module.ContabilidadRepoModule;
import com.root101.module.gestion.contabilidad.service.ResourceServiceImplementation;
import com.root101.module.gestion.contabilidad.service.ResourceServiceServerImplementation;
import org.springframework.stereotype.Component;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Component
public class A_ModuleGestionContabilidadRESTConfig {

    public static final String BASE_PACKAGE = "com.root101.module.gestion.contabilidad.rest";

    public final static CuadreUseCase cuadreUC;
    public final static CuentaBancariaUseCase cuentaBancariaUC;
    public final static CuentaContableUseCase cuentaContableUC;
    public final static InfoOperacionContableUseCase infoOpUC;
    public final static LiquidacionUseCase liquicadionUC;
    public final static MonedaUseCase monedaUC;
    public final static OperacionContableUseCase operacionContableUC;
    public final static TipoCuentaUseCase tipoCuentaUC;
    public final static TipoOperacionContableUseCase tipoOperacionContableUC;
    public final static FormaPagoUseCase formaPagoUC;
    public final static TitularUseCase titularUC;

    static {
        ResourceServiceImplementation.init();
        ResourceServiceServerImplementation.init();

        ContabilidadCoreModule.init(ContabilidadRepoModule.init());

        cuadreUC = ContabilidadCoreModule.getInstance().getImplementation(CuadreUseCase.class);
        cuentaBancariaUC = ContabilidadCoreModule.getInstance().getImplementation(CuentaBancariaUseCase.class);
        cuentaContableUC = ContabilidadCoreModule.getInstance().getImplementation(CuentaContableUseCase.class);
        infoOpUC = ContabilidadCoreModule.getInstance().getImplementation(InfoOperacionContableUseCase.class);
        liquicadionUC = ContabilidadCoreModule.getInstance().getImplementation(LiquidacionUseCase.class);
        formaPagoUC = ContabilidadCoreModule.getInstance().getImplementation(FormaPagoUseCase.class);
        monedaUC = ContabilidadCoreModule.getInstance().getImplementation(MonedaUseCase.class);
        operacionContableUC = ContabilidadCoreModule.getInstance().getImplementation(OperacionContableUseCase.class);
        tipoCuentaUC = ContabilidadCoreModule.getInstance().getImplementation(TipoCuentaUseCase.class);
        tipoOperacionContableUC = ContabilidadCoreModule.getInstance().getImplementation(TipoOperacionContableUseCase.class);
        titularUC = ContabilidadCoreModule.getInstance().getImplementation(TitularUseCase.class);

        MonedaInitializer.init(monedaUC);
    }
}
