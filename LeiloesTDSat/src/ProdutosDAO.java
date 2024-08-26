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
            try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return listagem;
    }

    public class ProdutosDAO {
    
    public boolean venderProduto(int produtoId) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, produtoId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
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
            try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return listagem;
    }

    void cadastrarProduto(ProdutosDTO produto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
