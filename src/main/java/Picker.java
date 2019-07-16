import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
class Picker {
        public static String result="";
        public static void main(String[] args) {
        JLabel label = new JLabel("Selected Date:");
        JLabel label1 = new JLabel("Enter the Details:");
        final JTextField text = new JTextField(20);
        final JTextArea text1 = new JTextArea(5,20);
        final JTextArea text2 = new JTextArea(10,30);
        JButton b = new JButton("Popup");
        JButton b1 = new JButton("Save Details");
        JButton b2 = new JButton("View Plans");
        final DateTimePicker dateTimePicker = new DateTimePicker();
        dateTimePicker.setFormats( DateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.MEDIUM ) );
        dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
        final Date date = new Date();
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
                    String detail = text.getText();
                    String detail1 =text1.getText();
                    String query ="insert into plan (dt,details)values(?,?)";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1,detail);
                    stmt.setString(2,detail1);
                    int set = stmt.executeUpdate();
                    if (set>0){
                        JOptionPane.showMessageDialog(null,"updated successfull");
                    }else {
                        JOptionPane.showMessageDialog(null,"insert valid data");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo","root","root");
                    Statement stmt=conn.createStatement();
                    ResultSet rs=stmt.executeQuery("select * from plan");
                    StringBuffer st=new StringBuffer();
                    while(rs.next()) {
                        st.append(rs.getString(1) + "  " + rs.getString(2));
                        st.append("\n");
                    }
                    text2.setText(st.toString());
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JPanel p = new JPanel();
        p.setBackground(Color.cyan);
        p.add(label);
        p.add(text);
        p.add(b);
        p.add(label1);
        p.add(text1);
        p.add(b1);
        p.add(b2);
        p.add(text2);
        final JFrame f = new JFrame();
        f.setSize(400,400);
        f.add(p);
        f.setVisible(true);
        f.setVisible(true);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
        dateTimePicker.dothis();
                text.setText(result);
            }
        });
        System.out.println("ther result is "+result);
    }
}