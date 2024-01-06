package face.recognisation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class RegisterStudent extends javax.swing.JFrame {

    public RegisterStudent.DaemonThread myThread = null;

    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\students\\haarcascade_frontalface_alt.xml");
    BytePointer btpointer = new BytePointer();
    RectVector detectedFaces = new RectVector();
    String root, dob, name;
    int sumSample = 25, sample = 1, idPerson, attendence, roll;
    MyConnection con = new MyConnection();
    private int userId;
    private String userName, userEmail;

    public RegisterStudent(String ownerName, int ownerId, String ownerEmail) {
        initComponents();
        userId = ownerId;
        userName = ownerName;
        userEmail = ownerEmail;
        setTitle("Student Registration");
        setDefaultCloseOperation(RegisterStudent.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        startCamera();
        rollNum.setText(String.valueOf(random()));
        idPerson = Integer.parseInt(rollNum.getText());
    }

    private RegisterStudent() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        dobField = new javax.swing.JFormattedTextField();
        nameField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        rollNum = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        counter = new javax.swing.JLabel();
        captureScreen = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setForeground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(255, 55, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Student Registration");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Name : ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("DOB : ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Roll Num : ");

        submit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        submit.setText("SUBMIT >>");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        try {
            dobField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dobField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dobFieldActionPerformed(evt);
            }
        });

        nameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        rollNum.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rollNum, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rollNum, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        counter.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        counter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(counter, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(counter, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameField)
                    .addComponent(dobField))
                .addGap(0, 44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dobField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submit)
                .addGap(46, 46, 46))
        );

        captureScreen.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout captureScreenLayout = new javax.swing.GroupLayout(captureScreen);
        captureScreen.setLayout(captureScreenLayout);
        captureScreenLayout.setHorizontalGroup(
            captureScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        captureScreenLayout.setVerticalGroup(
            captureScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(captureScreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(captureScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
    }//GEN-LAST:event_submitActionPerformed

    private void dobFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dobFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dobFieldActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    public int random() {
        Random random = new Random();
        int id = random.nextInt(10000, 99999);
        return id;
    }

    public void register() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegisterStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel captureScreen;
    private javax.swing.JLabel counter;
    private javax.swing.JFormattedTextField dobField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel rollNum;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables

    public class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = captureScreen.getGraphics();
                            Mat imageColor = new Mat();
                            imageColor = cameraImage;

                            Mat imageGray = new Mat();
                            opencv_imgproc.cvtColor(imageColor, imageGray, opencv_imgproc.COLOR_BGRA2GRAY);

                            RectVector detectedFaces = new RectVector();    // face detection 
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) {
                                Rect decodeFace = detectedFaces.get(0);
                                opencv_imgproc.rectangle(imageColor, decodeFace, new Scalar(255, 255, 0, 2), 4, 0, 0);
                                Mat face = new Mat(imageGray, decodeFace);
                                opencv_imgproc.resize(face, face, new Size(160, 160));

                                if (submit.getModel().isPressed()) {
                                    if (sample <= sumSample) {
                                        String cropped = "D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\students\\student." + idPerson + "." + sample + ".jpg";
                                        opencv_imgcodecs.imwrite(cropped, face);
                                        counter.setText(String.valueOf(sample));
                                        sample++;
                                    }
                                    if (sample > 25) {
                                        generate();
                                        System.out.println("files generated");
                                        stopCamera();
                                        insertDatabase();
                                    }
                                }
                            }
                            opencv_imgcodecs.imencode(".bmp", cameraImage, btpointer);
                            Image img = ImageIO.read(new ByteArrayInputStream(btpointer.getStringBytes()));
                            BufferedImage buff = (BufferedImage) img;
                            try {
                                if (g.drawImage(buff, 0, 0, 500, 500, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        System.out.println("save a photo");
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

    public void generate() {
        try {
            File directory = new File("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\students\\");
            FilenameFilter filter = (File dir, String name1) -> name1.endsWith(".jpg") || name1.endsWith(".png");  // lambda method
            File[] files = directory.listFiles(filter);
            MatVector photos = new MatVector(files.length);
            Mat labels = new Mat(files.length, 1, opencv_core.CV_32SC1);
            IntBuffer labelBuffer = labels.createBuffer();
            int counter = 0;
            for (File image : files) {
                Mat photo = opencv_imgcodecs.imread(image.getAbsolutePath(), opencv_imgproc.COLOR_BGRA2GRAY);
                int idP = Integer.parseInt(image.getName().split("\\.")[1]);
                opencv_imgproc.resize(photo, photo, new Size(160, 160));
                photos.put(counter, photo);
                labelBuffer.put(counter, idP);
                counter++;
            }
            LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(1, 8, 8, 8, 12);
            lbph.train(photos, labels);
            lbph.save("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\students\\classifierLBPH.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertDatabase() {
        Connection con = MyConnection.connect();
        try {
            PreparedStatement ps = con.prepareStatement("insert into students_data values (?,?,?,?)");
            roll = Integer.parseInt(rollNum.getText());
            dob = dobField.getText();
            name = nameField.getText();
            attendence = 0;
            ps.setInt(1, roll);
            ps.setString(2, name);
            ps.setString(3, dob);
            ps.setInt(4, attendence);
            ps.executeUpdate();
            new OwnerScreen(userName, userId, userEmail).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                myThread = new RegisterStudent.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
