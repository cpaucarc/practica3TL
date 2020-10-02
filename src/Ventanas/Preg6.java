package Ventanas;

import Gramatica.Palabras;
import Gramatica.Produccion;
import Gramatica.Tipos;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Preg6 extends javax.swing.JFrame {

    Tipos tipos = new Tipos();
    public Palabras palabras = new Palabras();
    public ArrayList<String> VT = new ArrayList<>();
    public ArrayList<String> VN = new ArrayList<>();
    public ArrayList<Produccion> producciones = new ArrayList<>();
    public int tipoGramatica = 3;

    public Preg6() {
        initComponents();
        setSize(new Dimension(520, 470));
        System.out.println("CAntente".matches("(.*)ant(.*)"));
    }

    public void almacenarVT(String texto) {
        VT.clear();
        String[] posiblesVT = obtenerElementos(texto);
        for (String elemento : posiblesVT) {
            if (tipos.esVT(elemento.trim()) && !VT.contains(elemento.trim())) {
                VT.add(elemento);
            }
        }
        String union = unirPorComas(VT);
        lbVT.setText("VT = {" + union + "}");
    }

    public void almacenarVN(String texto) {
        String[] posiblesVN = obtenerElementos(texto);
        for (String elemento : posiblesVN) {
            if (Tipos.esVN(elemento.trim()) && !VN.contains(elemento.trim())) {
                VN.add(elemento);
            }
        }
        String union = unirPorComas(VN);
        lbVN.setText("VN = {" + union + "}");
    }

    public void agregarProduccion(Produccion prd) {
        producciones.add(prd);
        String produccion = prd.getIzq() + "\t→\t" + prd.getDer();
        taProds.setText(taProds.getText() + produccion + "\n");
        txProdIzq.setText("");
        txProdDer.setText("");
        evaluarTipoGramatica(prd);
    }

    public void evaluarTipoGramatica(Produccion produccion) {
        int tipoProduccionEvaluada = Tipos.evaluarTipoDeProduccion(produccion);
        if (tipoProduccionEvaluada < tipoGramatica) {
            tipoGramatica = tipoProduccionEvaluada;
        }
    }

    public String[] obtenerElementos(String texto) {
        texto = texto.replace("", ",");
        texto = texto.substring(1, texto.length());
        return texto.split(",");
    }

    public String unirPorComas(ArrayList<String> vocabulario) {
        String union = "";
        for (String e : vocabulario) {
            union += e + ", ";
        }
        return union.length() > 0 ? union.substring(0, union.length() - 2) : union;
    }

    /*Inutil*/
    public boolean existeSimboloInicial() {
        for (Produccion prd : producciones) {
            if (prd.getIzq().equals("S")) {
                return true;
            }
        }
        return false;
    }

    public String obtenerDerechaDeProduccion(String parteIzq) {
        for (Produccion prd : producciones) {
            if (prd.getIzq().equals(parteIzq)) {
                return prd.getDer();
            }
        }
        return "";
    }
    /* Fin Inutil*/
    
    public void evaluarExpresionGramatica(){  // a(ab)^n
        ArrayList<Produccion> aux_prod = new ArrayList<>(producciones);
        Produccion simboloInic = null;
        for (Produccion prd : producciones){
            if (prd.getIzq().equals("S") && !Tipos.esVT(prd.getDer())){
                simboloInic = prd;
                aux_prod.remove(producciones.indexOf(prd));
            }
        }
        
        if (simboloInic != null){
            String regex = formarExpresionRegular(simboloInic);
        }
        //ToDo
    }

    public String formarExpresionRegular (Produccion prd){
        boolean esRecursivo = prd.getDer().matches("(.*)"+prd.getIzq()+"(.*)"); // forma: X -> aX
        String regex = "";
        if (esRecursivo){
            String[] aux = prd.getDer().split(prd.getIzq());
            for (String a : aux){
                if (!a.equals(prd.getIzq())){
                    regex += "["+a+"]*";
                }
            }
        }else{
            regex = "["+prd.getDer()+"]{1}";
        }     
        return regex;
    }
    
    public ArrayList<String> crearCadenas(int cantidad, ArrayList<String> vt){
        ArrayList<String> cadenas = new ArrayList<>();
        int longitud;
        
        while(cadenas.size() < cantidad){
            longitud = this.palabras.obtenerNumeroAleatorioEntreDosNumeros(1, 10);
            String cadena = this.palabras.generarPalabras(VT, longitud);
            if (!cadenas.contains(cadena)){
                cadenas.add(cadena);
            }
        }
        return cadenas;
    }
    
    public void mostrarResultados(){
        JTextArea textArea = new JTextArea();
        ArrayList<String> cadenas = crearCadenas(Integer.parseInt(txCantCadenas.getText()), VT);
        String resultado = "El tipo de gramatica es: "+tipoGramatica+"\n";
        resultado += "\nLas cadenas generadas son: ";
        for (int i=0; i<cadenas.size(); i++){
            resultado += "\n    "+(i+1)+" - "+cadenas.get(i);
        }
        textArea.setText(resultado);
        textArea.setFont(new java.awt.Font("Segoe UI", 0, 15));
        JOptionPane.showMessageDialog(null, textArea);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbVN = new javax.swing.JLabel();
        txVT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txVN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txProdIzq = new javax.swing.JTextField();
        txProdDer = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btAgregarProd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taProds = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        lbVT = new javax.swing.JLabel();
        txCantCadenas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btCalc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partes de la Gramatica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(33, 29, 37))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbVN.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbVN.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.add(lbVN, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 105, 250, 25));

        txVT.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txVT.setForeground(new java.awt.Color(33, 29, 37));
        txVT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txVTKeyReleased(evt);
            }
        });
        jPanel2.add(txVT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 250, 25));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(33, 29, 37));
        jLabel3.setText("Vocabulario No Terminal");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 25));

        txVN.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txVN.setForeground(new java.awt.Color(33, 29, 37));
        txVN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txVNKeyReleased(evt);
            }
        });
        jPanel2.add(txVN, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 250, 25));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(33, 29, 37));
        jLabel4.setText("Producciones");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 20));

        txProdIzq.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txProdIzq.setForeground(new java.awt.Color(33, 29, 37));
        txProdIzq.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txProdIzq.setText("S");
        txProdIzq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txProdIzqKeyTyped(evt);
            }
        });
        jPanel2.add(txProdIzq, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 100, 25));

        txProdDer.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txProdDer.setForeground(new java.awt.Color(33, 29, 37));
        txProdDer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txProdDerKeyTyped(evt);
            }
        });
        jPanel2.add(txProdDer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 100, 25));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(33, 29, 37));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("→");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 75, 25));

        btAgregarProd.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btAgregarProd.setForeground(new java.awt.Color(33, 29, 37));
        btAgregarProd.setText("Agregar");
        btAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarProdActionPerformed(evt);
            }
        });
        jPanel2.add(btAgregarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 80, 25));

        taProds.setColumns(20);
        taProds.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        taProds.setForeground(new java.awt.Color(33, 29, 37));
        taProds.setRows(5);
        jScrollPane1.setViewportView(taProds);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 380, 110));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(33, 29, 37));
        jLabel6.setText("Vocabulario Terminal");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 25));

        lbVT.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lbVT.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.add(lbVT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 55, 250, 25));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 460, 330));

        txCantCadenas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txCantCadenas.setForeground(new java.awt.Color(33, 29, 37));
        txCantCadenas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txCantCadenas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCantCadenasKeyTyped(evt);
            }
        });
        jPanel1.add(txCantCadenas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 90, 25));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(33, 29, 37));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Generar cadenas N°");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 115, 25));

        btCalc.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btCalc.setForeground(new java.awt.Color(33, 29, 37));
        btCalc.setText("Calcular");
        btCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCalcActionPerformed(evt);
            }
        });
        jPanel1.add(btCalc, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 380, 80, 25));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txVTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txVTKeyReleased
        almacenarVT(txVT.getText());
    }//GEN-LAST:event_txVTKeyReleased

    private void txVNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txVNKeyReleased
        almacenarVN(txVN.getText());
    }//GEN-LAST:event_txVNKeyReleased

    private void txCantCadenasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCantCadenasKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txCantCadenasKeyTyped

    private void txProdIzqKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txProdIzqKeyTyped
        if (!VT.contains("" + evt.getKeyChar()) && !VN.contains("" + evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txProdIzqKeyTyped

    private void txProdDerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txProdDerKeyTyped
        if (!VT.contains("" + evt.getKeyChar()) && !VN.contains("" + evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txProdDerKeyTyped

    private void btAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarProdActionPerformed
        String izq = txProdIzq.getText();
        String der = txProdDer.getText().length() > 0 ? txProdDer.getText() : "λ";
        if (izq.length() > 0 && der.length() > 0) {
            agregarProduccion(new Produccion(izq, der));
        } else {
            txProdIzq.grabFocus();
        }
    }//GEN-LAST:event_btAgregarProdActionPerformed

    private void btCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCalcActionPerformed
        if (existeSimboloInicial()) {
            if (txCantCadenas.getText().trim().length()>0){
                mostrarResultados();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe una produccion con en simbolo inicial 'S'");
            txProdIzq.setText("S");
            txProdDer.grabFocus();
        }
    }//GEN-LAST:event_btCalcActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preg6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>        
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Preg6().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAgregarProd;
    private javax.swing.JButton btCalc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbVN;
    private javax.swing.JLabel lbVT;
    private javax.swing.JTextArea taProds;
    private javax.swing.JTextField txCantCadenas;
    private javax.swing.JTextField txProdDer;
    private javax.swing.JTextField txProdIzq;
    private javax.swing.JTextField txVN;
    private javax.swing.JTextField txVT;
    // End of variables declaration//GEN-END:variables
}
