package View;

import Controller.AlunoController;
import Model.Aluno;
import Model.Livro;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.valueOf;

public class CadastrarAlunoForm extends JInternalFrame {

    private AlunoController Controller;
    private JTextField txtID, txtNome, txtTele, txtIdade;
    private JButton btnSalvar, btnBuscar;
    private Integer idAlunoEdicao;

    public CadastrarAlunoForm(AlunoController Controller, Integer idAluno) {
        super("Cadastro de aluno", true, true, true, true);
        this.Controller = Controller;
        this.idAlunoEdicao = idAluno;


        setSize(500, 350); // Tamanho da janela interna
        setLayout(new GridBagLayout()); // Layout para organizar os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 40); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche o espaço horizontalmente


        int row = 0; // Contador de linhas para o layout

        // Campo ID
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        txtID = new JTextField(10);
        txtID.setEditable(false); // ID não pode ser editado diretamente, apenas buscado
        add(txtID, gbc);
        gbc.gridx = 2;
        gbc.gridy = row;
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarAluno()); // Adiciona ação ao botão Buscar
        add(btnBuscar, gbc);
        row++;

        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Ocupa 2 colunas
        txtNome = new JTextField(20);
        add(txtNome, gbc);
        row++;

        // Campo Espécie
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtTele = new JTextField(20);
        add(txtTele, gbc);
        row++;

        // Campo Dieta
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Idade:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtIdade = new JTextField(20);
        add(txtIdade, gbc);
        row++;


        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3; // Ocupa 3 colunas
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarAluno()); // Adiciona ação ao botão Salvar
        add(btnSalvar, gbc);

        if (idAlunoEdicao != null) {
            carregarAlunoParaEdicao(idAlunoEdicao);
            txtID.setText(String.valueOf(idAlunoEdicao));
            btnBuscar.setEnabled(false); // Desabilita o botão buscar se já está editando
        }


    }
    private void buscarAluno(){
        String idStr=JOptionPane.showInputDialog(this,"Digite o ID do aluno a ser Buscado:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                carregarAlunoParaEdicao(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Por favor, digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarAlunoParaEdicao(int id) {
        try {
            Aluno dinossauro = Controller.buscarAlunoPorId(id);
            if (dinossauro != null) {
                txtID.setText(String.valueOf(dinossauro.getId_aluno()));
                txtNome.setText(dinossauro.getNome());
                txtTele.setText(dinossauro.getTelefone());
                txtIdade.setText(String.valueOf(dinossauro.getIdade()));
                idAlunoEdicao= dinossauro.getId_aluno(); // Define o ID para indicar que é uma edição
            } else {
                JOptionPane.showMessageDialog(this, "Aluno com ID " + id + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                limparCampos(); // Limpa os campos se não encontrar
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar Aluno: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void salvarAluno(){
        try {
            String nome = txtNome.getText().trim();
            String telefone= txtTele.getText().trim();
            int idade = Integer.parseInt(txtIdade.getText().trim());

            if (idAlunoEdicao == null) { // Se dinossauroIdParaEdicao é null, é um novo cadastro
                Controller.cadastrarAluno(nome, idade, telefone);
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            } else { // Caso contrário, é uma atualização
                Controller.EditAluno(idAlunoEdicao,nome,idade,telefone);
                JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso!");
            }
            this.dispose(); // Fecha a janela após a operação bem-sucedida
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar Livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }


    }
    private void limparCampos() {
        txtID.setText("");
        txtNome.setText("");
        txtIdade.setText("");
        txtTele.setText("");
        idAlunoEdicao= null; // Reseta para modo de novo cadastro
        btnBuscar.setEnabled(true); // Habilita o botão buscar novamente
    }

}
