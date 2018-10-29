import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main extends JFrame {
  private EmbeddedMediaListPlayerComponent mediaPlayer;
  private JToggleButton btnMute;
  private JButton btnPause;
  private JButton btnPlay;
  private JButton btnStop;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JPanel jPanel4;
  private JSlider sldProgress;
  private JSlider sldVolume;

  public static void main(final String[] args) {
    new NativeDiscovery().discover();
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Main().setVisible(true);
      }
    });
  }

  private void initUI() {
    GridBagConstraints gridBagConstraints;
    jPanel1 = new JPanel();
    jPanel3 = new JPanel();
    sldProgress = new JSlider();
    jPanel4 = new JPanel();
    btnPlay = new JButton();
    btnPause = new JButton();
    btnStop = new JButton();
    btnMute = new JToggleButton();
    sldVolume = new JSlider();
    jPanel2 = new JPanel();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jPanel1.setLayout(new BorderLayout());
    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.LINE_AXIS));
    jPanel3.add(sldProgress);
    jPanel1.add(jPanel3, BorderLayout.NORTH);
    jPanel4.setLayout(new GridBagLayout());
    btnPlay.setText("Play");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel4.add(btnPlay, gridBagConstraints);
    btnPause.setText("Pause");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel4.add(btnPause, gridBagConstraints);
    btnStop.setText("Stop");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel4.add(btnStop, gridBagConstraints);
    btnMute.setText("Mute");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel4.add(btnMute, gridBagConstraints);
    sldVolume.setPreferredSize(new Dimension(100, 23));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
    jPanel4.add(sldVolume, gridBagConstraints);
    jPanel1.add(jPanel4, BorderLayout.CENTER);
    getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel2.setBackground(new Color(64, 64, 64));
    jPanel2.setMinimumSize(new Dimension(100, 100));
    jPanel2.setPreferredSize(new Dimension(400, 300));
    jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.LINE_AXIS));
    getContentPane().add(jPanel2, BorderLayout.CENTER);
    pack();
  }

  public Main() {
    File file = new File("C:\\Videos\\ProgressMeeting.mp4");
    initUI();
    setTitle("Media Player :AT08:");
    setLocationRelativeTo(null);
    mediaPlayer = new EmbeddedMediaListPlayerComponent();
    jPanel2.add(mediaPlayer);
    mediaPlayer.setSize(jPanel2.getSize());
    mediaPlayer.setVisible(true);
    sldVolume.setMinimum(0);
    sldVolume.setMaximum(100);
    sldProgress.setMinimum(0);
    sldProgress.setMaximum(100);
    sldProgress.setValue(0);
    sldProgress.setEnabled(false);

    btnPlay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (file != null) {
          mediaPlayer.getMediaPlayer().playMedia(file.getAbsolutePath());
          sldVolume.setValue(mediaPlayer.getMediaPlayer().getVolume());
          sldProgress.setEnabled(true);
          setTitle(file.getName() + " - Media Player :AT08:");
        }
      }
    });

    btnPause.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        mediaPlayer.getMediaPlayer().pause();
      }
    });

    btnStop.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        mediaPlayer.getMediaPlayer().stop();
        sldProgress.setValue(0);
        sldProgress.setEnabled(false);
        setTitle("Media Player :AT08:");
      }
    });

    btnMute.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
        mediaPlayer.getMediaPlayer().mute(abstractButton.getModel().isSelected());
      }
    });

    sldVolume.addChangeListener(new ChangeListener(){

      @Override
      public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        mediaPlayer.getMediaPlayer().setVolume(((JSlider) source).getValue());
      }
    });
  }
}