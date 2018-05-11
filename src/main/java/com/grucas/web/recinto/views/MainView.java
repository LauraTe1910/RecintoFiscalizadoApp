/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.grucas.web.recinto.fragments.FragmentTop;

/**
 *
 * @author GrucasDev
 */
public class MainView extends HorizontalLayout implements View {
    
    public static final String NAME = "INICIO";
    CssLayout ccsLayout = new CssLayout();
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
       addComponent(new FragmentTop());
       
       
        
        
    }
    
    
    
}
