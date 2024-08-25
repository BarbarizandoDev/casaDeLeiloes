import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultSet;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    // Método para cadastrar um novo produto
    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new ConectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
        } finally {
            try {
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }
    }

    // Método para listar todos os produtos
    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new ConectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos";
        
        try {
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
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }
        
        return listagem;
    }

    // Método para vender um produto (atualizar status para "Vendido")
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

    // Método para listar todos os produtos vendidos
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();
        conn = new ConectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        
        try {
            prep = conn.prepareStatement(sql);
            resultSet = prep.executeQuery();
            
            while (resultSet.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setValor(resultSet.getInt("valor"));
                produto.setStatus(resultSet.getString("status"));
                
                produtosVendidos.add(produto);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }
        
        return produtosVendidos;
    }

    private Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
