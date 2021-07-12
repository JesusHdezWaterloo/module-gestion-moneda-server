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
package com.root101.module.gestion.moneda.consume.usecase_impl;

import com.root101.clean.core.app.usecase.DefaultCRUDUseCaseClient;
import com.root101.module.gestion.moneda.consume.module.MonedaConsumeCoreModule;
import com.root101.module.gestion.moneda.consume.repo_def.MonedaRepoConsume;
import com.root101.module.gestion.moneda.consume.repo_impl.MonedaRepoConsumeImpl;
import com.root101.module.gestion.moneda.core.domain.MonedaDomain;
import com.root101.module.gestion.moneda.consume.usecase_def.MonedaUseCaseClient;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MonedaUseCaseClientImpl extends DefaultCRUDUseCaseClient<MonedaDomain> implements MonedaUseCaseClient {

    private final MonedaRepoConsume repoConsume = MonedaConsumeCoreModule.getInstance().getImplementation(MonedaRepoConsumeImpl.class);

    public MonedaUseCaseClientImpl() {
        setRepo(repoConsume);
    }

    @Override
    public MonedaDomain randomMethodClient() {
        return null;//implementacion propia del cliente
    }

    @Override
    public MonedaDomain randomMethodConsume() {//delegate a un nuevo metodo del consume
        return repoConsume.randomMethodConsume();
    }

    @Override
    public MonedaDomain findMonedaBase() throws RuntimeException {//delegate del rest del server, que no es claro el crud que se automatiza
        return repoConsume.findMonedaBase();
    }

}
