package View;

import Controller.EmprestimoController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate; // caso queria usar data
import java.time.format.DateTimeParseException; // caso queria usar datetime

public class CadastrarEmprestimoForm  extends JInternalFrame{
    private EmprestimoController controller;
    private JTextField txtId, txtnome, txtIdAluno,txtIdLivro, txtData;
    private JButton btnSalvar,btnBuscar;
    private Integer idEmprestimoEdit;



}
