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

import com.root101.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.root101.module.gestion.contabilidad.core.repo_def.CuadreRepo;
import com.root101.module.gestion.contabilidad.repo.entities.Cuadre;
import com.root101.module.gestion.contabilidad.repo.entities.CuentaContable;
import com.root101.module.gestion.contabilidad.repo.entities.InfoOperacionContable;
import com.root101.module.gestion.contabilidad.repo.utils.ResourcesContabilidad;
import com.root101.utils.services.ConverterService;
import com.root101.repo.jpa.JPACleanCRUDRepo;
import com.root101.repo.jpa.NonExistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuadreRepoImpl extends JPACleanCRUDRepo<CuadreDomain, Cuadre> implements CuadreRepo {

    public CuadreRepoImpl() {
        super(ResourcesContabilidad.EMF, CuadreDomain.class, Cuadre.class);
    }

    @Override
    public CuadreDomain create(CuadreDomain domain) throws RuntimeException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Cuadre cuadreEntity = ConverterService.convert(domain, Cuadre.class);

            InfoOperacionContable info = cuadreEntity.getOperacionContableFk().getInfoOperacionContableFk();
            em.persist(info);
            em.flush();

            cuadreEntity.getOperacionContableFk().setInfoOperacionContableFk(info);
            cuadreEntity.getOperacionContableCuadreFk().setInfoOperacionContableFk(info);

            em.persist(cuadreEntity.getOperacionContableFk());
            em.persist(cuadreEntity.getOperacionContableCuadreFk());
            em.flush();

            em.persist(cuadreEntity);

            em.merge(cuadreEntity.getOperacionContableFk().getCuentaFk());
            em.merge(cuadreEntity.getOperacionContableCuadreFk().getCuentaFk());

            em.getTransaction().commit();
            domain = ConverterService.convert(cuadreEntity, CuadreDomain.class);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return domain;
    }

    @Override
    public CuadreDomain edit(CuadreDomain domain) throws RuntimeException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Cuadre cuadreEntity = ConverterService.convert(domain, Cuadre.class);

            InfoOperacionContable info = cuadreEntity.getOperacionContableFk().getInfoOperacionContableFk();
            em.merge(info);

            em.merge(cuadreEntity.getOperacionContableFk());
            em.merge(cuadreEntity.getOperacionContableCuadreFk());
            em.flush();

            em.persist(cuadreEntity);

            em.merge(cuadreEntity.getOperacionContableFk().getCuentaFk());
            em.merge(cuadreEntity.getOperacionContableCuadreFk().getCuentaFk());

            em.getTransaction().commit();
            domain = ConverterService.convert(cuadreEntity, CuadreDomain.class);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return domain;
    }

    @Override
    public CuadreDomain destroy(CuadreDomain domain) throws RuntimeException {
        EntityManager em = null;
        Cuadre persistedObject;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            persistedObject = em.find(Cuadre.class, domain.getIdCuadre());
            if (persistedObject == null) {
                throw new NonExistingEntityException(domain + " no longer exists.");
            }

            em.merge(ConverterService.convert(domain.getOperacionContableFk().getCuentaFk(), CuentaContable.class));
            em.merge(ConverterService.convert(domain.getOperacionContableCuadreFk().getCuentaFk(), CuentaContable.class));

            em.remove(persistedObject.getOperacionContableFk().getInfoOperacionContableFk());

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return domain;
    }

    @Override
    public CuadreDomain destroyById(Object keyId) throws RuntimeException {
        throw new RuntimeException("Eliminar directo el objeto completo");
    }

    @Override
    public List<CuadreDomain> findAllPending() throws RuntimeException {
        return findByLiquidada(false);
    }

    @Override
    public List<CuadreDomain> findAllLiquidadas() throws RuntimeException {
        return findByLiquidada(true);
    }

    @Override
    public List<CuadreDomain> findByLiquidada(boolean liquidada) throws RuntimeException {
        EntityManager em = getEntityManager();
        try {
            List<Cuadre> list = em.createNamedQuery("Cuadre.findByLiquidada", Cuadre.class).setParameter("liquidada", liquidada).getResultList();
            return ConverterService.convert(list, CuadreDomain.class);
        } finally {
            em.close();
        }
    }

}
