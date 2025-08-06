package View;

import Controller.EmprestimoController;
import Model.Aluno;
import Model.Emprestimo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate; // caso queria usar data
import java.time.format.DateTimeParseException; // caso queria usar datetime

public class CadastrarEmprestimoForm  extends JInternalFrame{
    private EmprestimoController controller;
    private JTextField txtId, txtIdAluno,txtIdLivro, txtData;
    private JButton btnSalvar,btnBuscar;
    private Integer idEmprestimoEdit;


    public CadastrarEmprestimoForm(EmprestimoController controller,int idEmprestimoEdit){
        super("Cadastro Emprestimos",true,true,true,true);
        this.controller=controller;
        this.idEmprestimoEdit=idEmprestimoEdit;

        setSize(500, 350); // Tamanho da janela interna
        setLayout(new GridBagLayout()); // Layout para organizar os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 40); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche o espaço horizontalmente

        int row = 0; // Contador de linhas para o layout

        // Campo ID
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = row;
        txtId = new JTextField(10);
        txtId.setEditable(false); // ID não pode ser editado diretamente, apenas buscado
        add(txtId, gbc);
        gbc.gridx = 2; gbc.gridy = row;
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscaEmprest()); // Adiciona ação ao botão Buscar
        add(btnBuscar, gbc);
        row++;
        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Id Aluno:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Ocupa 2 colunas
        txtIdAluno = new JTextField(20);
        add(txtIdAluno, gbc);
        row++;

        // Campo Espécie
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Id Livro:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtIdLivro = new JTextField(20);
        add(txtIdLivro, gbc);
        row++;

        // Campo Dieta
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Data:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtData = new JTextField(20);
        add(txtData, gbc);
        row++;

    }

    private void buscaEmprest(){
        String idStr=JOptionPane.showInputDialog(this,"Digite o ID do aluno a ser Buscado:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                carregarEmpreEdit(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Por favor, digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void carregarEmpreEdit(int id){

        try {
            Emprestimo dinossauro = controller.GetEmprestimoPorId(id);
            if (dinossauro != null) {
                txtData.setText(String.valueOf(dinossauro.getDataCadastro()));
                txtIdAluno.setText( String.valueOf(dinossauro.getId_aluno()));
                txtIdLivro.setText(String.valueOf(dinossauro.getId_livro()));
                txtId.setText(String.valueOf(dinossauro.getId()));
                idEmprestimoEdit= dinossauro.getId_aluno(); // Define o ID para indicar que é uma edição
            } else {
                JOptionPane.showMessageDialog(this, "Emprestimo com ID " + id + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                limparCampos(); // Limpa os campos se não encontrar
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar Emprestimo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos(){
        txtId.setText("");
        txtData.setText("");
        txtIdAluno.setText("");
        txtIdLivro.setText("");
        idEmprestimoEdit= null; // Reseta para modo de novo cadastro
        btnBuscar.setEnabled(true); // Habilita o botão buscar novamente
    }
}
