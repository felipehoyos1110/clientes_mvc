package com.clientes.controlador;

import com.clientes.modelo.Cliente;
import com.clientes.modelo.ClienteDAO;
import com.clientes.vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {
    ClienteDAO dao = new ClienteDAO();
    Cliente p = new Cliente();
    vista vista;
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(vista v) {
        this.vista = v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnAgregar) {
            add();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe Seleccionar un cliente");
            } else {
                int id = Integer.parseInt(vista.tabla.getValueAt(fila, 0).toString());
                String nom = (String) vista.tabla.getValueAt(fila, 1);
                String correo = (String) vista.tabla.getValueAt(fila, 2);
                String tel = (String) vista.tabla.getValueAt(fila, 3);
                vista.txtId.setText("" + id);
                vista.txtNom.setText(nom);
                vista.txtCorreo.setText(correo);
                vista.txtTel.setText(tel);
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            Actualizar();
            listar(vista.tabla);
            nuevo();

        }
        if (e.getSource() == vista.btnDelete) {
            delete();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnNuevo) {
            nuevo();
        }

    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtNom.setText("");
        vista.txtTel.setText("");
        vista.txtCorreo.setText("");
        vista.txtNom.requestFocus();
    }

    public void delete() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila...!!!");
        } else {
            int id = Integer.parseInt(vista.tabla.getValueAt(fila, 0).toString());
            dao.eliminar(id);
            System.out.println("El Resultado es" + id);
            JOptionPane.showMessageDialog(vista, "Cliente Eliminado...!!!");
        }
        limpiarTabla();
    }

    public void add() {
        String nom = vista.txtNom.getText();
        String correo = vista.txtCorreo.getText();
        String tel = vista.txtTel.getText();
        p.setNombres(nom);
        p.setCorreo(correo);
        p.setTelefono(tel);
        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Cliente Agregado con Éxito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id debe seleccionar la opción Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String nom = vista.txtNom.getText();
            String correo = vista.txtCorreo.getText();
            String tel = vista.txtTel.getText();
            p.setId(id);
            p.setNombres(nom);
            p.setCorreo(correo);
            p.setTelefono(tel);
            int r = dao.actualizar(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Cliente Actualizado con Éxito.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
        limpiarTabla();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Cliente> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (Cliente cliente : lista) {
            objeto[0] = cliente.getId();
            objeto[1] = cliente.getNombres();
            objeto[2] = cliente.getCorreo();
            objeto[3] = cliente.getTelefono();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
