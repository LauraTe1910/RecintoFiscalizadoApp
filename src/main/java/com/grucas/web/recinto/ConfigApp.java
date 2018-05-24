/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto;

/**
 *
 * @author GrucasDev
 */
public class ConfigApp {
    
    public static Integer APLICATION_CODE = 1002;
    public static String APLICATION_TYPE = "WEBSITE";
    private static final String ENV = "production";
    public static final Boolean DEBUG = true;

    public static String getEnvironment() {
        return ENV;
    }
}
