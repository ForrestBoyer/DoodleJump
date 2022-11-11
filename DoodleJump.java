import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class DoodleJump extends JFrame
{
  public DoodleJump()
  {
    super("Doodle Jump");

    DoodlePanel game = new DoodlePanel();
    
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    panel.add((JPanel)game, BorderLayout.CENTER);

    Container c = getContentPane();
    c.add(panel, BorderLayout.CENTER);
  }

  public static void main(String[] args)
  {
    DoodleJump window = new DoodleJump();
    window.setBounds(0, 0, 500, 700);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setVisible(true);
  }
}