/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.grucas.web.recinto.fragments.FragmentTop;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author GrucasDev
 */
@SuppressWarnings("serial")
public class MainView extends VerticalLayout implements View {
    
    public static final String NAME = "INICIO";

    public MainView() {
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        setMargin(false);
        addComponent(new FragmentTop());
    }
    
}