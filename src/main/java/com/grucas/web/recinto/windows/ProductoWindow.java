/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.windows;

import com.grucas.domain.model.Usuario;
import com.grucas.recinto.domain.ProductoService;
import com.grucas.recinto.model.Producto;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class ProductoWindow extends Window {

    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    VerticalLayout cont = new VerticalLayout();
    String title_window = "PRODUCTO_WINDOW";
    public Producto producto = new Producto();
    public Boolean isEdit = false;

    TextField txtNombre = new TextField("Nombre:");
    NativeSelect<String> cboTipo = new NativeSelect("Tipo:");
    TextField txtDescr = new TextField("Descripcion:");
    CheckBox checkbox1 = new CheckBox("Fotografias");
    TextField txtClave = new TextField("Clave:");
    Label title;
    FormLayout form = new FormLayout();

    public ProductoWindow() {
        setCaption("ALTA DE PRODUCTO");
        initComponents();
    }

    public ProductoWindow(Producto produc) {
        setCaption("MODIFICACION DE PRODUCTO");
        isEdit = true;
        this.producto = produc;
        initComponents();

    }

    public void initComponents() {
        setContent(cont);
        setHeight("410px");
        setWidth("450px");

        Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
        Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

        txtNombre.setWidth("300");
        txtDescr.setWidth("300");
        txtClave.setWidth("300");
        checkbox1.setWidth("300");

        cboTipo.setWidth("300");
        cboTipo.setItems("prueba1", "prueba2");
        cboTipo.setEmptySelectionAllowed(false);

        form.addComponents(txtNombre, cboTipo, txtDescr, checkbox1, txtClave);

        Binder<Producto> binder = new Binder<>();

        binder.forField(txtNombre).withValidator(nombre -> nombre.length() >= 1, "El campo no puede estar vacio").bind(Producto::getNombre, Producto::setNombre);
        binder.forField(txtDescr).bind(Producto::getDescripcion, Producto::setDescripcion);
        binder.forField(txtClave).withValidator(clave -> clave.length() >= 1, "El campo no puede estar vacio").bind(Producto::getClave, Producto::setClave);

        if (isEdit) {
            binder.readBean(producto);

            cboTipo.setValue(producto.getTipo());
        }

        cont.addComponents(new HorizontalLayout(form),
                new HorizontalLayout(btnGuardar, btnCancelar));

        cont.setComponentAlignment(cont.getComponent(0), Alignment.TOP_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.TOP_CENTER);

        btnCancelar.addClickListener((ev) -> {
            close();
        });

        btnGuardar.addClickListener((e) -> {
            try {
                binder.writeBean(producto);

                ProductoService service = new ProductoService();

//                cliente.setNombre(operador.getNombre().toUpperCase());
                producto.setTipo(cboTipo.getValue());
                producto.setUnidad_id(0);
                producto.setUnidad("Tampico");
                producto.setUsuario_id(0);
                producto.setUsuario("laura");
                producto.setEmpresa("Grucas");
                producto.setEmpresa_id(0);
                producto.setActivo(true);

                if (isEdit) {
                    service.ProductoUpdate(producto);
                } else {
                    service.ProductoInsert(producto);
                }

                if (service.getOk()) {

                    MessageBox.createInfo()
                            .withCaption("Atencion")
                            .withMessage(service.getNotification())
                            .open();

                    close();
                } else {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage(service.getNotification())
                            .withRetryButton()
                            .open();
                }

            } catch (Exception ex) {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
                        .withRetryButton()
                        .open();
            }
        });

    }

}
