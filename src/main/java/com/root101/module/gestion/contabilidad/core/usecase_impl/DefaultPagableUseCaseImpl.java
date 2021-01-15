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
package com.root101.module.gestion.contabilidad.core.usecase_impl;

import com.root101.clean.core.app.usecase.CRUDUseCase;
import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.root101.clean.core.utils.Licenced;
import com.root101.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.root101.module.gestion.contabilidad.core.domain.Pagable;
import com.root101.module.gestion.contabilidad.core.module.ContabilidadCoreModule;
import com.root101.module.gestion.contabilidad.core.usecase_def.CuadreUseCase;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class DefaultPagableUseCaseImpl<T extends Pagable> extends DefaultCRUDUseCase<T> implements CRUDUseCase<T> {

    private final CuadreUseCase cuadreUC = ContabilidadCoreModule.getInstance().getImplementation(CuadreUseCase.class);

    @Override
    @Licenced
    public T create(T newObject) throws RuntimeException {
        CuadreDomain cuadre = cuadreUC.create(newObject.getCuadreFk());
        newObject.setCuadreFk(cuadre);
        return super.create(newObject);
    }

    @Override
    @Licenced
    public T edit(T objectToUpdate) throws RuntimeException {
        destroy(objectToUpdate);
        return create(objectToUpdate);
    }

    @Override
    @Licenced
    public T destroy(T objectToDestroy) throws RuntimeException {
        //destruye el cuadre y por defecto el gasto. Destruye el cuadre viejo xq el nuevo viene con el valor cambiado
        cuadreUC.destroy(cuadreUC.findBy(objectToDestroy.getCuadreFk().getIdCuadre()));
        return objectToDestroy;
    }

}
