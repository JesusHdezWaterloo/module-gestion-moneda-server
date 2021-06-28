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
package com.root101.module.gestion.moneda.utils;

import com.root101.module.gestion.moneda.core.domain.MonedaDomain;
import java.math.BigDecimal;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 * @author Jessica Aidyl García Albalah (jgarciaalbalah@gmail.com)
 */
public class MonedaHandler {

    /**
     * Conversion para vender una moneda: TENGO 'valor' de la moneda 'desde'
     * para vender y quiero que me den a cambio 'hasta'.<\br>
     * EJ.: Tengo 1 CUC y quiero CUP, por lo tanto me dan 24 CUP. <\br>
     * F(x,y,z) = venta(1, CUC, CUP) = 24
     *
     * @param cantidad Cantidad de lo que tengo.
     * @param tengo Moneda que tengo.
     * @param quiero La moneda que quiero que me den a cambio.
     * @return la cantidad que me van a dar a cambio de lo que tengo.
     */
    public static BigDecimal venta(BigDecimal cantidad, MonedaDomain tengo, MonedaDomain quiero) {
        BigDecimal devol;
        if (tengo.equals(quiero)) {
            devol = cantidad;
        } else {
            devol = cantidad.multiply(tengo.getVenta()).divide(quiero.getCompra());
        }
        return devol;
    }

    /**
     * Conversión para comprar una moneda: Quiero 'cant' de la moneda 'quiero' y
     * tengo 'tengo', cuanto tengo que dar de 'tengo' para poder comprar.<\br>
     * EJ.: Quiero 1 CUC, tengo CUP, por lo tento devuelve 25, tengo que dar 25
     * CUP para comprar 1 CUC.<\br>
     * F(x,y,z) = compra(1, CUC, CUP) = 25
     *
     * @param cantidad La cantidad de lo que quiero.
     * @param quiero La moneda que quiero.
     * @param tengo Lo que tengo.
     * @return la cantidad de lo que tengo que tengo que dar para comprar lo que
     * quiero.
     */
    public static BigDecimal compra(BigDecimal cantidad, MonedaDomain quiero, MonedaDomain tengo) {
        BigDecimal devol;
        if (quiero.equals(tengo)) {
            devol = cantidad;
        } else {
            devol = cantidad.multiply(quiero.getCompra()).divide(tengo.getVenta());
        }
        return devol;
    }

    public static BigDecimal ventaFull(BigDecimal cantidad, MonedaDomain tengo, MonedaDomain quiero) {
        BigDecimal devol;
        if (tengo.equals(quiero)) {
            devol = cantidad;
        } else {
            devol = cantidad.multiply(tengo.getVenta()).divide(quiero.getVenta());
        }
        return devol;
    }

    public static BigDecimal compraFull(BigDecimal cantidad, MonedaDomain quiero, MonedaDomain tengo) {
        BigDecimal devol;
        if (quiero.equals(tengo)) {
            devol = cantidad;
        } else {
            devol = cantidad.multiply(quiero.getCompra()).divide(tengo.getCompra());
        }
        return devol;
    }
}
