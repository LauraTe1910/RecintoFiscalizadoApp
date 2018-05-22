/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.views;

import com.grucas.recinto.model.SolicitudDeServicio;
import com.grucas.web.recinto.fragments.FragmentTop;
import com.grucas.web.recinto.windows.SolicitudDeServicioWindow;
import com.rubik.model.Usuario;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class SolicitudDeServicioView extends Panel implements View {

    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    public static final String NAME = "SERVICIO_VIEW";

    VerticalLayout container = new VerticalLayout();

    Button btnAdd = new Button("", Fam3SilkIcon.ADD);
    Button btnModify = new Button("", Fam3SilkIcon.PENCIL);
    Button btnDowloadExcel = new Button("", Fam3SilkIcon.ARROW_DOWN);
    Button btnSearch = new Button("", Fam3SilkIcon.MAGNIFIER);
    Button btnPrint = new Button("", Fam3SilkIcon.PRINTER);

    TextField txtCliente = new TextField("Cliente:");
    NativeSelect<String> cboTipo = new NativeSelect("Estado Doc.");
    DateField fechaInicial = new DateField("F Inicial");
    DateField fechaFinal = new DateField("Fecha Final");

    FormLayout form = new FormLayout();
    FormLayout form1 = new FormLayout();

    Grid<SolicitudDeServicio> gridservicio = new Grid();

    List<SolicitudDeServicio> listServicio = new ArrayList<>();

    public SolicitudDeServicioView() {

        initComponents();
        
          Label lblTitulo = new Label("SOLICITUD DE SERVICIO PARA MANIOBRAS GENERALES") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                new HorizontalLayout(form, form1),
                new HorizontalLayout(btnSearch, btnPrint, btnAdd, btnModify, btnDowloadExcel),
                gridservicio
        );

        setContent(container);

        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(4), Alignment.MIDDLE_CENTER);
        

    

    }

    public void initComponents() {
        setSizeFull();
        btnSearch.setDescription("Buscar");
        btnPrint.setDescription("Imprimir PDF");
        btnAdd.setDescription("Agregar");
        btnModify.setDescription("Moficar");
        btnDowloadExcel.setDescription("Descargar Excel");

        txtCliente.setWidth("250px");
        fechaInicial.setWidth("250px");
        fechaFinal.setWidth("250px");

        cboTipo.setItems("ACTIVO", "PRE ELABORADO", "TERMINADO", "CANCELADO");
        cboTipo.setEmptySelectionAllowed(false);
        cboTipo.setWidth("250px");

        form.addComponents(txtCliente, fechaInicial);
        form1.addComponents(cboTipo, fechaFinal);
          
        gridservicio.setWidth("50%");
        gridservicio.setHeight("350px");
        gridservicio.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridservicio.addColumn(SolicitudDeServicio::getFolio).setCaption("FOLIO").setId("FOLIO");
        gridservicio.addColumn(SolicitudDeServicio::getCliente).setCaption("NOMBRE").setId("NOMBRE");
        gridservicio.addColumn(SolicitudDeServicio::getRegimen).setCaption("REGIMEN").setId("REGIMEN");
        gridservicio.addColumn(SolicitudDeServicio::getEstado_doc).setCaption("ESTADO").setId("ESTADO");
        gridservicio.addColumn(SolicitudDeServicio::getFecha_cita).setCaption("FECHA DE LA CITA").setId("FECHA DE LA CITA");

        
            btnAdd.addClickListener(e -> {
            SolicitudDeServicioWindow windows = new SolicitudDeServicioWindow();
            windows.addCloseListener(ev -> {
                windows.center();
                windows.setModal(true);
//                gridservicio.setItems(get(""));
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener(event -> {

        });

        btnSearch.addClickListener(event -> {

        });
        
    }

}
