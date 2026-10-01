
package piaiis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class Interfaz extends javax.swing.JFrame {
    
    DefaultTableModel tabla = new DefaultTableModel();

    public int cantidadI;
    public Producto One = new Producto("Lasagna", 22500f, 0, "lasagna.png");
    public Producto Two = new Producto("Pollo Broaster", 26000f, 0, "pollobroaster.jpg");
    public Producto Three = new Producto("Pollo Asado", 24000f, 0, "polloasado.jpg");
    public Producto Four = new Producto("Ejecutivo", 15000f, 0, "ejecutivo.jpg");
    public Producto Five = new Producto("Bandeja Paisa", 20000f, 0, "bandejapaisa.jpg");
    public Producto Six = new Producto("Sancocho", 33000f, 0, "sancocho.jpg");
    public String filaId[] = {"Cantidad", "Nombre", "Valor Unitario", "Valor Total"};
    public float TotalC;
    
    public Interfaz() 
    {
        initComponents();
        tabla.setColumnIdentifiers(filaId);
        resumen.setModel(tabla);
        this.setLocationRelativeTo(null);
        SetImageLabel(img1,One.rutaImg);
        SetImageLabel(img2,Two.rutaImg);
        SetImageLabel(img3,Three.rutaImg);
        SetImageLabel(img4,Four.rutaImg);
        SetImageLabel(img5,Five.rutaImg);
        SetImageLabel(img6,Six.rutaImg);
        SetImageLabel(visa, "/imagenes/visa.png");
        SetImageLabel(mastercard, "/imagenes/mastercard.png");
        SetImageLabel(americane, "/imagenes/americane.png");
        SetImageLabel(pse,"/imagenes/pse.png");
        SetImageLabel(efectivo,"/imagenes/efectivo.png");
        SetImageLabel(qr,"/imagenes/qr.png");
        Update();
        setResizable(false);
        CalculoTotal();
        desaparecerPag2(jPanel3);
    }
    
    private void desaparecerPag2(JPanel pag)
    {
        pag.setVisible(false);
    }
    
    private void mostrarPag2(JPanel pag)
    {
        pag.setVisible(true);
    }
    
    private void desaparecerPag()
    {
        sig.setVisible(false);
        jScrollPane1.setVisible(false);
        jLabel2.setVisible(false);
        totalLab.setVisible(false);
        restb.setVisible(false);
        jPanel1.setVisible(false);
        filler1.setVisible(false);
        filler2.setVisible(false);
        filler4.setVisible(false);
    }
    
    private void mostrarPag()
    {
        sig.setVisible(true);
        jScrollPane1.setVisible(true);
        jLabel2.setVisible(true);
        totalLab.setVisible(true);
        restb.setVisible(true);
        jPanel1.setVisible(true);
        filler1.setVisible(true);
        filler2.setVisible(true);
        filler4.setVisible(true);
    }
    
    
    private void maxButton(JLabel cantidad, Producto Objeto)
    {
        Objeto.cantidad=Aumentar(Objeto.cantidad);
        cantidad.setText(Integer.toString(Objeto.cantidad));
        CalculoTotal();
    }
    
    private void menosButton(JLabel cantidad, Producto Objeto)
    {
        Objeto.cantidad=Decremento(Objeto.cantidad);
        cantidad.setText(Integer.toString(Objeto.cantidad));
        CalculoTotal();
    }
    
    private void TablaMax(Producto producto)
    {
        String cantidad = String.valueOf(producto.cantidad);
        String nombre = String.valueOf(producto.nombre);
        String valorU = String.valueOf(producto.valor);
        String valorT = String.valueOf(producto.obtenerVT());
        
        int filas = tabla.getRowCount();
        int fila=0;
        Boolean existe = false;

        
        if(filas != 0)
        {
            while(fila<filas)
            {
                if(nombre.equals(tabla.getValueAt(fila, 1)))
                {
                    existe=true;
                    break;
                }
                else
                {
                    fila++;
                }
            }
                if(existe)
                {
                tabla.setValueAt(valorT, fila, 3);
                tabla.setValueAt(cantidad, fila, 0);
                }
                else
                {
                tabla.addRow(new Object[]{cantidad, nombre, valorU, valorT});  
                }
            
        }
        else
        {
            tabla.addRow(new Object[]{cantidad, nombre, valorU, valorT});
        }
        
        
            
        resumen.setModel(tabla);
        
        
        
    }
    
    private void TablaMenos(Producto producto)
    {
        String cantidad = String.valueOf(producto.cantidad);
        String nombre = String.valueOf(producto.nombre);
        String valorU = String.valueOf(producto.valor);
        String valorT = String.valueOf(producto.obtenerVT());
        
        int filas = tabla.getRowCount();
        int fila=0;
        Boolean existe = false;

        
        if(filas != 0)
        {
            while(fila<filas)
            {
                if(nombre.equals(tabla.getValueAt(fila, 1)))
                {
                    existe=true;
                    break;
                }
                else
                {
                    fila++;
                }
            }
            if(existe)
            {
                if(producto.cantidad==0)
                {
                    tabla.removeRow(fila);
                }
                else
                {
                    tabla.setValueAt(valorT, fila, 3);
                    tabla.setValueAt(cantidad, fila, 0);
                }
            }
        }
    }
    
    private void CalculoTotal()
    {
        float Total = One.obtenerVT()+Two.obtenerVT()+Three.obtenerVT()+Four.obtenerVT()+Five.obtenerVT()+Six.obtenerVT();
        TotalC = Total;
        totalLab.setText("   Total: $"+Float.toString(Total));
    }
    
    private void Update()
    {
        UpdateObj(cant1,nombreP1,prec1,One);
        UpdateObj(cant2,nombreP2,prec2,Two);
        UpdateObj(cant3,nombreP3,prec3,Three);
        UpdateObj(cant4,nombreP4,prec4,Four);
        UpdateObj(cant5,nombreP5,prec5,Five);
        UpdateObj(cant6,nombreP6,prec6,Six);
    }
    
    private void UpdateObj(JLabel cantidad, JLabel nombre, JLabel precio, Producto Objeto)
    {
        cantidad.setText(Integer.toString(Objeto.cantidad));
        nombre.setText(Objeto.nombre);
        precio.setText("$"+Float.toString(Objeto.valor));
    }
    
    
    private void SetImageLabel(JLabel labelName, String resourcePath)
    {
        try {
        ImageIcon image = new ImageIcon(getClass().getResource(resourcePath));
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_SMOOTH));
        labelName.setIcon(icon);
    } catch (Exception e) {
        System.err.println("No se pudo cargar la imagen: " + resourcePath);
        e.printStackTrace();
    }
    }
    
    private int Aumentar(int cantidad)
    {
        if(cantidad==10)
        {
            JOptionPane.showMessageDialog(null, "Cantidad máxima alcanzada");
        }
        else
        {
        cantidad++;
        }
        return cantidad;
    }
    
    private int Decremento(int cantidad)
    {
        if(cantidad==0)
        {
            JOptionPane.showMessageDialog(null,"Cantidad mínima alcanzada");
        }
        else
        {
            cantidad--;
        }
        return cantidad;
    }
    
    private void Reiniciar()
    {
        int filas,i=0;
        filas = tabla.getRowCount();
        One.cantidad=0;
        Two.cantidad=0;
        Three.cantidad=0;
        Four.cantidad=0;
        Five.cantidad=0;
        Six.cantidad=0;
        while(i<filas)
        {
            tabla.removeRow(0);
            i++;
        }
        resumen.setModel(tabla);
        Update();
        CalculoTotal();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        sig = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        resumen = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        totalLab = new javax.swing.JLabel();
        restb = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        img1 = new javax.swing.JLabel();
        max1 = new javax.swing.JButton();
        menos1 = new javax.swing.JButton();
        cant1 = new javax.swing.JLabel();
        nombreP1 = new javax.swing.JLabel();
        prec1 = new javax.swing.JLabel();
        img2 = new javax.swing.JLabel();
        nombreP2 = new javax.swing.JLabel();
        max2 = new javax.swing.JButton();
        menos2 = new javax.swing.JButton();
        cant2 = new javax.swing.JLabel();
        prec2 = new javax.swing.JLabel();
        nombreP3 = new javax.swing.JLabel();
        max3 = new javax.swing.JButton();
        prec3 = new javax.swing.JLabel();
        cant3 = new javax.swing.JLabel();
        menos3 = new javax.swing.JButton();
        img3 = new javax.swing.JLabel();
        nombreP4 = new javax.swing.JLabel();
        max4 = new javax.swing.JButton();
        prec4 = new javax.swing.JLabel();
        cant4 = new javax.swing.JLabel();
        menos4 = new javax.swing.JButton();
        img4 = new javax.swing.JLabel();
        nombreP5 = new javax.swing.JLabel();
        menos5 = new javax.swing.JButton();
        max5 = new javax.swing.JButton();
        cant5 = new javax.swing.JLabel();
        img5 = new javax.swing.JLabel();
        prec5 = new javax.swing.JLabel();
        prec6 = new javax.swing.JLabel();
        cant6 = new javax.swing.JLabel();
        img6 = new javax.swing.JLabel();
        nombreP6 = new javax.swing.JLabel();
        max6 = new javax.swing.JButton();
        menos6 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        back = new javax.swing.JButton();
        sig2 = new javax.swing.JButton();
        americane = new javax.swing.JLabel();
        visa = new javax.swing.JLabel();
        mastercard = new javax.swing.JLabel();
        pse = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        efectivo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        qr = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sig.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sig.setForeground(new java.awt.Color(0, 204, 0));
        sig.setText("Siguiente");
        sig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigActionPerformed(evt);
            }
        });

        resumen = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        resumen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Cantidad", "Nombre ", "Valor Unitario", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resumen.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(resumen);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Productos Añadidos");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        totalLab.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalLab.setForeground(new java.awt.Color(0, 0, 0));
        totalLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalLab.setText("  Total:");
        totalLab.setToolTipText("");
        totalLab.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        restb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        restb.setForeground(new java.awt.Color(204, 0, 0));
        restb.setText("Reiniciar Productos");
        restb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restbActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        img1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img1.setText("Imagen");
        img1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        img1.setMaximumSize(new java.awt.Dimension(569, 350));
        img1.setPreferredSize(new java.awt.Dimension(569, 350));

        max1.setText("+");
        max1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        max1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                max1ActionPerformed(evt);
            }
        });

        menos1.setText("-");
        menos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menos1ActionPerformed(evt);
            }
        });

        cant1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant1.setText("cantidad");

        nombreP1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nombreP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP1.setLabelFor(img1);
        nombreP1.setText("nombreProducto");
        nombreP1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreP1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        prec1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prec1.setText("precio");
        prec1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        img2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img2.setText("Imagen");
        img2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        img2.setMaximumSize(new java.awt.Dimension(569, 350));
        img2.setPreferredSize(new java.awt.Dimension(569, 350));

        nombreP2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nombreP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP2.setLabelFor(img1);
        nombreP2.setText("nombreProducto");
        nombreP2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreP2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        max2.setText("+");
        max2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        max2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                max2ActionPerformed(evt);
            }
        });

        menos2.setText("-");
        menos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menos2ActionPerformed(evt);
            }
        });

        cant2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant2.setText("cantidad");

        prec2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prec2.setText("precio");
        prec2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        nombreP3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nombreP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP3.setLabelFor(img1);
        nombreP3.setText("nombreProducto");
        nombreP3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreP3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        max3.setText("+");
        max3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        max3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                max3ActionPerformed(evt);
            }
        });

        prec3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prec3.setText("precio");
        prec3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cant3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant3.setText("cantidad");

        menos3.setText("-");
        menos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menos3ActionPerformed(evt);
            }
        });

        img3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img3.setText("Imagen");
        img3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        img3.setMaximumSize(new java.awt.Dimension(569, 350));
        img3.setPreferredSize(new java.awt.Dimension(569, 350));

        nombreP4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nombreP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP4.setLabelFor(img1);
        nombreP4.setText("nombreProducto");
        nombreP4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreP4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        max4.setText("+");
        max4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        max4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                max4ActionPerformed(evt);
            }
        });

        prec4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prec4.setText("precio");
        prec4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cant4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant4.setText("cantidad");

        menos4.setText("-");
        menos4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menos4ActionPerformed(evt);
            }
        });

        img4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img4.setText("Imagen");
        img4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        img4.setMaximumSize(new java.awt.Dimension(569, 350));
        img4.setPreferredSize(new java.awt.Dimension(569, 350));

        nombreP5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nombreP5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP5.setLabelFor(img1);
        nombreP5.setText("nombreProducto");
        nombreP5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreP5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        menos5.setText("-");
        menos5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menos5ActionPerformed(evt);
            }
        });

        max5.setText("+");
        max5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        max5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                max5ActionPerformed(evt);
            }
        });

        cant5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant5.setText("cantidad");

        img5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img5.setText("Imagen");
        img5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        img5.setMaximumSize(new java.awt.Dimension(569, 350));
        img5.setPreferredSize(new java.awt.Dimension(569, 350));

        prec5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prec5.setText("precio");
        prec5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        prec6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prec6.setText("precio");
        prec6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cant6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cant6.setText("cantidad");

        img6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img6.setText("Imagen");
        img6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        img6.setMaximumSize(new java.awt.Dimension(569, 350));
        img6.setPreferredSize(new java.awt.Dimension(569, 350));

        nombreP6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nombreP6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreP6.setLabelFor(img1);
        nombreP6.setText("nombreProducto");
        nombreP6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        nombreP6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        max6.setText("+");
        max6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        max6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                max6ActionPerformed(evt);
            }
        });

        menos6.setText("-");
        menos6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menos6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prec1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombreP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(menos1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cant1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(max1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prec2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombreP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(menos2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cant2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(max2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prec3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombreP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(menos3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cant3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(max3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prec4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombreP4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(menos4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cant4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(max4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prec5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombreP5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(menos5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cant5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(max5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(img6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(prec6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombreP6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(menos6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cant6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(max6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(img2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreP2)
                                .addGap(13, 13, 13)
                                .addComponent(prec2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(menos2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(cant2))
                                    .addComponent(max2)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(img1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombreP1)
                                .addGap(13, 13, 13)
                                .addComponent(prec1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(menos1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(cant1))
                                    .addComponent(max1)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(img3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreP3)
                        .addGap(13, 13, 13)
                        .addComponent(prec3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menos3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cant3))
                            .addComponent(max3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(img6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreP6)
                        .addGap(13, 13, 13)
                        .addComponent(prec6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menos6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cant6))
                            .addComponent(max6)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(img4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreP4)
                        .addGap(13, 13, 13)
                        .addComponent(prec4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menos4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cant4))
                            .addComponent(max4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(img5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreP5)
                        .addGap(13, 13, 13)
                        .addComponent(prec5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menos5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(cant5))
                            .addComponent(max5))))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(972, 544));
        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Métodos de Pago");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(320, 0, 328, 68);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton1);
        jRadioButton1.setBounds(40, 100, 19, 27);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setText("Tarjeta de crédito o débito");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(70, 100, 270, 27);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton2);
        jRadioButton2.setBounds(40, 200, 19, 27);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setText("Transferencia con PSE");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(70, 200, 270, 27);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setText("Efectivo");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(70, 300, 270, 27);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton3);
        jRadioButton3.setBounds(40, 300, 19, 27);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setText("Código QR");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(70, 400, 270, 27);

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton4);
        jRadioButton4.setBounds(40, 400, 19, 27);
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(0, 470, 980, 20);

        back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 0, 0));
        back.setText("Atrás");
        back.setPreferredSize(new java.awt.Dimension(141, 31));
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        jPanel3.add(back);
        back.setBounds(10, 480, 210, 60);

        sig2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sig2.setForeground(new java.awt.Color(0, 204, 0));
        sig2.setText("Generar Factura");
        sig2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sig2ActionPerformed(evt);
            }
        });
        jPanel3.add(sig2);
        sig2.setBounds(750, 480, 210, 60);

        americane.setText("americane");
        jPanel3.add(americane);
        americane.setBounds(850, 90, 80, 50);

        visa.setText("visa");
        visa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel3.add(visa);
        visa.setBounds(610, 90, 110, 50);

        mastercard.setText("mastercard");
        mastercard.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel3.add(mastercard);
        mastercard.setBounds(730, 90, 110, 50);

        pse.setText("pse");
        jPanel3.add(pse);
        pse.setBounds(780, 190, 150, 50);

        jLabel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel3.add(jLabel10);
        jLabel10.setBounds(20, 170, 930, 90);

        efectivo.setText("efectivo");
        jPanel3.add(efectivo);
        efectivo.setBounds(840, 280, 90, 70);

        jLabel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel3.add(jLabel11);
        jLabel11.setBounds(20, 270, 930, 90);

        qr.setText("qr");
        jPanel3.add(qr);
        qr.setBounds(850, 380, 80, 70);

        jLabel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel3.add(jLabel9);
        jLabel9.setBounds(20, 70, 930, 90);

        jLabel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jPanel3.add(jLabel8);
        jLabel8.setBounds(20, 370, 930, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(482, 482, 482)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(restb, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalLab, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sig, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filler4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(restb, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(totalLab, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(sig, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigActionPerformed
        
        if(TotalC!=0)
        {
        desaparecerPag();
        mostrarPag2(jPanel3);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún producto");
        }
    }//GEN-LAST:event_sigActionPerformed

    private void restbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restbActionPerformed
        
        Reiniciar();
    }//GEN-LAST:event_restbActionPerformed

    private void menos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menos1ActionPerformed
        
        menosButton(cant1,One);
        TablaMenos(One);
        
    }//GEN-LAST:event_menos1ActionPerformed

    private void max1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_max1ActionPerformed
        
        maxButton(cant1,One);
        TablaMax(One);
    }//GEN-LAST:event_max1ActionPerformed

    private void max2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_max2ActionPerformed

        maxButton(cant2,Two);
        TablaMax(Two);
    }//GEN-LAST:event_max2ActionPerformed

    private void menos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menos2ActionPerformed
        
        menosButton(cant2,Two);
        TablaMenos(Two);
    }//GEN-LAST:event_menos2ActionPerformed

    private void max3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_max3ActionPerformed
        
        maxButton(cant3,Three);
        TablaMax(Three);
    }//GEN-LAST:event_max3ActionPerformed

    private void menos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menos3ActionPerformed
        
        menosButton(cant3,Three);
        TablaMenos(Three);
    }//GEN-LAST:event_menos3ActionPerformed

    private void max4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_max4ActionPerformed
        
        maxButton(cant4,Four);
        TablaMax(Four);
    }//GEN-LAST:event_max4ActionPerformed

    private void menos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menos4ActionPerformed
        
        menosButton(cant4,Four);
        TablaMenos(Four);
    }//GEN-LAST:event_menos4ActionPerformed

    private void menos5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menos5ActionPerformed
        
        menosButton(cant5,Five);
        TablaMenos(Five);
    }//GEN-LAST:event_menos5ActionPerformed

    private void max5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_max5ActionPerformed
        
        maxButton(cant5,Five);
        TablaMax(Five);
    }//GEN-LAST:event_max5ActionPerformed

    private void max6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_max6ActionPerformed
        
        maxButton(cant6,Six);
        TablaMax(Six);
    }//GEN-LAST:event_max6ActionPerformed

    private void menos6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menos6ActionPerformed
        
        menosButton(cant6,Six);
        TablaMenos(Six);
    }//GEN-LAST:event_menos6ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        desaparecerPag2(jPanel3);
        mostrarPag();
    }//GEN-LAST:event_backActionPerformed

    private void sig2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sig2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sig2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel americane;
    private javax.swing.JButton back;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cant1;
    private javax.swing.JLabel cant2;
    private javax.swing.JLabel cant3;
    private javax.swing.JLabel cant4;
    private javax.swing.JLabel cant5;
    private javax.swing.JLabel cant6;
    private javax.swing.JLabel efectivo;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JLabel img1;
    private javax.swing.JLabel img2;
    private javax.swing.JLabel img3;
    private javax.swing.JLabel img4;
    private javax.swing.JLabel img5;
    private javax.swing.JLabel img6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel mastercard;
    private javax.swing.JButton max1;
    private javax.swing.JButton max2;
    private javax.swing.JButton max3;
    private javax.swing.JButton max4;
    private javax.swing.JButton max5;
    private javax.swing.JButton max6;
    private javax.swing.JButton menos1;
    private javax.swing.JButton menos2;
    private javax.swing.JButton menos3;
    private javax.swing.JButton menos4;
    private javax.swing.JButton menos5;
    private javax.swing.JButton menos6;
    private javax.swing.JLabel nombreP1;
    private javax.swing.JLabel nombreP2;
    private javax.swing.JLabel nombreP3;
    private javax.swing.JLabel nombreP4;
    private javax.swing.JLabel nombreP5;
    private javax.swing.JLabel nombreP6;
    private javax.swing.JLabel prec1;
    private javax.swing.JLabel prec2;
    private javax.swing.JLabel prec3;
    private javax.swing.JLabel prec4;
    private javax.swing.JLabel prec5;
    private javax.swing.JLabel prec6;
    private javax.swing.JLabel pse;
    private javax.swing.JLabel qr;
    private javax.swing.JButton restb;
    private javax.swing.JTable resumen;
    private javax.swing.JButton sig;
    private javax.swing.JButton sig2;
    private javax.swing.JLabel totalLab;
    private javax.swing.JLabel visa;
    // End of variables declaration//GEN-END:variables
}
