
package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Conexao {
    private Statement stmt; //executar consultas DML
    private ResultSet rs; //gerenciar consultas DQL
    private Connection c;         

    public Conexao() {
        String Database = "rafael_psc";
        String user = "rafael_psc_1";
        String password = "Entrega@App720";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://rafael-psc.mysql.uhserver.com:3306/" + Database + "?serverTimezone=UTC";
        
        //Abrir conexão
        try {            
            Class.forName(driver).newInstance();                               
            c = DriverManager.getConnection(url, user, password);            
            stmt = c.createStatement();              
        }
        catch (Exception e) {            
            JOptionPane.showMessageDialog(null, "Erro na conexão MySQL");            
            JOptionPane.showMessageDialog(null, e.getMessage());
            JOptionPane.showMessageDialog(null, "A aplicação será finalizada...");
            System.exit(0); //Finalizar a aplicação
        }              
    }    
    
    public boolean SQLExecute(String SQL) {
        try {
            this.stmt.execute(SQL);
            return true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }        
    }

    public boolean setResultSet(String Query) {
        try {
            this.rs = this.stmt.executeQuery(Query);
            return true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }                
    }    
    
    public void setComboBox(JComboBox ComboBox, String TableName) {
        //Limpar o combobox
        ComboBox.removeAllItems();
        
        //Listar itens para adicionar no combobox
        try {
            this.setResultSet("SELECT Nome FROM " + TableName + " ORDER BY Nome");
            if (this.getResultSet().first()) {
                do {
                    ComboBox.addItem(this.getResultSet().getString("Nome"));
                } while(this.getResultSet().next());
                
                //Deixar sem seleção
                ComboBox.setSelectedIndex(-1);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public String getCodigoComboBox(JComboBox ComboBox, String TableName) {
        String Codigo = "-1";
        try {
            this.setResultSet("SELECT Cod" + TableName +
                    " FROM " + TableName + 
                    " WHERE Nome LIKE '" + ComboBox.getSelectedItem() + "'");

            if (this.getResultSet().first()) 
                 Codigo = this.getResultSet().getString("Cod" + TableName);
   
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Codigo;
    }
    
    public ResultSet getResultSet() {
        return rs;
    }       
          
}