import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class Main {
  private JFrame frame;
  private EmbeddedMediaListPlayerComponent mediaPlayer;

  public static void main(final String[] args) {
    new NativeDiscovery().discover();
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Main();
      }
    });
  }

  public Main() {
    frame = new JFrame("Media Player :AT08:");
    mediaPlayer = new EmbeddedMediaListPlayerComponent();
    mediaPlayer.setPreferredSize(new Dimension(426, 240));
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());
    panel.add(mediaPlayer);

    JLabel label = new JLabel("This is a label!");

    JButton button = new JButton();
    button.setText("Press me");

    panel.add(label);
    panel.add(button);

    frame.add(panel);
    frame.setSize(490, 250);

    //frame.setContentPane(mediaPlayer);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    mediaPlayer.getMediaPlayer().playMedia(Paths.get("C:\\Videos\\Test.mp4").toUri().toString());
  }
}
