/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.DefaultComboBoxModel;
import javax.swing.*;
/**
 *
 * @author ESeries
 */
public class MyComboBoxModel extends DefaultComboBoxModel
{
    Connection cnn;
    PreparedStatement pst;
    ResultSet res;
    ResultSetMetaData rsmd;
    String url="jdbc:mysql://localhost/payment?user=root&password=eimon";
   public MyComboBoxModel()
   {
       MainPage mp=new MainPage();
       try{
            Class.forName("com.mysql.jdbc.Driver");
            cnn=DriverManager.getConnection(url);
            String sql="select column_name from information_schema.columns where table_name='students' and table_schema='payment'";
            //String sql="select table_name from information_schema.tables where table_schema='payment'";
            pst=cnn.prepareStatement(sql);
            res=pst.executeQuery();
            int size=0;
            while(res.next())
            {
                //String fee=res.getString("column_name");
                //feeCombo.addItem(fee);
                //size=res.getRow();
                
                /*for(int i=4;i<=size;i++)
                {
                    String fee=res.getString("column_name");
                    feeCombo.addItem(fee);
                }*/
                String fee=res.getString("column_name");
                size++;
                if(size>3)
                {
                    mp.getComboBox().addItem(fee);
                }
            }
            
            }catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
   }
   public static void main(String args[])
   {
       MyComboBoxModel myModel=new MyComboBoxModel();
   }
}
