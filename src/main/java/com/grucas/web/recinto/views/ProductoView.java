/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.views;

import com.grucas.recinto.domain.ProductoService;
import com.grucas.recinto.model.Producto;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;
import com.grucas.web.recinto.fragments.FragmentTop;
import com.grucas.web.recinto.windows.ProductoWindow;
import com.rubik.model.Usuario;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GrucasDev
 */
public class ProductoView extends Panel implements View {

    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    public static final String NAME = "PRODUCTO";
    VerticalLayout container = new VerticalLayout();

    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Grid<Producto> gridProducto = new Grid<>();
    List<Producto> listProducto = new ArrayList<>();

    public ProductoView() {

        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(btnAdd, btnModify);

        Label lblTitulo = new Label("PRODUCTO") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                gridProducto);

        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);

    }

    public void initComponents() {
        setSizeFull();

        gridProducto.setItems(getProductos());
        gridProducto.setHeight("350px");
        gridProducto.setWidth("700px");
        gridProducto.setSelectionMode(SelectionMode.SINGLE);

        gridProducto.addColumn(Producto::getNombre).setCaption("NOMBRE").setId("NOMBRE");
        gridProducto.addColumn(Producto::getTipo).setCaption("TIPO").setId("TIPO");
        gridProducto.addColumn(Producto::getDescripcion).setCaption("DESCRIPCION").setId("DESCRIPCION");
        gridProducto.addColumn(Producto::getFotografia).setCaption("FOTOGRAFIA").setId("FOTOGRAFIA");
        gridProducto.addColumn(Producto::getClave).setCaption("CLAVE").setId("CLAVE");

        gridProducto.addItemClickListener((evt) -> {
            if (evt.getMouseEventDetails().isDoubleClick()) {
                ProductoWindow windows = new ProductoWindow(evt.getItem());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridProducto.setItems(getProductos());
                    }
                });
                getUI().addWindow(windows);
            }
        });

        btnAdd.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ProductoWindow windows = new ProductoWindow();
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridProducto.setItems(getProductos());
                    }
                });
                getUI().addWindow(windows);
            }
        });

        btnModify.addClickListener(event -> {

            if (gridProducto.getSelectedItems().size() == 1) {
                ProductoWindow windows = new ProductoWindow(gridProducto.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridProducto.setItems(getProductos());
                    }
                });
                getUI().addWindow(windows);

            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Producto seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getProductos() {
        ProductoService service = new ProductoService();
        service.getProducto("", "", "");
        listProducto = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listProducto;

    }

}
