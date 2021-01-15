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
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@RestController
@RequestMapping(value = MONEDA_GENERAL_PATH)
public class MonedaRESTService extends RESTServiceTemplate<MonedaDomain> implements MonedaUseCase {

    private final MonedaUseCase monedaUC = A_ModuleGestionContabilidadRESTConfig.monedaUC;

    public MonedaRESTService() {
        setUseCase(monedaUC);
    }

    @Override
    @GetMapping(MONEDA_FIND_BASE_PATH)
    public MonedaDomain findMonedaBase() throws RuntimeException {
        return monedaUC.findMonedaBase();
    }
}
