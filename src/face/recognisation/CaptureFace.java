package face.recognisation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
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
import java.sql.*;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;

public class CaptureFace extends javax.swing.JFrame {

    public CaptureFace.DaemonThread myThread = null;

    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    CascadeClassifier cascade = new CascadeClassifier("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\owner\\haarcascade_frontalface_alt.xml");
    BytePointer btpointer = new BytePointer();
    RectVector detectedFaces = new RectVector();
    String root, dob, name, email, pwd;
    int sumSample = 25, sample = 1, idPerson;
    MyConnection con = new MyConnection();
 
    public CaptureFace(int userId, String userName,String userEmail,String userDob,String userPwd) {
        try {
            initComponents();
            idPerson = userId;
            name = userName;
            dob = userDob;
            email = userEmail;
            pwd = userPwd;
            startCamera();
            setTitle("Recognizing face ...");
            setDefaultCloseOperation(CaptureFace.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
        } catch (Exception e) {
            System.out.print("check7");
        }
    }

    private CaptureFace() {
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        screen = new javax.swing.JPanel();
        capture = new javax.swing.JButton();
        counter = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Scanning your face ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        screen.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout screenLayout = new javax.swing.GroupLayout(screen);
        screen.setLayout(screenLayout);
        screenLayout.setHorizontalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        screenLayout.setVerticalGroup(
            screenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        capture.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        capture.setText("CAPTURE   (25 SNAPSHOTS)");
        capture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureActionPerformed(evt);
            }
        });

        counter.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        counter.setForeground(new java.awt.Color(0, 0, 153));
        counter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counter.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(screen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(counter, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(capture, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(screen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(counter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(capture, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void captureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureActionPerformed
    }//GEN-LAST:event_captureActionPerformed

    public void capture() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CaptureFace().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton capture;
    private javax.swing.JLabel counter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel screen;
    // End of variables declaration//GEN-END:variables

    public class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = screen.getGraphics();
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

                                if (capture.getModel().isPressed()) {
                                    if (sample <= sumSample) {
                                        String cropped = "D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\owner\\person." + idPerson + "." + sample + ".jpg";
                                        opencv_imgcodecs.imwrite(cropped, face);
                                        counter.setText(String.valueOf(sample));
                                        sample++;
                                    }
                                    if (sample > 25) {
                                        generate();
                                        insertDatabase();
                                        System.out.println("files generated");
                                        stopCamera();
                                    }
                                }
                            }
                            opencv_imgcodecs.imencode(".bmp", cameraImage, btpointer);
                            Image img = ImageIO.read(new ByteArrayInputStream(btpointer.getStringBytes()));
                            BufferedImage buff = (BufferedImage) img;
                            try {
                                if (g.drawImage(buff, 0, 0, 400, 400, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
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
            File directory = new File("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\owner\\");
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
            lbph.save("D:\\Projects\\Face Recognisation\\Face-Recognisation\\photos\\owner\\classifierLBPH.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertDatabase() {
        Connection con = MyConnection.connect();
        try {
            PreparedStatement ps = con.prepareStatement("insert into user_data values (?,?,?,?,?)");
            ps.setInt(1, idPerson);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, dob);
            ps.setString(5, pwd);
            ps.executeUpdate();
            new LoginForm().setVisible(true);
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
                myThread = new CaptureFace.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }
}
