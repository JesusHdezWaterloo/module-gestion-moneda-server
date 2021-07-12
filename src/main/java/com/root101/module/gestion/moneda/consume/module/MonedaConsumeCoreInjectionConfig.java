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
package com.root101.module.gestion.moneda.consume.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.root101.module.gestion.moneda.consume.repo_def.*;
import com.root101.module.gestion.moneda.consume.repo_impl.*;
import com.root101.module.gestion.moneda.consume.usecase_impl.*;
import com.root101.module.gestion.moneda.consume.usecase_def.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MonedaConsumeCoreInjectionConfig extends AbstractModule {

    @Override
    protected void configure() {
        //bind repo consume
        bind(MonedaRepoConsume.class).to(MonedaRepoConsumeImpl.class).in(Singleton.class);

        //bind UC client
        bind(MonedaUseCaseClient.class).to(MonedaUseCaseClientImpl.class).in(Singleton.class);
    }

}
