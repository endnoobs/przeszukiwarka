package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

    public Main() {
        ustawieniaDomyslneOkna();
        initComponents();
    }

    public void initComponents() {


        panelSzukania.add(znajdz);
        panelSzukania.add(poleTextoweSzukany);

        panelSzukania.add(podmien);
        panelSzukania.add(wyrazPodmianka);


        znajdz.addActionListener(new znajdowanieHandler());
        podmien.addActionListener(new zamienianieHandler());

        this.getContentPane().add(obszarPrzewijania, BorderLayout.NORTH);
        this.getContentPane().add(panelSzukania, BorderLayout.CENTER);

    }



    private class znajdowanieHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            poczatekSzukanego = obszarTextowy.getText().indexOf(poleTextoweSzukany.getText(), poczatekSzukanego + poleTextoweSzukany.getText().length());

            System.out.println(poczatekSzukanego);

            if (poczatekSzukanego == -1) {
                poczatekSzukanego = obszarTextowy.getText().indexOf(poleTextoweSzukany.getText());
            }

            if (poczatekSzukanego >= 0 && poleTextoweSzukany.getText().length() > 0) {
                obszarTextowy.requestFocus();
                obszarTextowy.select(poczatekSzukanego, poczatekSzukanego + poleTextoweSzukany.getText().length());
            }
        }

        private int poczatekSzukanego = 0;
    }


    private class zamienianieHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (obszarTextowy.getSelectionStart() != obszarTextowy.getSelectionEnd()) {
                zamienTekst();
                //obszarTextowy.replaceRange(wyrazPodmianka.getText(),obszarTextowy.getSelectionStart(),obszarTextowy.getSelectionEnd());
            } else {
                znajdz.doClick();
                if (obszarTextowy.getSelectionStart() != obszarTextowy.getSelectionEnd()) {
                    zamienTekst();
                }

            }
        }

        private void zamienTekst() {
            obszarTextowy.requestFocus();

            //int opcja = JOptionPane.showConfirmDialog(rootPane,"Czy chcesz zamienic\""+poleTextoweSzukany.getText()+"\" na \"" + wyrazPodmianka.getText()+"\"" ,"Uwaga ! ",JOptionPane.YES_NO_OPTION);

            int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy chcesz zamienić \"" + poleTextoweSzukany.getText() + "\" na \"" + wyrazPodmianka.getText() + "\"", "Uwaga, nastąpi zmiana!", JOptionPane.YES_NO_OPTION);
            if (opcja == 0) {
                obszarTextowy.replaceRange(wyrazPodmianka.getText(), obszarTextowy.getSelectionStart(), obszarTextowy.getSelectionEnd());
            }
        }
    }


    public void ustawieniaDomyslneOkna() {
        this.setTitle("Pole wyboru");

        int szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
        int wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setSize(szerokosc / 2, wysokosc / 2); // Ustawienie rozmiaru ramki na 1/4 rozmiaru ekranu użytkownika

        int szerokoscRamki = this.getSize().width;
        int wysokoscRamki = this.getSize().height;
        this.setLocation((szerokosc - szerokoscRamki) / 2, (wysokosc - wysokoscRamki) / 2); // centrowanie ramki dynamicznie

        this.setTitle("Aplikacja z uzyciem biblioteki Swing w Javie");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("ikona.jpg"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    private JTextArea obszarTextowy = new JTextArea("Wpisz tutaj jakiś text albo skopiuj ", 7, 10);
    private JScrollPane obszarPrzewijania = new JScrollPane(obszarTextowy);

    private JPanel panelSzukania = new JPanel();

    private JButton znajdz = new JButton("Znajdz");
    private JTextField poleTextoweSzukany = new JTextField(10);

    private JButton podmien = new JButton("Podmień");
    private JTextField wyrazPodmianka = new JTextField(10);

    public static void main(String[] args) {
        new Main().setVisible(true);


    }
}
// obszarTextowy.selectAll(); // zaznaczanie całego textu
//obszarTextowy.select(0,2);// zaznaczenie obszaru w zakresie
//obszarTextowy.replaceSelection("zamianka"); // zamiana zaznaczonego obszru
// obszarTextowy.replaceRange("podmianka", 0,2);// podmianka na cos z obszru
//obszarTextowy.insert("cos tam",0); // dodanie w jakims miejscu
//obszarTextowy.append("Test ostatni"); // dodanie czegos na samym koncu
// obszarTextowy.select(obszarTextowy.getText().indexOf("test"),obszarTextowy.getText().indexOf("test")+"test".length());