package views;

import controllers.CandidatoController;
import controllers.EmpleoController;
import controllers.PostulacionController;
import controllers.UpPDFController;
import core.services.FileService;
import core.services.ResponseService;
import core.utils.UsuarioThreadLocal;
import core.utils.Util;
import core.utils.Validation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Candidato;
import models.Empleo;
import models.EstudioAcademico;
import models.ExperienciaLaboral;
import models.FiltroEmpleosCandidato;
import models.FiltroPostulaciones;
import models.Postulacion;

public class ViewMenuCandidato extends javax.swing.JFrame {

    Util util = new Util();
    Login login = new Login();
    CandidatoController candidatoController = new CandidatoController();
    EmpleoController empleoController = new EmpleoController();
    PostulacionController postulacionController = new PostulacionController();

    public ViewMenuCandidato() {
        initComponents();
        mostrarDatosBasicos();
        listarEstudiosAcademicos();
        listarExperienciaLaboral();
        listarEmpleosCandidatos();
        listarPostulaciones();
        inicializarComponente();
    }

    final public void mostrarDatosBasicos() {
        txtNombres.setText(UsuarioThreadLocal.get().getCandidato().getNombre());
        txtApellidos.setText(UsuarioThreadLocal.get().getCandidato().getApellidos());
        txtEmail.setText(UsuarioThreadLocal.get().getUsername());
        cbxGenero.setSelectedItem(UsuarioThreadLocal.get().getCandidato().getSexo());
        cbxEstadoCivil.setSelectedItem(UsuarioThreadLocal.get().getCandidato().getEstadoCivil());
        txtAptitudes.setText(UsuarioThreadLocal.get().getCandidato().getAptitudes());
        txtDNI.setText(UsuarioThreadLocal.get().getCandidato().getNroDocumento());
        txtFechaNacimiento.setText(UsuarioThreadLocal.get().getCandidato().getFechaNacimiento());
        txtTelefono.setText(UsuarioThreadLocal.get().getCandidato().getTelefono());
        if (!txtFechaNacimiento.getText().isEmpty()) {
            txtEdad.setText(util.calcularEdad(txtFechaNacimiento.getText()));
        }

    }

    final public void inicializarComponente() {
        btnPostularEmpleos.setEnabled(false);
    }

    final public void listarEstudiosAcademicos() {
        EstudioAcademico estudioAcademico = new EstudioAcademico();
        estudioAcademico.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());

        final ResponseService<List<Object[]>> response = candidatoController.listarEstudiosAcademicos(estudioAcademico);

