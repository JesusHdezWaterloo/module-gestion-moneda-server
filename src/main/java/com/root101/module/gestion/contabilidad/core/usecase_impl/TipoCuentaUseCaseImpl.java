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
import com.root101.module.gestion.contabilidad.core.domain.TipoCuentaDomain;
import com.root101.module.gestion.contabilidad.core.module.ContabilidadCoreModule;
import com.root101.module.gestion.contabilidad.core.repo_def.TipoCuentaRepo;
import com.root101.module.gestion.contabilidad.core.usecase_def.TipoCuentaUseCase;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class TipoCuentaUseCaseImpl extends DefaultCRUDUseCase<TipoCuentaDomain> implements TipoCuentaUseCase {

    private final TipoCuentaRepo repo = ContabilidadCoreModule.getInstance().getImplementation(TipoCuentaRepo.class);

    public TipoCuentaUseCaseImpl() {
        super.setRepo(repo);
    }

    @Override
    public List<TipoCuentaDomain> findAllEquivalent(TipoCuentaDomain tipo) throws RuntimeException {
        return findAll().stream()
                .filter((TipoCuentaDomain t) -> {
                    return t.equivalent(tipo);
                }).collect(Collectors.toList());
    }

    /**
     * Delegate de findAllEquivalent(TipoCuentaDomain tipo)
     *
     * @param idTipoCuenta
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<TipoCuentaDomain> findAllEquivalent(Integer idTipoCuenta) throws RuntimeException {
        return findAllEquivalent(ContabilidadCoreModule.getInstance().getImplementation(TipoCuentaUseCase.class).findBy(idTipoCuenta));
    }
}
