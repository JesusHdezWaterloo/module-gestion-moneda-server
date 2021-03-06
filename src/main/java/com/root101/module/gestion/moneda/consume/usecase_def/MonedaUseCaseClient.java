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
package com.root101.module.gestion.moneda.consume.usecase_def;

import com.root101.clean.core.app.usecase.CRUDUseCaseClient;
import com.root101.module.gestion.moneda.core.domain.MonedaDomain;
import com.root101.module.gestion.moneda.core.usecase_def.MonedaUseCase;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public interface MonedaUseCaseClient extends CRUDUseCaseClient<MonedaDomain>, MonedaUseCase {

    /**
     * M?todo de prueba para probar la herencia y los metodos nuevos que se
     * crean especificamente en el cliente
     *
     * @return
     */
    public MonedaDomain randomMethodClient();

    /**
     * M?todo de prueba para probar la herencia y los metodos nuevos que se
     * crean especificamente en el cliente.<br/>
     * Este es para hacer el delegate a un supuesto incremento de consume en el
     * repo.
     *
     * @return
     */
    public MonedaDomain randomMethodConsume();
}
