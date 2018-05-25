/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.windows;

import com.grucas.domain.model.Usuario;
import com.grucas.recinto.model.SolicitudDeServicio;
import com.grucas.web.recinto.fragments.FragmentTop;
import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class SolicitudDeServicioWindow extends Window {

    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    public static final String NAME = "SERVICIO";

    VerticalLayout container = new VerticalLayout();
 
    Button btnGuardar = new Button("", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("", Fam3SilkIcon.CANCEL);

    NativeSelect<String> cboAgencia = new NativeSelect("Agencia Aduanal:");
    NativeSelect<String> cboImpExp = new NativeSelect("Importador/Exportador:");
    NativeSelect<String> cboConsignatario = new NativeSelect("Consignatario:");
    NativeSelect<String> cboFactura = new NativeSelect("Factura a:");
    NativeSelect<String> cboRegimen = new NativeSelect("Regimen:");

    TextField txtServicio = new TextField("Nombre:");
    TextField txtLinea = new TextField("Linea Naviera:");
    TextField txtBuque = new TextField("Buque / Viaje No.");
    TextField txtViaje = new TextField("Viaje No.:");
    TextField txtOrigen = new TextField("Origen:");
    TextField txtDestino = new TextField("Destino:");
    TextField txtBoorking = new TextField("Boorking / BL:");
    TextField txtSolicita = new TextField("Quien Solicita:");

    DateField txtArribo = new DateField("Fecha de Arribo:");
    TwinColSelect<String> select = new TwinColSelect<>("Servicios Solicitados");
    FormLayout form = new FormLayout();
    FormLayout form1 = new FormLayout();

    TabSheet tab = new TabSheet();

    //---------------------FACTURA------------------------------------------------------------
    NativeSelect<String> cboRazonS = new NativeSelect("Razon Social:");
    TextField txtRecibeFac = new TextField("Nombre:");
    TextField txtNoReferencia = new TextField("Numero de Factura:");
    TextField txtDatoAdi = new TextField("Dato Adicional:");

    FormLayout form6 = new FormLayout();

    //---------------INFO. MERCANCIA------------------------------------------------------------
    NativeSelect<String> cboMercancia = new NativeSelect("Mercancia:");
    CheckBox cbManejo = new CheckBox("Manejo especial");
    TextArea txtDesc = new TextArea("Descripcion:");
    TextField txtIMO = new TextField("IMO");
    TextField txtUNDG = new TextField("UNDG");
    TextField txtMarcas = new TextField("Marcas:");
    TextField txtNum = new TextField("Numeros:");
    TextField txtCantidad = new TextField("Cantidad:");
    TextField txtEmbalaje = new TextField("Embalaje:");
    TextField txtTamaño = new TextField("Tamaño:");
    TextField txtContenido = new TextField("Contenido:");
    TextField txtDescMerca = new TextField("Descripcion de la mercancia:");
    TextField txtPeso = new TextField("Peso Bruto KG:");

    FormLayout form2 = new FormLayout();
    FormLayout form3 = new FormLayout();

    //------------------DATOS TRANSPORTE--------------------------------------------------
    NativeSelect<String> cboTransportista = new NativeSelect("CIA. Transportista:");
    NativeSelect<String> cboOperador = new NativeSelect("Operador:");
    NativeSelect<String> cboPlacas = new NativeSelect("Placas:");
    NativeSelect<String> cboNoCaja = new NativeSelect("No. de Caja:");
    DateTimeField dateTime = new DateTimeField("Solicitud de servicio");

    FormLayout form4 = new FormLayout();
//    FormLayout form5 = new FormLayout();
    SolicitudDeServicio deServicio = new SolicitudDeServicio();

    public SolicitudDeServicioWindow() {
        initComponents();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                new HorizontalLayout(tab)
        );

        setContent(container);

        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);

    }

    public void initComponents() {

        setSizeFull();
        //-----------------DATOS GENERALES-----------------------------
        cboAgencia.setWidth("200px");
        cboAgencia.setEmptySelectionAllowed(false);
        cboAgencia.setItems("GRUPO CASTAÑEDA");
        cboAgencia.setValue("GRUPO CASTAÑEDA");
        cboImpExp.setWidth("200px");
        cboImpExp.setEmptySelectionAllowed(false);
        cboConsignatario.setWidth("200px");
        cboConsignatario.setEmptySelectionAllowed(false);
        cboFactura.setWidth("200px");
        cboFactura.setItems("AGENCIA ADUANAL", "IMPORTADOR/EXPORTADOR", "EXPORTACION", "DESISTIMIENTO");
        cboFactura.setEmptySelectionAllowed(false);
        cboRegimen.setWidth("200px");
        cboRegimen.setItems("IMPORTACION", "EXPORTACION", "DESISTIMIENTO");
        cboRegimen.setEmptySelectionAllowed(false);

        txtServicio.setDescription("Nombre quien solicita el servicio");
        txtServicio.setWidth("200px");
        txtSolicita.setWidth("200px");
        txtSolicita.setPlaceholder("Cliente/Representante");

        select.setItems("Recepcion de mercancia", "Recepcion de contenedor", "Desconsolidacion", "Previo ocular", "previo fisico",
                "Entrga de mercancia", "Entraga de contenedor", "Consolidacion", "Reconocimiento aduanero");
        select.setWidth("500px");
        select.setHeight("345px");

        form.addComponents(cboAgencia, txtServicio, cboImpExp, cboConsignatario, cboFactura, cboRegimen, txtSolicita);
        form1.addComponents(txtLinea, txtBuque, txtArribo, txtOrigen, txtDestino, txtBoorking);

        //------------------FACTURA----------------------------------
        cboRazonS.setWidth("265px");
        cboRazonS.setEmptySelectionAllowed(false);
        txtRecibeFac.setDescription("Nombre de la persona que debe recibir la factura");
        txtRecibeFac.setWidth("265px");
        txtNoReferencia.setPlaceholder("Referencia para incluir en factura");
        txtNoReferencia.setWidth("265px");
        txtDatoAdi.setWidth("265px");

        form6.addComponents(cboRazonS, txtRecibeFac, txtNoReferencia, txtDatoAdi);

        //---------------------INFO. MERCANCIA--------------------------------------
        txtDesc.setEnabled(false);
        txtDesc.setWidth("250px");
        txtDesc.setHeight("80px");
        txtIMO.setEnabled(false);
        txtIMO.setWidth("250px");
        txtUNDG.setEnabled(false);
        txtUNDG.setWidth("250px");
        txtMarcas.setWidth("250px");
        txtNum.setWidth("250px");
        txtContenido.setWidth("250px");
        txtPeso.setWidth("250px");
        txtCantidad.setWidth("250px");
        txtEmbalaje.setWidth("250px");
        txtTamaño.setWidth("250px");
        txtDescMerca.setWidth("250px");
        cboMercancia.setItems("PALETIZADO", "CARGA SOBREDIMENCIONAL", "NO PALETIZA", "CARGA PELIGROSA");
        cboMercancia.setEmptySelectionAllowed(false);
        cboMercancia.setWidth("250px");

        form2.addComponents(cboMercancia, txtIMO, txtUNDG, txtMarcas, txtNum, txtContenido, txtPeso);
        form3.addComponents(cbManejo, txtDesc, txtCantidad, txtEmbalaje, txtTamaño, txtDescMerca);

        //-------------------DATOS TRANSPORTE---------------------------------------------
        btnGuardar.setDescription("Guardar");
        btnCancelar.setDescription("Cancelar");

        cboTransportista.setWidth("200px");
        cboTransportista.setEmptySelectionAllowed(false);
        cboOperador.setWidth("200px");
        cboOperador.setEmptySelectionAllowed(false);
        dateTime.setWidth("200px");
        dateTime.setDescription("Fecha y hora en que se suguiere asignar la solicitud de servicio");
        cboPlacas.setWidth("200px");
        cboPlacas.setEmptySelectionAllowed(false);
        cboNoCaja.setWidth("200px");
        cboNoCaja.setEmptySelectionAllowed(false);

        form4.addComponents(cboTransportista, cboOperador, cboPlacas, cboNoCaja, dateTime);

        //------------------------------------------------------------------
        cbManejo.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(ValueChangeEvent event) {
                txtDesc.setEnabled(cbManejo.getValue());

                if (!cbManejo.getValue()) {
                    txtDesc.setValue(" ");

                } else {
                    txtDesc.setValue("");
                }
            }
        });

        btnCancelar.addClickListener((ev) -> {
            close();
        });

        btnGuardar.addClickListener(ev -> {

        });

        HorizontalLayout layPrincipal = new HorizontalLayout();
        HorizontalLayout principal = new HorizontalLayout(form, form1, select);

        layPrincipal.addComponents(principal);
        principal.setComponentAlignment(principal.getComponent(0), Alignment.MIDDLE_CENTER);

        HorizontalLayout layTarja = new HorizontalLayout();
        layTarja.addComponents(form6);
        layTarja.setComponentAlignment(layTarja.getComponent(0), Alignment.MIDDLE_CENTER);

        HorizontalLayout layTarja2 = new HorizontalLayout();
        layTarja2.addComponents(form2, form3);
        layTarja2.setComponentAlignment(layTarja2.getComponent(0), Alignment.MIDDLE_CENTER);

        HorizontalLayout layTarja3 = new HorizontalLayout();
        layTarja3.addComponents(form4, new HorizontalLayout(btnGuardar, btnCancelar));
        layTarja3.setComponentAlignment(layTarja3.getComponent(0), Alignment.MIDDLE_CENTER);
        layTarja3.setComponentAlignment(layTarja3.getComponent(1), Alignment.BOTTOM_CENTER);

        tab.addTab(layPrincipal, "DATOS GENERALES");
        tab.addTab(layTarja, "FACTURA");
        tab.addTab(layTarja2, "INFORMACION DE LA MERCANCIA");
        tab.addTab(layTarja3, "DATOS DE TRANSPORTE");

    }

}
