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
import com.root101.module.gestion.contabilidad.core.domain.Cuenta;
import com.root101.module.gestion.contabilidad.core.domain.CuentaBancariaDomain;
import com.root101.module.gestion.contabilidad.core.domain.LiquidacionDomain;
import com.root101.module.gestion.contabilidad.core.domain.MonedaDomain;
import com.root101.module.gestion.contabilidad.core.module.ContabilidadCoreModule;
import com.root101.module.gestion.contabilidad.core.repo_def.CuentaBancariaRepo;
import com.root101.module.gestion.contabilidad.core.usecase_def.CuentaBancariaUseCase;
import com.root101.module.gestion.contabilidad.core.usecase_def.LiquidacionUseCase;
import com.root101.module.gestion.contabilidad.core.usecase_def.MonedaUseCase;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class CuentaBancariaUseCaseImpl extends DefaultCRUDUseCase<CuentaBancariaDomain> implements CuentaBancariaUseCase {

    private final CuentaBancariaRepo repo = ContabilidadCoreModule.getInstance().getImplementation(CuentaBancariaRepo.class);

    public CuentaBancariaUseCaseImpl() {
        super.setRepo(repo);
        checkIntegrity();
    }

    @Override
    @Licenced
    public CuentaBancariaDomain create(CuentaBancariaDomain cuenta) throws RuntimeException {
        cuenta.setDebito(BigDecimal.ZERO);//siempre empiezan en cero
        cuenta.setCredito(BigDecimal.ZERO);
        return super.create(cuenta);
    }

    @Override
    @Licenced
    public CuentaBancariaDomain edit(CuentaBancariaDomain objectToUpdate) throws RuntimeException {
        CuentaBancariaDomain old = findBy(objectToUpdate.getIdCuentaBancaria());
        if (old.getDebito().compareTo(objectToUpdate.getDebito()) != 0) {
            throw new ValidationException("debito", "No se puede modificar el débito directamente, solo mediante operaciones.\nProbablemente alguien haya modificado la cuenta externamente.");
        }
        if (old.getCredito().compareTo(objectToUpdate.getCredito()) != 0) {
            throw new ValidationException("credito", "No se puede modificar el crédito directamente, solo mediante operaciones.\nProbablemente alguien haya modificado la cuenta externamente.");
        }
        if (!old.getMonedaFk().equals(objectToUpdate.getMonedaFk())) {
            throw new ValidationException("monedaFk", "No se puede cambiar la moneda de la cuenta.");
        }
        return super.edit(objectToUpdate);
    }

    @Override
    public List<CuentaBancariaDomain> findAll(String searchText) throws RuntimeException {
        List<CuentaBancariaDomain> cuentasBancarias = findAll();
        List<CuentaBancariaDomain> cuentas = new ArrayList<>();
        for (CuentaBancariaDomain c : cuentasBancarias) {
            if (c.test(searchText)) {
                cuentas.add(c);
            }
        }
        return cuentas;
    }

    @Override
    public CuentaBancariaDomain findCuentaDefault(MonedaDomain moneda) throws RuntimeException {
        List<CuentaBancariaDomain> all = findAll();
        if (all.isEmpty()) {
            throw new RuntimeException("No hay ninguna cuenta bancaria creada.");
        }
        for (CuentaBancariaDomain c : all) {
            if (c.getMonedaFk().equals(moneda)) {
                return c;
            }
        }
        return all.get(0);
    }

    /**
     * Delegate al findCuentaDefault(MonedaDomain moneda)
     *
     * @param idMoneda
     * @return
     * @throws RuntimeException
     */
    @Override
    public CuentaBancariaDomain findCuentaDefault(Integer idMoneda) throws RuntimeException {
        return findCuentaDefault(ContabilidadCoreModule.getInstance().getImplementation(MonedaUseCase.class).findBy(idMoneda));
    }

    @Override
    public List<Cuenta> findAllCuentas() throws RuntimeException {
        List<CuentaBancariaDomain> cuentasBancarias = findAll();
        List<Cuenta> cuentas = new ArrayList<>(cuentasBancarias.size());
        for (CuentaBancariaDomain c : cuentasBancarias) {
            cuentas.add(c);
        }
        return cuentas;
    }

    private void checkIntegrity() {
        try {
            LiquidacionUseCase liqUC = ContabilidadCoreModule.getInstance().getImplementation(LiquidacionUseCase.class);
            for (CuentaBancariaDomain c : super.findAll()) {
                BigDecimal deb = BigDecimal.ZERO;
                BigDecimal cred = BigDecimal.ZERO;
                for (LiquidacionDomain liquidacionDomain : liqUC.findAll(c)) {
                    deb = deb.add(liquidacionDomain.getDebito());
                    cred = cred.add(liquidacionDomain.getCredito());
                }
                if (c.getDebito() != deb || c.getCredito() != cred) {
                    c.setDebito(deb);
                    c.setCredito(cred);
                    repo.edit(c);//directo pal repo pa que no pase las validaciones del edit
                }
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            NotificationHandler.showConfirmDialog(NotificationsGeneralType.CONFIRM_WARNING, ResourceHandler.getString("msg.default_config.error.check_integrity"));
        }
    }

}
