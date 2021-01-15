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
package com.root101.module.gestion.contabilidad.repo.repo_impl;

import com.root101.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.root101.module.gestion.contabilidad.core.domain.OperacionContableDomain;
import com.root101.module.gestion.contabilidad.core.repo_def.OperacionContableRepo;
import com.root101.module.gestion.contabilidad.repo.entities.CuentaContable;
import com.root101.module.gestion.contabilidad.repo.entities.OperacionContable;
import com.root101.module.gestion.contabilidad.repo.utils.ResourcesContabilidad;
import com.root101.utils.services.ConverterService;
import com.root101.repo.jpa.JPACleanCRUDRepo;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class OperacionContableRepoImpl extends JPACleanCRUDRepo<OperacionContableDomain, OperacionContable> implements OperacionContableRepo {

    private final String OperacionContable_findByCuenta = "SELECT o FROM OperacionContable o WHERE o.cuentaFk.idCuentaContable = :idCuenta";

    public OperacionContableRepoImpl() {
        super(ResourcesContabilidad.EMF, OperacionContableDomain.class, OperacionContable.class);
    }

    @Override
    public List<OperacionContableDomain> findAll(CuentaContableDomain cuenta) throws RuntimeException {
        if (cuenta == null) {
            return findAll();
        } else {
            return findAllByCuenta(cuenta);
        }
    }

    private List<OperacionContableDomain> findAllByCuenta(CuentaContableDomain cuenta) throws RuntimeException {//TODO: ver xq recive la cuenta y no solo el id
        EntityManager em = getEntityManager();
        try {
            List<CuentaContable> list = em.createQuery(OperacionContable_findByCuenta, CuentaContable.class)
                    .setParameter("idCuenta", cuenta.getIdCuentaContable())
                    .getResultList();
            return ConverterService.convert(list, OperacionContableDomain.class);
        } finally {
            em.close();
        }
    }
}
