package UTP6.zad1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MyGUI implements ActionListener {

    private List<Travel> list;
    JFrame frame;
    JPanel firstPanel;
    JPanel firstLowerPanel;
    JTable table;
    JLabel label=new JLabel("Choose your language");
    JButton buttonPL=new JButton("Polish");
    JButton buttonENG=new JButton("English");

    public MyGUI(List<Travel> list){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e){
            System.out.println("Error setting Nimbus Look and Feel: "+e);
        }

        basics();
        this.list=list;
        makeFirstPanel();
    }


    public void basics(){
        frame=new JFrame("Application");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(640,480);
    }

    public void makeFirstPanel(){
        firstPanel=new JPanel();
        firstPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        firstLowerPanel=new JPanel();
        firstLowerPanel.setLayout(new GridLayout(0,2,50,50));
        frame.add(firstPanel);
        firstPanel.add(label, c);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        buttonPL.addActionListener(this);
        buttonENG.addActionListener(this);
        firstLowerPanel.add(buttonPL);
        firstLowerPanel.add(buttonENG);
        c.gridy = 1;
        firstPanel.add(firstLowerPanel, c);
    }


    public void makeTable(Locale locale){
        String[] columns=new String[6];
        if(locale.getLanguage().equals("en"))
            columns=new String[] {"country", "dateFrom", "dateTo", "place", "price", "currency"};
        else if(locale.getLanguage().equals("pl"))
            columns=new String[] {"kraj", "dataOd", "dataDo", "miejsce", "cena", "waluta"};

        Object[][] data=new Object[list.size()][6];
        NumberFormat numberFormat=NumberFormat.getInstance(locale);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
            data[i][0]=Dictionary.translateCountryName(list.get(i).getLoc(),locale,list.get(i).getCountry());
            data[i][1]=format.format(list.get(i).getDateFrom());
            data[i][2]=format.format(list.get(i).getDateTo());
            data[i][3]=Dictionary.translateWord(list.get(i).getLoc(),locale,list.get(i).getPlace());
            data[i][4]=numberFormat.format(list.get(i).getPrice());
            data[i][5]=list.get(i).getCurrency();
        }

        table=new JTable(data,columns){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Locale locale=null;
        if(e.getSource()==buttonPL){
            locale=new Locale("pl");
        } else if(e.getSource() == buttonENG){
            locale=new Locale("en");
        }
        makeTable(locale);
        frame.remove(firstPanel);
        frame.add(new JScrollPane(table));
        frame.revalidate();
        frame.repaint();
    }


}