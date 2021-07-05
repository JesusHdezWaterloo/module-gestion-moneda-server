/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.module.gestion.moneda.test;

import com.root101.json.ConverterServiceJSONImpl;
import com.root101.module.gestion.moneda.core.repo_def.MonedaRepo;
import com.root101.module.gestion.moneda.repo.module.MonedaRepoModule;
import org.junit.Test;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 */
public class RepoLayerTest {

    public RepoLayerTest() {
        ConverterServiceJSONImpl.INSTANCE();
        
        MonedaRepoModule.init();
    }

    @Test
    public void repoTest() {
        System.out.println("Testing Repo...");

        MonedaRepo repo = MonedaRepoModule.getInstance().getImplementation(MonedaRepo.class);
        System.out.println(repo.findAll());

        System.out.println("Finish Testing Repo...");
    }

}