        if (response.isSuccess()) {
            List<Object[]> items = response.getResult();

            DefaultTableModel modelo = (DefaultTableModel) tblListaEstudiosAcademicos.getModel();
            modelo.setRowCount(0);

            // Agregar los datos al modelo
            for (Object[] item : items) {
                modelo.addRow(item);
            }
        } else {
            util.alertMessage("Error al obtener estudios academicos", true);
        }
    }

    final public void listarExperienciaLaboral() {
        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
        experienciaLaboral.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());

        final ResponseService<List<Object[]>> response = candidatoController.listarExperienciasLaborales(experienciaLaboral);

        if (response.isSuccess()) {
            List<Object[]> items = response.getResult();

            DefaultTableModel modelo = (DefaultTableModel) TablaExperiencia.getModel();
            modelo.setRowCount(0);

            // Agregar los datos al modelo
            for (Object[] item : items) {
                modelo.addRow(item);
            }
        } else {
            util.alertMessage("Error al obtener experiencia laboral", true);
        }
    }

    final public void listarEmpleosCandidatos() {
        FiltroEmpleosCandidato filtroEmpleosCandidato = new FiltroEmpleosCandidato();
        final ResponseService<List<Object[]>> response = empleoController.listarEmpleosCandidatos(filtroEmpleosCandidato);

        if (response.isSuccess()) {
            List<Object[]> items = response.getResult();

            DefaultTableModel modelo = (DefaultTableModel) tblEmpleos.getModel();
            modelo.setRowCount(0);

            // Agregar los datos al modelo
            for (Object[] item : items) {
                modelo.addRow(item);
            }
        } else {
            util.alertMessage("Error al obtener empleos", true);
        }
    }

    final public void listarPostulaciones() {
        Postulacion postulacion = new Postulacion();
        postulacion.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());

        FiltroPostulaciones filtroPostulaciones = new FiltroPostulaciones();
        filtroPostulaciones.setBuscar(txtBuscar.getText());
        final ResponseService<List<Object[]>> response = postulacionController.listarPostulaciones(postulacion, filtroPostulaciones);

        if (response.isSuccess()) {
            List<Object[]> items = response.getResult();

            DefaultTableModel modelo = (DefaultTableModel) tblMisPostulaciones.getModel();
            modelo.setRowCount(0);

            // Agregar los datos al modelo
            for (Object[] item : items) {
                modelo.addRow(item);
            }
        } else {
            util.alertMessage("Error al postulaciones", true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        cbxEstadoCivil = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        cbxEstudios = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        EstudiosFechaInicio = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        EstudiosFechaFin = new javax.swing.JTextField();
        btnRegistrarEstudioAcademico = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtExperienciaEL = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        ExperienciaFechaInicio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ExperienciaFechaFin = new javax.swing.JTextField();
        AgregarExperiencia = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Aptitudes = new javax.swing.JTextField();
        AgregarAptitud = new javax.swing.JButton();
        btnActualizarCandidato = new javax.swing.JButton();
        QuitarExperiencia = new javax.swing.JButton();
        QuitarEstudios = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        btnAgregarCV = new javax.swing.JButton();
        CurriculumArchivo = new javax.swing.JLabel();
        QuitarCurriculum = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        AgregarCertificado = new javax.swing.JButton();
        QuitarCertificado = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblListaEstudiosAcademicos = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        TablaExperiencia = new javax.swing.JTable();
        QuitarAptitud = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcionEL = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAptitudes = new javax.swing.JTextArea();
        txtEdad = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscarMisPostulaciones = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMisPostulaciones = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPostulacionesDescripcion = new javax.swing.JTextArea();
        cbxEstado = new javax.swing.JComboBox<>();
        cbxModalidad = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField15 = new javax.swing.JTextField();
        btnBuscarEmpleos = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblEmpleos = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtEmpleoDescripcion = new javax.swing.JTextArea();
        btnPostularEmpleos = new javax.swing.JButton();
        cbxModalidadEmpleos = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel18.setText("Nombre(s)");

        jLabel19.setText("Apellido(s)");

        jLabel20.setText("Email");

        jLabel21.setText("Celular");

        txtNombres.setBackground(new java.awt.Color(229, 229, 229));

        txtApellidos.setBackground(new java.awt.Color(229, 229, 229));

        txtEmail.setEditable(false);
        txtEmail.setBackground(new java.awt.Color(229, 229, 229));

        txtTelefono.setBackground(new java.awt.Color(229, 229, 229));

        cbxGenero.setBackground(new java.awt.Color(229, 229, 229));
        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--", "M", "F" }));

        jLabel24.setText("DNI");

        jLabel25.setText("Fecha de Nacimiento (DD/MM/YY)");

        jLabel26.setText("Género");

        jLabel27.setText("Estado Civil");

        txtDNI.setBackground(new java.awt.Color(229, 229, 229));

        txtFechaNacimiento.setBackground(new java.awt.Color(229, 229, 229));

        cbxEstadoCivil.setBackground(new java.awt.Color(229, 229, 229));
        cbxEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--", "soltero", "casado", "viudo" }));

        jLabel28.setText("Grado");

        cbxEstudios.setBackground(new java.awt.Color(229, 229, 229));
        cbxEstudios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccionar--", "tecnico", "universitario" }));

        jLabel29.setText("Fecha Inicio");

        EstudiosFechaInicio.setBackground(new java.awt.Color(229, 229, 229));

        jLabel30.setText("Fecha Final");

        EstudiosFechaFin.setBackground(new java.awt.Color(229, 229, 229));

        btnRegistrarEstudioAcademico.setBackground(new java.awt.Color(102, 102, 102));
        btnRegistrarEstudioAcademico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarEstudioAcademico.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarEstudioAcademico.setText("AGREGAR");
        btnRegistrarEstudioAcademico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarEstudioAcademicoActionPerformed(evt);
            }
        });

        jLabel1.setText("Título");

        txtExperienciaEL.setBackground(new java.awt.Color(229, 229, 229));
        txtExperienciaEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExperienciaELActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Inicio");

        ExperienciaFechaInicio.setBackground(new java.awt.Color(229, 229, 229));

        jLabel3.setText("Fecha Final");

        ExperienciaFechaFin.setBackground(new java.awt.Color(229, 229, 229));

        AgregarExperiencia.setBackground(new java.awt.Color(102, 102, 102));
        AgregarExperiencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AgregarExperiencia.setForeground(new java.awt.Color(255, 255, 255));
        AgregarExperiencia.setText("AGREGAR");
        AgregarExperiencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarExperienciaActionPerformed(evt);
            }
        });

        jLabel4.setText("Aptitudes");

        Aptitudes.setBackground(new java.awt.Color(229, 229, 229));

        AgregarAptitud.setBackground(new java.awt.Color(102, 102, 102));
        AgregarAptitud.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AgregarAptitud.setForeground(new java.awt.Color(255, 255, 255));
        AgregarAptitud.setText("AGREGAR");
        AgregarAptitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAptitudActionPerformed(evt);
            }
        });

        btnActualizarCandidato.setBackground(new java.awt.Color(102, 102, 102));
        btnActualizarCandidato.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizarCandidato.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarCandidato.setText("GUARDAR");
        btnActualizarCandidato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCandidatoActionPerformed(evt);
            }
        });

        QuitarExperiencia.setBackground(new java.awt.Color(102, 102, 102));
        QuitarExperiencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        QuitarExperiencia.setForeground(new java.awt.Color(255, 255, 255));
        QuitarExperiencia.setText("QUITAR");

        QuitarEstudios.setBackground(new java.awt.Color(102, 102, 102));
        QuitarEstudios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        QuitarEstudios.setForeground(new java.awt.Color(255, 255, 255));
        QuitarEstudios.setText("QUITAR");
        QuitarEstudios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitarEstudiosActionPerformed(evt);
            }
        });

        jLabel22.setText("Curriculum Vitae");

        btnAgregarCV.setBackground(new java.awt.Color(102, 102, 102));
        btnAgregarCV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarCV.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCV.setText("AGREGAR");
        btnAgregarCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCVActionPerformed(evt);
            }
        });

        CurriculumArchivo.setText("archivo");

        QuitarCurriculum.setBackground(new java.awt.Color(102, 102, 102));
        QuitarCurriculum.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        QuitarCurriculum.setForeground(new java.awt.Color(255, 255, 255));
        QuitarCurriculum.setText("QUITAR");
        QuitarCurriculum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuitarCurriculumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarCV)
                .addGap(18, 18, 18)
                .addComponent(QuitarCurriculum, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CurriculumArchivo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(btnAgregarCV)
                    .addComponent(QuitarCurriculum)
                    .addComponent(CurriculumArchivo))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel23.setText("Certificados");

        AgregarCertificado.setBackground(new java.awt.Color(102, 102, 102));
        AgregarCertificado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AgregarCertificado.setForeground(new java.awt.Color(255, 255, 255));
        AgregarCertificado.setText("AGREGAR");

        QuitarCertificado.setBackground(new java.awt.Color(102, 102, 102));
        QuitarCertificado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        QuitarCertificado.setForeground(new java.awt.Color(255, 255, 255));
        QuitarCertificado.setText("QUITAR");

        tblListaEstudiosAcademicos.setBackground(new java.awt.Color(229, 229, 229));
        tblListaEstudiosAcademicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título", "Descripción", "Fecha Inicio", "Fecha Final", "Grado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblListaEstudiosAcademicos);

        TablaExperiencia.setBackground(new java.awt.Color(229, 229, 229));
        TablaExperiencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título", "Descripción", "Empresa", "Fecha Inicio", "Fecha Final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(TablaExperiencia);

        QuitarAptitud.setBackground(new java.awt.Color(102, 102, 102));
        QuitarAptitud.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        QuitarAptitud.setForeground(new java.awt.Color(255, 255, 255));
        QuitarAptitud.setText("QUITAR");

        jLabel5.setText("Descripción");

        txtDescripcionEL.setBackground(new java.awt.Color(226, 226, 226));

        jLabel6.setText("Título");

        txtTitulo.setBackground(new java.awt.Color(229, 229, 229));

        txtDescripcion.setBackground(new java.awt.Color(229, 229, 229));

        jLabel8.setText("Descripción");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Estudios Académicos");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Experiencia Laboral");

        jLabel10.setText("Empresa");

        txtEmpresa.setBackground(new java.awt.Color(226, 226, 226));

        txtAptitudes.setEditable(false);
        txtAptitudes.setBackground(new java.awt.Color(229, 229, 229));
        txtAptitudes.setColumns(20);
        txtAptitudes.setRows(5);
        jScrollPane2.setViewportView(txtAptitudes);

        txtEdad.setEditable(false);
        txtEdad.setBackground(new java.awt.Color(229, 229, 229));

        jLabel16.setText("Edad");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(Aptitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AgregarAptitud)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(QuitarAptitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(159, 159, 159)
                                .addComponent(jLabel19))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDNI)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel6))
                                .addGap(46, 46, 46)
                                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EstudiosFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(EstudiosFechaFin))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRegistrarEstudioAcademico)
                                .addGap(18, 18, 18)
                                .addComponent(QuitarEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbxEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)
                                .addComponent(jLabel30)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDescripcion)))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AgregarCertificado)
                        .addGap(18, 18, 18)
                        .addComponent(QuitarCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel26)
                                        .addComponent(txtEmail)
                                        .addComponent(cbxGenero, 0, 140, Short.MAX_VALUE))
                                    .addComponent(jLabel20))
                                .addGap(72, 72, 72)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel27)
                                    .addComponent(txtTelefono)
                                    .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56))
                            .addComponent(btnActualizarCandidato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(AgregarExperiencia)
                                .addGap(18, 18, 18)
                                .addComponent(QuitarExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDescripcionEL)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                            .addComponent(txtExperienciaEL))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(ExperienciaFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ExperienciaFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel16))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(cbxEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(EstudiosFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(EstudiosFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtExperienciaEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ExperienciaFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExperienciaFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDescripcionEL, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QuitarExperiencia, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(AgregarExperiencia)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QuitarEstudios)
                            .addComponent(btnRegistrarEstudioAcademico))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QuitarCertificado)
                    .addComponent(AgregarCertificado)
                    .addComponent(jLabel23)
                    .addComponent(QuitarAptitud)
                    .addComponent(AgregarAptitud)
                    .addComponent(Aptitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnActualizarCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73))
        );

        jTabbedPane1.addTab("Mis Datos", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        txtBuscar.setBackground(new java.awt.Color(229, 229, 229));

        btnBuscarMisPostulaciones.setBackground(new java.awt.Color(102, 102, 102));
        btnBuscarMisPostulaciones.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarMisPostulaciones.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarMisPostulaciones.setText("BUSCAR");
        btnBuscarMisPostulaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMisPostulacionesActionPerformed(evt);
            }
        });

        tblMisPostulaciones.setBackground(new java.awt.Color(229, 229, 229));
        tblMisPostulaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cargo", "Empresa", "Sueldo", "Modalidad", "Descripcion", "Estado", "Feedback", "Fecha Creado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMisPostulaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMisPostulacionesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMisPostulaciones);

        txtPostulacionesDescripcion.setBackground(new java.awt.Color(229, 229, 229));
        txtPostulacionesDescripcion.setColumns(20);
        txtPostulacionesDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtPostulacionesDescripcion);

        cbxEstado.setBackground(new java.awt.Color(229, 229, 229));
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todos", "postulado", "cancelado" }));

        cbxModalidad.setBackground(new java.awt.Color(229, 229, 229));
        cbxModalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todos", "remoto", "presencial", "hibrido" }));

        jLabel11.setText("Estado:");

        jLabel12.setText("Modalidad:");

        jLabel13.setText("Cargo o Empresa o Descripción:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbxModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnBuscarMisPostulaciones))
                            .addComponent(jLabel12))))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarMisPostulaciones)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxModalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mis Postulaciones", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jTextField15.setBackground(new java.awt.Color(229, 229, 229));

        btnBuscarEmpleos.setBackground(new java.awt.Color(102, 102, 102));
        btnBuscarEmpleos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarEmpleos.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarEmpleos.setText("BUSCAR");
        btnBuscarEmpleos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarEmpleosActionPerformed(evt);
            }
        });

        tblEmpleos.setBackground(new java.awt.Color(229, 229, 229));
        tblEmpleos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cargo", "Empresa", "Sueldo", "Modalidad", "Descripcion", "Edad Min", "Edad Max", "Fecha Creado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblEmpleos);

        txtEmpleoDescripcion.setBackground(new java.awt.Color(229, 229, 229));
        txtEmpleoDescripcion.setColumns(20);
        txtEmpleoDescripcion.setRows(5);
        jScrollPane7.setViewportView(txtEmpleoDescripcion);

        btnPostularEmpleos.setBackground(new java.awt.Color(102, 102, 102));
        btnPostularEmpleos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPostularEmpleos.setForeground(new java.awt.Color(255, 255, 255));
        btnPostularEmpleos.setText("POSTULAR");
        btnPostularEmpleos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostularEmpleosActionPerformed(evt);
            }
        });

        cbxModalidadEmpleos.setBackground(new java.awt.Color(229, 229, 229));
        cbxModalidadEmpleos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "todos", "remoto", "presencial", "hibrido" }));

        jLabel14.setText("Modalidad:");

        jLabel15.setText("Cargo o Empresa o Descripción:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(cbxModalidadEmpleos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)
                                    .addComponent(btnBuscarEmpleos)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnPostularEmpleos, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(334, 334, 334)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarEmpleos)
                    .addComponent(cbxModalidadEmpleos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPostularEmpleos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empleos", jPanel3);

        jMenuBar1.setBorder(null);

        jMenu1.setText("Cerrar Sesión");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Cerrar Sesión");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        util.centerOnScreen(login, true);
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void QuitarCurriculumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitarCurriculumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuitarCurriculumActionPerformed

    private void QuitarEstudiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuitarEstudiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuitarEstudiosActionPerformed

    private void btnActualizarCandidatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCandidatoActionPerformed

        // Validar
        boolean validateTxtNombres = Validation.validateComponent(txtNombres).required().min(5).max(10).validate();
        boolean validateTxtApellidos = Validation.validateComponent(txtApellidos).required().min(10).max(20).validate();
        boolean validateTxtCelular = Validation.validateComponent(txtTelefono).required().min(9).max(9).validate();
        boolean validateTxtEmail = Validation.validateComponent(txtEmail).required().min(10).max(50).email().validate();
        boolean validateTxtFechaNacimiento = Validation.validateComponent(txtFechaNacimiento).required().min(10).max(10).validateAge().validate();
        boolean validateGenero = Validation.validateComponent(cbxGenero).required(cb -> cb.getSelectedIndex() > 0).validate();
        boolean validateEstadoCivil = Validation.validateComponent(cbxEstadoCivil).required(cb -> cb.getSelectedIndex() > 0).validate();

        if (!validateTxtNombres || !validateTxtApellidos || !validateTxtCelular || !validateTxtEmail || !validateTxtFechaNacimiento || !validateGenero || !validateEstadoCivil) {
            return;
        }

        // Obtener la fecha de nacimiento y calcular la edad
        String fechaNacimiento = txtFechaNacimiento.getText();
        int yearOfBirth = Integer.parseInt(fechaNacimiento.substring(fechaNacimiento.length() - 4));
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        int edad = currentYear - yearOfBirth;

        // Mostrar la edad calculada en el campo txtEdad
        txtEdad.setText(String.valueOf(edad));

        // Actualizar
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String celular = txtTelefono.getText();
        String dni = txtDNI.getText();
        String sexo = cbxGenero.getSelectedItem().toString();
        String estadoCivil = cbxEstadoCivil.getSelectedItem().toString();

        try {
            CandidatoController candidatoController = new CandidatoController();
            Candidato candidato = new Candidato();

            // Candidato
            candidato.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());
            candidato.setIdPersona(UsuarioThreadLocal.get().getIdPersona());
            candidato.setNombre(nombres);
            candidato.setApellidos(apellidos);
            candidato.setTelefono(celular);
            candidato.setNroDocumento(dni);
            candidato.setFechaNacimiento(fechaNacimiento);
            candidato.setSexo(sexo);
            candidato.setEstadoCivil(estadoCivil);

            candidatoController.actualizarCandidato(candidato);
            System.out.println("Candidato actualizado exitosamente.");

            util.alertMessage("actualizado correctamente");
        } catch (Exception e) {
            util.alertMessage("NO SE PUDO GUARDAR TU USUARIO", true);
            throw new RuntimeException(e);
        }


    }//GEN-LAST:event_btnActualizarCandidatoActionPerformed

    private void btnRegistrarEstudioAcademicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarEstudioAcademicoActionPerformed
        // Validar
        boolean validateTxtTitulo = Validation.validateComponent(txtTitulo).required().min(10).max(20).validate();
        boolean validateEstudiosFechaInicio = Validation.validateComponent(EstudiosFechaInicio).required().min(10).max(10).validate();
        boolean validateEstudiosFechaFin = Validation.validateComponent(EstudiosFechaFin).required().min(10).max(10).validate();
        boolean validateCbxEstudios = Validation.validateComponent(cbxEstudios).required(cb -> cb.getSelectedIndex() > 0).validate();
        boolean validateTxtDescripcion = Validation.validateComponent(txtDescripcion).required().min(20).max(200).validate();

        if (!validateTxtTitulo || !validateEstudiosFechaInicio || !validateEstudiosFechaFin || !validateCbxEstudios || !validateTxtDescripcion) {
            return;
        }

        String titulo = txtTitulo.getText();
        String grado = cbxEstudios.getSelectedItem().toString();
        String descripcion = txtDescripcion.getText();
        String fecha_ini = EstudiosFechaInicio.getText();
        String fecha_fin = EstudiosFechaFin.getText();

        EstudioAcademico estudioAcademico = new EstudioAcademico();
        estudioAcademico.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());
        estudioAcademico.setTitulo(titulo);
        estudioAcademico.setGrado(grado);
        estudioAcademico.setDescripcion(descripcion);
        estudioAcademico.setFechaInicio(fecha_ini);
        estudioAcademico.setFechaFin(fecha_fin);

        ResponseService<Boolean> response = candidatoController.registrarEstudioAcademico(estudioAcademico);
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());

        if (response.isSuccess()) {
            util.alertMessage(response.getMessage());
            listarEstudiosAcademicos();
            txtTitulo.setText("");
            cbxEstudios.setSelectedIndex(0);
            txtDescripcion.setText("");
            EstudiosFechaInicio.setText("");
            EstudiosFechaFin.setText("");
        } else {
            util.alertMessage("Error al registrar", true);
        }
    }//GEN-LAST:event_btnRegistrarEstudioAcademicoActionPerformed

    private void AgregarExperienciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarExperienciaActionPerformed

        // Validar
        boolean validateTxtExperienciaEL = Validation.validateComponent(txtExperienciaEL).required().min(10).max(20).validate();
        boolean validateExperienciaFechaInicio = Validation.validateComponent(ExperienciaFechaInicio).required().min(10).max(10).validate();
        boolean validateExperienciaFechaFin = Validation.validateComponent(ExperienciaFechaFin).required().min(10).max(10).validate();
        boolean validateTxtEmpresa = Validation.validateComponent(txtEmpresa).required().min(10).max(35).validate();
        boolean validateTxtDescripcion = Validation.validateComponent(txtDescripcionEL).required().min(20).max(200).validate();

        if (!validateTxtExperienciaEL || !validateExperienciaFechaInicio || !validateExperienciaFechaFin || !validateTxtEmpresa || !validateTxtDescripcion) {
            return;
        }

        String titulo = txtExperienciaEL.getText();
        String empresa = txtEmpresa.getText();
        String fecha_ini = ExperienciaFechaInicio.getText();
        String fecha_fin = ExperienciaFechaFin.getText();
        String descripcion = txtDescripcionEL.getText();

        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
        experienciaLaboral.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());
        experienciaLaboral.setTitulo(titulo);
        experienciaLaboral.setEmpresa(empresa);
        experienciaLaboral.setFechaInicio(fecha_ini);
        experienciaLaboral.setFechaFin(fecha_fin);
        experienciaLaboral.setDescripcion(descripcion);

        ResponseService<Boolean> response = candidatoController.registrarExperienciaLaboral(experienciaLaboral);
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());

        if (response.isSuccess()) {
            util.alertMessage(response.getMessage());
            listarExperienciaLaboral();
            txtExperienciaEL.setText("");
            txtEmpresa.setText("");
            ExperienciaFechaInicio.setText("");
            ExperienciaFechaFin.setText("");
            txtDescripcionEL.setText("");
        } else {
            util.alertMessage("Error al registrar", true);
        }
    }//GEN-LAST:event_AgregarExperienciaActionPerformed

    private void txtExperienciaELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExperienciaELActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExperienciaELActionPerformed

    private void AgregarAptitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarAptitudActionPerformed

    }//GEN-LAST:event_AgregarAptitudActionPerformed

    private void tblEmpleosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleosMouseClicked
        System.out.println("clickeando");

        int fila = tblEmpleos.getSelectedRow();
        System.out.println("La fila es: " + fila);

        if (fila != -1) {
            btnPostularEmpleos.setEnabled(true);
            Object codigo = tblEmpleos.getValueAt(fila, 0);
            Object titulo = tblEmpleos.getValueAt(fila, 1);
            Object empresa = tblEmpleos.getValueAt(fila, 2);
            Object sueldo = tblEmpleos.getValueAt(fila, 3);
            Object modalidad = tblEmpleos.getValueAt(fila, 4);
            Object descripcion = tblEmpleos.getValueAt(fila, 5);
            Object edadMin = tblEmpleos.getValueAt(fila, 6);
            Object edadMax = tblEmpleos.getValueAt(fila, 7);
            Object fechaCreado = tblEmpleos.getValueAt(fila, 8);

            StringBuilder descripcionCompleta = new StringBuilder();
            descripcionCompleta.append("================== DESCRIPCIÓN DEL EMPLEO ==================\n\n");

            descripcionCompleta.append("Titulo:  ").append(titulo != null ? titulo.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Empresa:  ").append(empresa != null ? empresa.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Sueldo:  ").append(sueldo != null ? sueldo.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Modalidad:  ").append(modalidad != null ? modalidad.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Descripción:  ").append(descripcion != null ? descripcion.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Rango de edad permitido:  ").append(edadMin != null && edadMax != null ? edadMin.toString() + " - " + edadMax.toString(): "No disponible").append("\n\n");
            descripcionCompleta.append("Fecha creado:  ").append(fechaCreado != null ? fechaCreado.toString() : "No disponible").append("\n\n");

            txtEmpleoDescripcion.setText(descripcionCompleta.toString());
        }
    }//GEN-LAST:event_tblEmpleosMouseClicked

    private void btnPostularEmpleosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostularEmpleosActionPerformed

        int fila = tblEmpleos.getSelectedRow();
        System.out.println("prueba: " + tblEmpleos.getValueAt(fila, 0));
        System.out.println("prueba: " + tblEmpleos.getValueAt(fila, 0).toString());

        if (fila != -1) {
            Postulacion postulacion = new Postulacion();
            postulacion.setEstado("postulado");
            postulacion.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());
            postulacion.setIdEmpleo(Integer.parseInt(tblEmpleos.getValueAt(fila, 0).toString()));
            postulacion.setEdad(Integer.parseInt(util.calcularEdad(UsuarioThreadLocal.get().getCandidato().getFechaNacimiento())));

            ResponseService<String> response = postulacionController.registrarPostulacion(postulacion);
            System.out.println("Success: " + response.isSuccess());
            System.out.println("Mensaje: " + response.getMessage());
            System.out.println("Resultado: " + response.getResult());

            int totalRowsEstudiosAcademicos = tblListaEstudiosAcademicos.getSelectedRows().length;
            System.out.println("tblListaEstudiosAcademicos Len: " + totalRowsEstudiosAcademicos);
            int totalRowsExperiencias = TablaExperiencia.getSelectedRows().length;
            System.out.println("TablaExperiencia Len: " + totalRowsExperiencias);

            if (totalRowsEstudiosAcademicos > 0 && totalRowsExperiencias > 0) {
                if (response.isSuccess()) {
                    switch (response.getResult()) {
                        case "creado":
                            util.alertMessage("Postulado correctamente");
                            listarPostulaciones();
                            break;
                        case "postulado":
                            util.alertMessage("Usted ya se encuentra postulado a este empleo", true);
                            listarPostulaciones();
                            break;
                        default:
                            util.alertMessage("Error al postular", true);
                            break;
                    }

                } else {
                    util.alertMessage("Error al registrar su postulación", true);
                }
            }else{
               util.alertMessage("Error al registrar su postulación", true); 
            }

        } else {
            // Mostrar mensaje de error si no se ha seleccionado una celda
            util.alertMessage("Porfavor, selecciona un empleo", true);
        }
    }//GEN-LAST:event_btnPostularEmpleosActionPerformed

    private void tblMisPostulacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMisPostulacionesMouseClicked
        System.out.println("clickeando");

        int fila = tblMisPostulaciones.getSelectedRow();
        System.out.println("La fila es: " + fila);

        if (fila != -1) {
            Object titulo = tblMisPostulaciones.getValueAt(fila, 1);
            Object empresa = tblMisPostulaciones.getValueAt(fila, 2);
            Object sueldo = tblMisPostulaciones.getValueAt(fila, 3);
            Object modalidad = tblMisPostulaciones.getValueAt(fila, 4);
            Object descripcion = tblMisPostulaciones.getValueAt(fila, 5);

            StringBuilder descripcionCompleta = new StringBuilder();
            descripcionCompleta.append("================== DESCRIPCIÓN DEL EMPLEO ==================\n\n");
            descripcionCompleta.append("Titulo:  ").append(titulo != null ? titulo.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Empresa:  ").append(empresa != null ? empresa.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Sueldo:  ").append(sueldo != null ? sueldo.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Modalidad:  ").append(modalidad != null ? modalidad.toString() : "No disponible").append("\n\n");
            descripcionCompleta.append("Descripción:  ").append(descripcion != null ? descripcion.toString() : "No disponible").append("\n\n");

            txtPostulacionesDescripcion.setText(descripcionCompleta.toString());
        }
    }//GEN-LAST:event_tblMisPostulacionesMouseClicked

    private void btnBuscarMisPostulacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMisPostulacionesActionPerformed

        Postulacion postulacion = new Postulacion();
        postulacion.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());

        FiltroPostulaciones filtroPostulaciones = new FiltroPostulaciones();
        if (!txtBuscar.getText().isEmpty()) {
            filtroPostulaciones.setBuscar(txtBuscar.getText());
        }
        if (!cbxEstado.getSelectedItem().toString().equals("todos")) {
            filtroPostulaciones.setEstado(cbxEstado.getSelectedItem().toString());
        }
        if (!cbxModalidad.getSelectedItem().toString().equals("todos")) {
            filtroPostulaciones.setModalidad(cbxModalidad.getSelectedItem().toString());
        }

        ResponseService<List<Object[]>> response = postulacionController.listarPostulaciones(postulacion, filtroPostulaciones);
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());

        if (response.isSuccess()) {
            List<Object[]> items = response.getResult();

            DefaultTableModel modelo = (DefaultTableModel) tblMisPostulaciones.getModel();
            modelo.setRowCount(0);

            // Agregar los datos al modelo
            for (Object[] item : items) {
                modelo.addRow(item);
            }
        } else {
            util.alertMessage("Error al postulaciones", true);
        }
    }//GEN-LAST:event_btnBuscarMisPostulacionesActionPerformed

    private void btnAgregarCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCVActionPerformed
        UpPDFController fileController = new UpPDFController();
        ResponseService<String> response = fileController.uploadFile();

        if (response.isSuccess()) {
            CandidatoController candidatoController = new CandidatoController();

            Candidato candidato = new Candidato();
            candidato.setIdCandidato(UsuarioThreadLocal.get().getIdCandidato());
            candidato.setPathCV(response.getResult());

            ResponseService<Boolean> responsito = candidatoController.actualizarCandidatoPorColumna(candidato, "path_curriculum_vitae");
            System.out.println("success: " + responsito.isSuccess());
            System.out.println("Mensaje: " + responsito.getMessage());
            System.out.println("Resultado: " + responsito.getResult());
        } else {

            JOptionPane.showMessageDialog(null, response.getMessage(), "Error en la subida del archivo", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAgregarCVActionPerformed

    private void btnBuscarEmpleosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarEmpleosActionPerformed

        Empleo empleo = new Empleo();
        empleo.setIdEmpleo(UsuarioThreadLocal.get().getIdCandidato());

        FiltroEmpleosCandidato filtroEmpleosCandidato = new FiltroEmpleosCandidato();

        if (!txtBuscar.getText().isEmpty()) {
            filtroEmpleosCandidato.setBuscar(txtBuscar.getText());
        }
        if (!cbxModalidad.getSelectedItem().toString().equals("todos")) {
            filtroEmpleosCandidato.setModalidad(cbxModalidad.getSelectedItem().toString());
        }

        ResponseService<List<Object[]>> response = empleoController.listarEmpleosCandidatos(filtroEmpleosCandidato);
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());

        if (response.isSuccess()) {
            List<Object[]> items = response.getResult();

            DefaultTableModel modelo = (DefaultTableModel) tblEmpleos.getModel();
            modelo.setRowCount(0);

            // Agregar los datos al modelo
            for (Object[] item : items) {
                modelo.addRow(item);
            }
        } else {
            util.alertMessage("Error al empleos", true);
        }

    }//GEN-LAST:event_btnBuscarEmpleosActionPerformed

    private void AgregarCurriculumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCurriculumActionPerformed

    }//GEN-LAST:event_AgregarCurriculumActionPerformed

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
            java.util.logging.Logger.getLogger(ViewMenuCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMenuCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMenuCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMenuCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewMenuCandidato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarAptitud;
    private javax.swing.JButton AgregarCertificado;
    private javax.swing.JButton AgregarExperiencia;
    private javax.swing.JTextField Aptitudes;
    private javax.swing.JLabel CurriculumArchivo;
    private javax.swing.JTextField EstudiosFechaFin;
    private javax.swing.JTextField EstudiosFechaInicio;
    private javax.swing.JTextField ExperienciaFechaFin;
    private javax.swing.JTextField ExperienciaFechaInicio;
    private javax.swing.JButton QuitarAptitud;
    private javax.swing.JButton QuitarCertificado;
    private javax.swing.JButton QuitarCurriculum;
    private javax.swing.JButton QuitarEstudios;
    private javax.swing.JButton QuitarExperiencia;
    private javax.swing.JTable TablaExperiencia;
    private javax.swing.JButton btnActualizarCandidato;
    private javax.swing.JButton btnAgregarCV;
    private javax.swing.JButton btnBuscarEmpleos;
    private javax.swing.JButton btnBuscarMisPostulaciones;
    private javax.swing.JButton btnPostularEmpleos;
    private javax.swing.JButton btnRegistrarEstudioAcademico;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxEstadoCivil;
    private javax.swing.JComboBox<String> cbxEstudios;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JComboBox<String> cbxModalidad;
    private javax.swing.JComboBox<String> cbxModalidadEmpleos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTable tblEmpleos;
    private javax.swing.JTable tblListaEstudiosAcademicos;
    private javax.swing.JTable tblMisPostulaciones;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextArea txtAptitudes;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDescripcionEL;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtEmpleoDescripcion;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtExperienciaEL;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextArea txtPostulacionesDescripcion;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
