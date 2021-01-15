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
package com.root101.module.gestion.moneda.core.usecase_impl;

import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.root101.module.gestion.moneda.core.domain.MonedaDomain;
import com.root101.module.gestion.moneda.core.module.MonedaCoreModule;
import com.root101.module.gestion.moneda.core.repo_def.MonedaRepo;
import com.root101.module.gestion.moneda.core.usecase_def.MonedaUseCase;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class MonedaUseCaseImpl extends DefaultCRUDUseCase<MonedaDomain> implements MonedaUseCase {

    private final MonedaRepo repo = MonedaCoreModule.getInstance().getImplementation(MonedaRepo.class);

    public MonedaUseCaseImpl() {
        super.setRepo(repo);
    }

    @Override
    public MonedaDomain findMonedaBase() throws RuntimeException {
        List<MonedaDomain> mon = findAll();
        Collections.sort(mon, (a, b) -> {
            return Integer.compare(a.getIdMoneda(), b.getIdMoneda());
        });
        if (mon.isEmpty()) {
            return null;
        }
        return mon.get(0);
    }
}
