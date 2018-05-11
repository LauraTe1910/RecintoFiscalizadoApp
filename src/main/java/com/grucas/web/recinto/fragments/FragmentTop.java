/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.fragments;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.grucas.web.recinto.views.ClienteView;
import com.grucas.web.recinto.views.EmbarcadorView;
import com.grucas.web.recinto.views.ProductoView;
import com.rubik.model.Usuario;
import com.vaadin.server.VaadinSession;

/**
 *
 * @author GrucasDev
 */
public class FragmentTop extends HorizontalLayout {

    MenuBar menubar = new MenuBar();

  
    public FragmentTop() {
        
         Usuario usuario = new Usuario();
       
    
        
        
        VaadinSession.getCurrent().getSession().setAttribute("USUARIO_ACTIVO", usuario);
        
        MenuBar.Command actionMenu = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {

                if (selectedItem.getText().equals("Cliente")) {
                    getUI().getNavigator().navigateTo(ClienteView.NAME);

                } else if (selectedItem.getText().equals("Embarcador")) {
                    getUI().getNavigator().navigateTo(EmbarcadorView.NAME);

                } else if (selectedItem.getText().equals("Producto")) {
                    getUI().getNavigator().navigateTo(ProductoView.NAME);

                }
            }
        };

        MenuBar.MenuItem cliente = menubar.addItem("Cliente", null, actionMenu);
        MenuBar.MenuItem embarcador = menubar.addItem("Embarcador", null, actionMenu);
        MenuBar.MenuItem producto = menubar.addItem("Producto", null, actionMenu);
        MenuBar.MenuItem salir = menubar.addItem("Salir", null, actionMenu);

        addComponent(menubar);

    }
}
