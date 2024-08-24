import com.mysql.jdbc.Connection;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        // Executa o código relacionado à GUI na thread de despacho de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Conectar ao banco de dados
                    ConectaDAO conectaDAO = new ConectaDAO();
                    Connection conn = (Connection) conectaDAO.connectDB();
                    
                    if (conn != null) {
                        System.out.println("Conexão bem-sucedida!");
                    } else {
                        System.out.println("Falha na conexão.");
                    }

                    // Exibir a tela de cadastroVIEW
                    cadastroVIEW cadastro = new cadastroVIEW();
                    cadastro.setVisible(true); // Torna a tela visível
                } catch (Exception e) {
                    System.out.println("Falha na conexão: " + e.getMessage());
                }
            }
        });
    }
}

