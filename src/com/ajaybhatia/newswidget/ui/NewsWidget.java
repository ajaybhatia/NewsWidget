/*
 * The MIT License
 *
 * Copyright 2014 ajay.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ajaybhatia.newswidget.ui;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author ajay
 */
public class NewsWidget extends javax.swing.JFrame {
    private URL urlTopNews;
    
    /**
     * Creates new form NewsWidget
     */
    public NewsWidget() {
        initComponents();
        placeAtRightCorner();
        initNews(topNewsTab, "http://feeds.reuters.com/reuters/INtopNews");
    }
    
    private void placeAtRightCorner() {
        // Scale from right (Right Edge Gutter)
        final int SCALE_X = 50;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int)rect.getMaxX() - getWidth() - SCALE_X;
        int y = 70;
        setLocation(new Point(x, y));
    }

    private void initNews(JPanel panel, String uri) {
        try {
            urlTopNews = new URL(uri);
        } catch (MalformedURLException ex) {
            Logger.getLogger(NewsWidget.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<SyndEntry> newsList = topNewsList();
        panel.setLayout(new GridLayout(newsList.size() * 2, 1));
        
        newsList.stream().forEach((SyndEntry news) -> {
            JLabel newsLabel = new JLabel("<HTML><U>" + news.getTitle() + "</U></HTML>");
            newsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            newsLabel.setForeground(Color.blue);
            newsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            newsLabel.addMouseListener(new MouseInputAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() > 0) {
                        if (Desktop.isDesktopSupported()) {
                            try {
                                Desktop desktop = Desktop.getDesktop();
                                URI uri = new URI(news.getUri());
                                desktop.browse(uri);
                            } catch (URISyntaxException | IOException ex) {
                                Logger.getLogger(NewsWidget.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            });
            panel.add(newsLabel);
            panel.add(new JLabel(""));
        });
    }
    
    private List<SyndEntry> topNewsList() {
        SyndFeedInput feedInput = new SyndFeedInput();
        try {
            SyndFeed syndFeed = feedInput.build(new XmlReader(urlTopNews));
            return syndFeed.getEntries();
        } catch (IllegalArgumentException | FeedException | IOException ex) {
            Logger.getLogger(NewsWidget.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        topNewsScrollPane = new javax.swing.JScrollPane();
        topNewsTab = new javax.swing.JPanel();
        businessNewsScrollPane = new javax.swing.JScrollPane();
        businessNewsTab = new javax.swing.JPanel();
        technologyNewsScrollPane = new javax.swing.JScrollPane();
        technologyNewsTab = new javax.swing.JPanel();
        worldNewsjScrollPane = new javax.swing.JScrollPane();
        worldNewsTab = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        topNewsTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                topNewsTabFocusGained(evt);
            }
        });

        javax.swing.GroupLayout topNewsTabLayout = new javax.swing.GroupLayout(topNewsTab);
        topNewsTab.setLayout(topNewsTabLayout);
        topNewsTabLayout.setHorizontalGroup(
            topNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        topNewsTabLayout.setVerticalGroup(
            topNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        topNewsScrollPane.setViewportView(topNewsTab);

        tabbedPane.addTab("Top News", topNewsScrollPane);

        javax.swing.GroupLayout businessNewsTabLayout = new javax.swing.GroupLayout(businessNewsTab);
        businessNewsTab.setLayout(businessNewsTabLayout);
        businessNewsTabLayout.setHorizontalGroup(
            businessNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        businessNewsTabLayout.setVerticalGroup(
            businessNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        businessNewsScrollPane.setViewportView(businessNewsTab);

        tabbedPane.addTab("Business", businessNewsScrollPane);

        javax.swing.GroupLayout technologyNewsTabLayout = new javax.swing.GroupLayout(technologyNewsTab);
        technologyNewsTab.setLayout(technologyNewsTabLayout);
        technologyNewsTabLayout.setHorizontalGroup(
            technologyNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        technologyNewsTabLayout.setVerticalGroup(
            technologyNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        technologyNewsScrollPane.setViewportView(technologyNewsTab);

        tabbedPane.addTab("Technology", technologyNewsScrollPane);

        javax.swing.GroupLayout worldNewsTabLayout = new javax.swing.GroupLayout(worldNewsTab);
        worldNewsTab.setLayout(worldNewsTabLayout);
        worldNewsTabLayout.setHorizontalGroup(
            worldNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        worldNewsTabLayout.setVerticalGroup(
            worldNewsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        worldNewsjScrollPane.setViewportView(worldNewsTab);

        tabbedPane.addTab("World", worldNewsjScrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topNewsTabFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_topNewsTabFocusGained
        
    }//GEN-LAST:event_topNewsTabFocusGained

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
            java.util.logging.Logger.getLogger(NewsWidget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewsWidget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewsWidget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewsWidget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NewsWidget().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane businessNewsScrollPane;
    private javax.swing.JPanel businessNewsTab;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JScrollPane technologyNewsScrollPane;
    private javax.swing.JPanel technologyNewsTab;
    private javax.swing.JScrollPane topNewsScrollPane;
    private javax.swing.JPanel topNewsTab;
    private javax.swing.JPanel worldNewsTab;
    private javax.swing.JScrollPane worldNewsjScrollPane;
    // End of variables declaration//GEN-END:variables
}
