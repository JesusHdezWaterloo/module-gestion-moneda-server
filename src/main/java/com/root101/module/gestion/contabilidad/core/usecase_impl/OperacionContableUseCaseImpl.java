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

import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.root101.clean.core.utils.Licenced;
import com.root101.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.root101.module.gestion.contabilidad.core.domain.OperacionContableDomain;
import com.root101.module.gestion.contabilidad.core.module.ContabilidadCoreModule;
import com.root101.module.gestion.contabilidad.core.repo_def.OperacionContableRepo;
import com.root101.module.gestion.contabilidad.core.usecase_def.CuentaContableUseCase;
import com.root101.module.gestion.contabilidad.core.usecase_def.OperacionContableUseCase;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class OperacionContableUseCaseImpl extends DefaultCRUDUseCase<OperacionContableDomain> implements OperacionContableUseCase {

    private final OperacionContableRepo repo = ContabilidadCoreModule.getInstance().getImplementation(OperacionContableRepo.class);

    public OperacionContableUseCaseImpl() {
        super.setRepo(repo);
    }

    @Override
    public List<OperacionContableDomain> findAll(CuentaContableDomain cuenta) throws RuntimeException {
        return repo.findAll(cuenta);
    }

    /**
     * Delegate de findAll(CuentaContableDomain cuenta)
     *
     * @param idCuentaContable
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<OperacionContableDomain> findAll(Integer idCuentaContable) throws RuntimeException {
        return findAll(ContabilidadCoreModule.getInstance().getImplementation(CuentaContableUseCase.class).findBy(idCuentaContable));
    }

    @Override
    @Deprecated
    @Licenced
    public OperacionContableDomain create(OperacionContableDomain newObject) throws RuntimeException {
        throw new RuntimeException("No se puede crear una operacion directamente.\nTiene que crearse todo el ajuste");
    }

    @Override
    @Deprecated
    @Licenced
    public OperacionContableDomain edit(OperacionContableDomain objectToUpdate) throws RuntimeException {
        throw new RuntimeException("No se puede editar una operacion directamente.\nTiene que editarse todo el ajuste");
    }

    @Override
    @Deprecated
    @Licenced
    public OperacionContableDomain destroy(OperacionContableDomain objecttToDestroy) throws RuntimeException {
        throw new RuntimeException("No se puede eliminar una operacion directamente.\nTiene que eliminarse todo el ajuste");
    }

    @Override
    @Deprecated
    @Licenced
    public OperacionContableDomain destroyById(Object keyId) throws RuntimeException {
        throw new RuntimeException("No se puede eliminar una operacion directamente.\nTiene que eliminarse todo el ajuste");
    }

}
