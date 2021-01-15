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
@RequestMapping(value = TIPO_CUENTA_GENERAL_PATH)
public class TipoCuentaRESTService extends RESTServiceTemplate<TipoCuentaDomain> implements TipoCuentaUseCase {

    private final TipoCuentaUseCase tipoCuentaUC = A_ModuleGestionContabilidadRESTConfig.tipoCuentaUC;

    public TipoCuentaRESTService() {
        setUseCase(tipoCuentaUC);
    }

    /**
     * Use findAllEquivalent(@PathVariable(TIPO_CUENTA) Integer idTipoCuenta)
     * para lightweight
     *
     * @param selectedItem
     * @return
     * @throws RuntimeException
     * @deprecated
     */
    @Override
    @Deprecated
    public List<TipoCuentaDomain> findAllEquivalent(TipoCuentaDomain selectedItem) throws RuntimeException {
        return tipoCuentaUC.findAllEquivalent(selectedItem);
    }

    @Override
    @GetMapping(TIPO_CUENTA_FIND_ALL_EQUIVALENT_PATH)
    public List<TipoCuentaDomain> findAllEquivalent(@PathVariable(TIPO_CUENTA) Integer idTipoCuenta) throws RuntimeException {
        return tipoCuentaUC.findAllEquivalent(idTipoCuenta);
    }

}
