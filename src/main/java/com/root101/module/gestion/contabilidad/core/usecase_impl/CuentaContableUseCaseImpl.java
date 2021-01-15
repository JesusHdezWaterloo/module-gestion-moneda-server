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

import com.root101.clean.core.app.services.ExceptionHandler;
import com.root101.clean.core.app.services.NotificationHandler;
import com.root101.clean.core.app.services.NotificationsGeneralType;
import com.root101.clean.core.app.usecase.DefaultCRUDUseCase;
import com.root101.clean.core.domain.services.ResourceHandler;
import com.root101.clean.core.exceptions.ValidationException;
import com.root101.clean.core.utils.Licenced;
import com.root101.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.root101.module.gestion.contabilidad.core.domain.Cuenta;
import com.root101.module.gestion.contabilidad.core.domain.CuentaContableDomain;
import com.root101.module.gestion.contabilidad.core.domain.TipoCuentaDomain;
import com.root101.module.gestion.contabilidad.core.module.ContabilidadCoreModule;
import com.root101.module.gestion.contabilidad.core.repo_def.CuentaContableRepo;
import com.root101.module.gestion.contabilidad.core.usecase_def.CuadreUseCase;
import com.root101.module.gestion.contabilidad.core.usecase_def.CuentaContableUseCase;
import com.root101.module.gestion.contabilidad.core.usecase_def.TipoCuentaUseCase;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuentaContableUseCaseImpl extends DefaultCRUDUseCase<CuentaContableDomain> implements CuentaContableUseCase {

    private final CuentaContableRepo repo = ContabilidadCoreModule.getInstance().getImplementation(CuentaContableRepo.class);

    public CuentaContableUseCaseImpl() {
        super.setRepo(repo);
        checkIntegrity();
    }

    @Override
    @Licenced
    public CuentaContableDomain create(CuentaContableDomain newObject) throws RuntimeException {
        newObject.setDebito(BigDecimal.ZERO);//siempre empiezan en cero
        newObject.setCredito(BigDecimal.ZERO);
        return super.create(newObject);
    }

    @Override
    @Licenced
    public CuentaContableDomain edit(CuentaContableDomain objectToUpdate) throws RuntimeException {
        CuentaContableDomain old = findBy(objectToUpdate.getIdCuentaContable());
        if (old.getDebito().compareTo(objectToUpdate.getDebito()) != 0) {
            throw new ValidationException("debito", "No se puede modificar el débito directamente, solo mediante operaciones.\nProbablemente alguien haya modificado la cuenta externamente.");
        }
        if (old.getCredito().compareTo(objectToUpdate.getCredito()) != 0) {
            throw new ValidationException("credito", "No se puede modificar el crédito directamente, solo mediante operaciones.\nProbablemente alguien haya modificado la cuenta externamente.");
        }
        if (!old.getMonedaFk().equals(objectToUpdate.getMonedaFk())) {
            throw new ValidationException("monedaFk", "No se puede cambiar la moneda de la cuenta.");
        }
        if (!old.getTipoCuentaFk().equivalent(objectToUpdate.getTipoCuentaFk())) {
            throw new ValidationException("tipoCuentaFk", "No se puede cambiar el tipo cuenta.");
        }
        return super.edit(objectToUpdate);
    }

    @Override
    public List<CuentaContableDomain> findAll(String searchText) throws RuntimeException {
        List<CuentaContableDomain> cuentasBancarias = findAll();
        List<CuentaContableDomain> cuentas = new ArrayList<>();
        for (CuentaContableDomain c : cuentasBancarias) {
            if (c.test(searchText)) {
                cuentas.add(c);
            }
        }
        return cuentas;
    }

    @Override
    public List<Cuenta> findAllCuentas() throws RuntimeException {
        List<CuentaContableDomain> cuentasContables = findAll();
        List<Cuenta> cuentas = new ArrayList<>(cuentasContables.size());
        for (CuentaContableDomain c : cuentasContables) {
            cuentas.add(c);
        }
        return cuentas;
    }

    @Override
    public List<CuentaContableDomain> findAllCuenta(TipoCuentaDomain tipo) throws RuntimeException {
        List<CuentaContableDomain> cuentasContables = findAll();
        List<CuentaContableDomain> cuentas = new ArrayList<>(cuentasContables.size());
        for (CuentaContableDomain c : cuentasContables) {
            if (c.getTipoCuentaFk().equals(tipo)) {
                cuentas.add(c);
            }
        }
        return cuentas;
    }

    /**
     * Delegate al findAllCuenta(TipoCuentaDomain tipo)
     *
     * @param idTipoCuenta
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<CuentaContableDomain> findAllCuenta(Integer idTipoCuenta) throws RuntimeException {
        return findAllCuenta(ContabilidadCoreModule.getInstance().getImplementation(TipoCuentaUseCase.class).findBy(idTipoCuenta));
    }

    private void checkIntegrity() {
        try {
            HashMap<Integer, CuentaContableDomain> h = new HashMap<>();
            for (CuentaContableDomain c : super.findAll()) {
                c.setDebito(BigDecimal.ZERO);
                c.setCredito(BigDecimal.ZERO);
                h.put(c.getIdCuentaContable(), c);
            }

            CuadreUseCase cuadreUC = ContabilidadCoreModule.getInstance().getImplementation(CuadreUseCase.class);
            for (CuadreDomain c : cuadreUC.findAll()) {
                try {
                    c.validate();

                    CuentaContableDomain ctaMap = h.get(c.getOperacionContableFk().getCuentaFk().getIdCuentaContable());
                    ctaMap.setDebito(ctaMap.getDebito().add(c.getOperacionContableFk().getDebito()));
                    ctaMap.setCredito(ctaMap.getCredito().add(c.getOperacionContableFk().getCredito()));

                    if (!c.getLiquidada()) {
                        CuentaContableDomain ctaMapCuadre = h.get(c.getOperacionContableCuadreFk().getCuentaFk().getIdCuentaContable());
                        ctaMapCuadre.setDebito(ctaMapCuadre.getDebito().add(c.getOperacionContableCuadreFk().getDebito()));
                        ctaMapCuadre.setCredito(ctaMapCuadre.getCredito().add(c.getOperacionContableCuadreFk().getCredito()));
                    }
                } catch (Exception e) {
                }
            }
            for (CuentaContableDomain value : h.values()) {
                repo.edit(value);
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            NotificationHandler.showConfirmDialog(NotificationsGeneralType.CONFIRM_WARNING, ResourceHandler.getString("msg.default_config.error.check_integrity"));
        }
    }

}
