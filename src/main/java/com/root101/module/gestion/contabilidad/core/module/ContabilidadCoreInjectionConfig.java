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
package com.root101.module.gestion.contabilidad.core.module;

import com.google.inject.Singleton;
import com.root101.module.gestion.contabilidad.core.usecase_def.*;
import com.root101.module.gestion.contabilidad.core.usecase_impl.*;
import com.root101.module.control.licence.core.injection.LicenceInjectionConfig;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ContabilidadCoreInjectionConfig extends LicenceInjectionConfig {

    @Override
    protected void configure() {
        super.configure();//configura la licencia

        bind(CuadreUseCase.class).to(CuadreUseCaseImpl.class).in(Singleton.class);
        bind(CuentaBancariaUseCase.class).to(CuentaBancariaUseCaseImpl.class).in(Singleton.class);
        bind(CuentaContableUseCase.class).to(CuentaContableUseCaseImpl.class).in(Singleton.class);
        bind(InfoOperacionContableUseCase.class).to(InfoOperacionContableUseCaseImpl.class).in(Singleton.class);
        bind(LiquidacionUseCase.class).to(LiquidacionUseCaseImpl.class).in(Singleton.class);
        bind(FormaPagoUseCase.class).to(FormaPagoUseCaseImpl.class).in(Singleton.class);
        bind(MonedaUseCase.class).to(MonedaUseCaseImpl.class).in(Singleton.class);
        bind(OperacionContableUseCase.class).to(OperacionContableUseCaseImpl.class).in(Singleton.class);
        bind(TipoCuentaUseCase.class).to(TipoCuentaUseCaseImpl.class).in(Singleton.class);
        bind(TipoOperacionContableUseCase.class).to(TipoOperacionContableUseCaseImpl.class).in(Singleton.class);
        bind(TitularUseCase.class).to(TitularUseCaseImpl.class).in(Singleton.class);
    }

}
