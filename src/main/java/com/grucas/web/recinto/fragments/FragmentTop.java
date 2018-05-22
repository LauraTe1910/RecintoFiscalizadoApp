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
import com.grucas.web.recinto.views.SolicitudDeServicioView;
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

                } else if (selectedItem.getText().equals("Servicio")) {
                    getUI().getNavigator().navigateTo(SolicitudDeServicioView.NAME);
                }
            }
        };

           // Menu Catalogos
        MenuBar.MenuItem menuInventarios = menubar.addItem("Catalogos", null);
        MenuBar.MenuItem subCliente = menuInventarios.addItem("Cliente", actionMenu);
        MenuBar.MenuItem subEmbarcador = menuInventarios.addItem("Embarcador", actionMenu);
        MenuBar.MenuItem subProducto = menuInventarios.addItem("Producto", actionMenu);

        MenuBar.MenuItem servicio = menubar.addItem("Servicio", null, actionMenu);
        MenuBar.MenuItem salir = menubar.addItem("Salir", null, actionMenu);

        addComponent(menubar);

    }
}
