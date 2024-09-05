import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultSet;

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {
            conn = new ConectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultSet = prep.executeQuery();

            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (SQLException e) {}
        }

        return listagem;
    }

    public boolean venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try {
            conn = new ConectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
            return false;
        } finally {
            try { conn.close(); } catch (SQLException e) {}
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();

        try {
            conn = new ConectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultSet = prep.executeQuery();

            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));

                listagem.add(produto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        } finally {
            try { conn.close(); } catch (SQLException e) {}
        }

        return listagem;
    }

     public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            conn = new ConectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            
            // Definindo os parâmetros da query
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            int rowsAffected = prep.executeUpdate(); // Executa o comando SQL

            return rowsAffected > 0; // Retorna true se o produto foi cadastrado com sucesso

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close(); // Fecha a conexão com o banco de dados
                }
            } catch (SQLException e) {
            }
        }
    }
}
