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
import com.root101.module.gestion.contabilidad.core.domain.CuadreDomain;
import com.root101.module.gestion.contabilidad.core.domain.CuentaBancariaDomain;
import com.root101.module.gestion.contabilidad.core.domain.LiquidacionDomain;
import com.root101.module.gestion.contabilidad.core.module.ContabilidadCoreModule;
import com.root101.module.gestion.contabilidad.core.repo_def.LiquidacionRepo;
import com.root101.module.gestion.contabilidad.core.usecase_def.CuadreUseCase;
import com.root101.module.gestion.contabilidad.core.usecase_def.CuentaBancariaUseCase;
import com.root101.module.gestion.contabilidad.core.usecase_def.LiquidacionUseCase;
import com.root101.module.gestion.contabilidad.utils.MonedaHandler;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class LiquidacionUseCaseImpl extends DefaultCRUDUseCase<LiquidacionDomain> implements LiquidacionUseCase {

    private final LiquidacionRepo repo = ContabilidadCoreModule.getInstance().getImplementation(LiquidacionRepo.class);

    private final CuentaBancariaUseCase cuentaUC = ContabilidadCoreModule.getInstance().getImplementation(CuentaBancariaUseCase.class);

    public LiquidacionUseCaseImpl() {
        super.setRepo(repo);
    }

    @Override
    @Licenced
    public LiquidacionDomain create(LiquidacionDomain newObject) throws RuntimeException {
        if (newObject.getCuadreFk().getLiquidada()) {
            throw new RuntimeException("No se puede liquidar dos veces el mismo cuadre");
        }
        if (!newObject.getCuadreFk().getOperacionContableCuadreFk().getCuentaFk().getTipoCuentaFk().isLiquidable()) {
            throw new RuntimeException("No se puede liquidar un cuadre de una cuenta que no es liquidable.");
        }

        //convierto a la moneda e incremento banco
        BigDecimal debito = MonedaHandler.venta(newObject.getCuadreFk().getOperacionContableCuadreFk().getDebito(), newObject.getCuadreFk().getOperacionContableCuadreFk().getCuentaFk().getMonedaFk(), newObject.getCuentaFk().getMonedaFk());
        newObject.setDebito(debito);
        BigDecimal credito = MonedaHandler.venta(newObject.getCuadreFk().getOperacionContableCuadreFk().getCredito(), newObject.getCuadreFk().getOperacionContableCuadreFk().getCuentaFk().getMonedaFk(), newObject.getCuentaFk().getMonedaFk());
        newObject.setCredito(credito);

        //si se crea una liquidacion se le quita esa cantidad a la cuenta, por eso el decrease al deb-cred
        newObject.getCuentaFk().increase(newObject);

        //rebajo la cuenta contable
        newObject.getCuadreFk().getOperacionContableCuadreFk().getCuentaFk().decrease(newObject.getCuadreFk().getOperacionContableCuadreFk());

        newObject.getCuadreFk().setLiquidada(true);

        return super.create(newObject);
    }

    @Override
    @Licenced
    public LiquidacionDomain edit(LiquidacionDomain objectToUpdate) throws RuntimeException {
        LiquidacionDomain original = findBy(objectToUpdate.getIdLiquidacion());
        objectToUpdate.setDebito(original.getDebito());
        objectToUpdate.setCredito(original.getCredito());
        objectToUpdate.setCuadreFk(original.getCuadreFk());
        return objectToUpdate;
    }

    @Override
    @Licenced
    public LiquidacionDomain destroy(LiquidacionDomain objectToDestroy) throws RuntimeException {
        objectToDestroy.getCuadreFk().setLiquidada(false);
        //si se elimina una liquidacion se le agrega esa cantidad a la cuenta, por eso el increase al deb-cred
        objectToDestroy.getCuentaFk().decrease(objectToDestroy);

        objectToDestroy.getCuadreFk().getOperacionContableCuadreFk().getCuentaFk().increase(objectToDestroy.getCuadreFk().getOperacionContableCuadreFk());

        return super.destroy(objectToDestroy);
    }

    @Override
    @Licenced
    public LiquidacionDomain destroyById(Object keyId) throws RuntimeException {
        throw new RuntimeException("No se puede eliminar directamente una liquidación.\nTiene que corregirse también el cuadre asociado");
    }

    @Override
    public LiquidacionDomain getLiquidacion(CuadreDomain cuadre) throws RuntimeException {
        return new LiquidacionDomain(
                cuadre.info().getDocumento(),
                cuadre.info().getNombre(),
                cuadre.getOperacionContableCuadreFk().getDebito(),
                cuadre.getOperacionContableCuadreFk().getCredito(),
                LocalDate.now(),
                cuadre.info().getDescripcion(),
                cuentaUC.findCuentaDefault(cuadre.getOperacionContableCuadreFk().getCuentaFk().getMonedaFk()),
                cuadre);
    }

    /**
     * Delegate de getLiquidacion(CuadreDomain cuadre)
     *
     * @param idCuadre
     * @return
     * @throws RuntimeException
     */
    @Override
    public LiquidacionDomain getLiquidacion(Integer idCuadre) throws RuntimeException {
        return getLiquidacion(ContabilidadCoreModule.getInstance().getImplementation(CuadreUseCase.class).findBy(idCuadre));
    }

    @Override
    public List<LiquidacionDomain> findAll(CuentaBancariaDomain cuenta) throws RuntimeException {
        return repo.findAll(cuenta);
    }

    /**
     * Delegate de findAll(CuentaBancariaDomain cuenta)
     *
     * @param IdCuentaBancaria
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<LiquidacionDomain> findAll(Integer IdCuentaBancaria) throws RuntimeException {
        return findAll(ContabilidadCoreModule.getInstance().getImplementation(CuentaBancariaUseCase.class).findBy(IdCuentaBancaria));
    }
}
