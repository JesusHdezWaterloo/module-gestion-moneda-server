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
package com.root101.module.gestion.moneda.consume.repo_impl;

import com.root101.module.gestion.moneda.consume.repo_def.MonedaRepoConsume;
import com.root101.module.gestion.moneda.core.domain.MonedaDomain;
import static com.root101.module.gestion.moneda.rest.ModuleGestionMonedaRESTConstants.*;
//import com.root101.module.util.rest_config.services.RESTHandler;
import com.root101.spring.client.ConsumerRepoTemplate;
import org.springframework.web.client.RestOperations;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MonedaRepoConsumeImpl extends ConsumerRepoTemplate<MonedaDomain> implements MonedaRepoConsume {

    public MonedaRepoConsumeImpl() {
        super(MonedaDomain.class, /*RESTHandler.urlActualREST() +*/ MONEDA_GENERAL_PATH, (RestOperations) null);//null==supplier RestOperations
    }

    @Override
    public MonedaDomain findMonedaBase() throws RuntimeException {
        return template().getForObject(urlGeneral + MONEDA_FIND_BASE_PATH, MonedaDomain.class);
    }

    @Override
    public MonedaDomain randomMethodConsume() {
        //consume other rest service
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
