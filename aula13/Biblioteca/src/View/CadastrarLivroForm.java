package View;

import Controller.LivroController;
import Model.Livro;

import javax.swing.*;
import java.awt.*;

import static DAO.LivroDAO.buscarLivroPorId;

public class CadastrarLivroForm extends JInternalFrame{

    private LivroController Controller;
    private JTextField txtId, txtNome, txtGenero,txtAutor,txtISBN;
    private JButton btnSalvar,btnBuscar;
    private Integer idLivroEdit;

    public CadastrarLivroForm(LivroController controller,Integer idLivro){
        super("Cadastro de Livros",true,true,true,true);
        this.Controller=controller;
        this.idLivroEdit=idLivro;

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
        btnBuscar.addActionListener(e -> buscarLivro()); // Adiciona ação ao botão Buscar
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
        add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtAutor = new JTextField(20);
        add(txtAutor, gbc);
        row++;

        // Campo Dieta
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Genero:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtGenero = new JTextField(20);
        add(txtGenero, gbc);
        row++;

        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("ISBN:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtISBN = new JTextField(20); // Preenche com a data atual por padrão
        add(txtISBN, gbc);
        row++;

        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3; // Ocupa 3 colunas
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarLivro()); // Adiciona ação ao botão Salvar
        add(btnSalvar, gbc);

        if (idLivroEdit != null) {
            carregarLivroPraEdicao(idLivroEdit);
            txtId.setText(String.valueOf(idLivroEdit));
            btnBuscar.setEnabled(false); // Desabilita o botão buscar se já está editando
        }
    }

    private void buscarLivro(){
        String idStr = JOptionPane.showInputDialog(this, "Digite o ID do Livro para buscar:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                carregarLivroPraEdicao(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Por favor, digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void carregarLivroPraEdicao(int id) {
        try {
            Livro dinossauro = Controller.buscarLivroPorId(id);
            if (dinossauro != null) {
                txtId.setText(String.valueOf(dinossauro.getId()));
                txtNome.setText(dinossauro.getNome());
                txtAutor.setText(dinossauro.getAutor());
                txtGenero.setText(dinossauro.getGenero());
                txtISBN.setText(dinossauro.getISBN());
                idLivroEdit= dinossauro.getId(); // Define o ID para indicar que é uma edição
            } else {
                JOptionPane.showMessageDialog(this, "Livro com ID " + id + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                limparCampos(); // Limpa os campos se não encontrar
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar Livro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarLivro() {
        try {
            String nome = txtNome.getText().trim();
            String autor= txtAutor.getText().trim();
            String genero = txtGenero.getText().trim();
            String isbn = txtISBN.getText().trim();

            if (idLivroEdit == null) { // Se dinossauroIdParaEdicao é null, é um novo cadastro
                Controller.setLivro(nome, autor, genero, isbn);
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            } else { // Caso contrário, é uma atualização
                Controller.EditLivro(idLivroEdit,nome,autor,genero,isbn);
                JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso!");
            }
            this.dispose(); // Fecha a janela após a operação bem-sucedida
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar Livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtAutor.setText("");
        txtGenero.setText("");
        txtISBN.setText("");
        idLivroEdit= null; // Reseta para modo de novo cadastro
        btnBuscar.setEnabled(true); // Habilita o botão buscar novamente
    }
}
