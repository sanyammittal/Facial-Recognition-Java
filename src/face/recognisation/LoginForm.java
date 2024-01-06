package face.recognisation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class LoginForm extends javax.swing.JFrame {

    private LoginForm.DaemonThread myThread = null;
    private String name, email;

    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\owner\\haarcascade_frontalface_alt.xml");
    LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
    BytePointer mem = new BytePointer();
    RectVector detectedFaces = new RectVector();
    int idPerson;
    Timer tt = null;

    public LoginForm() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Login Page");
        setDefaultCloseOperation(LoginForm.EXIT_ON_CLOSE);
        recognizer.read("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\owner\\classifierLBPH.yml");
        recognizer.setThreshold(80);
        startCamera();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        submitBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        signUpBtn = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        uid = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pwd = new javax.swing.JLabel();
        screen = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("User ID : ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Password : ");

        submitBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        submitBtn.setText("Submit >>");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Don't have any Account ???");

        signUpBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        signUpBtn.setForeground(new java.awt.Color(0, 0, 204));
        signUpBtn.setText("SignUp");
        signUpBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signUpBtnMouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18))
        );

        uid.setBackground(new java.awt.Color(255, 255, 255));
        uid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(uid, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uid, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );

        pwd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pwd, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(263, 263, 263))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(signUpBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(47, 47, 47)
                .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(signUpBtn))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        screen.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout screenLayout = new javax.swing.GroupLayout(screen);
        screen.setLayout(screenLayout);
        screenLayout.setHorizontalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );
        screenLayout.setVerticalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(screen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(screen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        try {
            Connection con = MyConnection.connect();
            PreparedStatement ps = con.prepareStatement("select * from user_data where user_id=? and pwd=?");
            ps.setInt(1, Integer.parseInt(uid.getText()));
            ps.setString(2, pwd.getText());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                stopCamera();
                dispose();
                new OwnerScreen(name, Integer.parseInt(uid.getText()), email).setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "face not matched..");
//                e.printStackTrace();
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void signUpBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signUpBtnMouseClicked
        stopCamera();
        dispose();
        new SignUp().setVisible(true);
    }//GEN-LAST:event_signUpBtnMouseClicked

    public void Login() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel pwd;
    private javax.swing.JPanel screen;
    private javax.swing.JLabel signUpBtn;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel uid;
    // End of variables declaration//GEN-END:variables

    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);

                            Graphics g = screen.getGraphics();
                            Mat imageGray = new Mat();
                            opencv_imgproc.cvtColor(cameraImage, imageGray, opencv_imgproc.COLOR_BGRA2GRAY);

                            RectVector detectedFace = new RectVector();
                            cascade.detectMultiScale(imageGray, detectedFace, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFace.size(); i++) {
                                Rect dadosFace = detectedFace.get(i);
                                opencv_imgproc.rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = -1;
                                prediction = rotulo.get(0);
                                System.out.println("Prediction : " + prediction);
                                if (prediction == -1) {
                                    opencv_imgproc.rectangle(cameraImage, dadosFace, new Scalar(0, 0, 255, 3), 3, 0, 0);
                                    uid.setText("Miss Match >>");
                                    pwd.setText("Miss Match >>");
                                    idPerson = 0;

                                } else {
                                    opencv_imgproc.rectangle(cameraImage, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                    System.out.println(confidence.get(0));
                                    idPerson = prediction;
                                    System.out.println("ID of person is : " + idPerson);
                                    rec(idPerson);
                                }
                            }

                            opencv_imgcodecs.imencode(".bmp", cameraImage, mem);
                            Image img = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) img;

                            try {
                                if (g.drawImage(buff, 0, 0, 360, 390, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private void rec(int idP) {
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    Connection con = MyConnection.connect();
                    PreparedStatement ps = con.prepareStatement("select * from user_data where user_id=?");
                    ps.setInt(1, idP);
                    uid.setText(String.valueOf(idP));
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        pwd.setText(rs.getString(5));
                        name = rs.getString(2);
                        email = rs.getString(3);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "face not matched");
                }
                return null;
            }
        };
        worker.execute();
    }

    public void stopCamera() {
        myThread.runnable = false;
        webSource.release();
        dispose();
    }

    public void startCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);
                myThread = new LoginForm.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
