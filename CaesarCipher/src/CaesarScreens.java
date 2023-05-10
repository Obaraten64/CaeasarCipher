import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CaesarScreens {
    public static void mainScreen(JFrame f) {
        GridBagConstraints gdc = CaesarCodeMain.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        f.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Select between encoding and decoding your text");
        f.add(label, gdc);

        gdc.anchor = GridBagConstraints.WEST;
        gdc.gridy++;
        JButton codeButton = new JButton("Code");
        codeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CaesarScreens.secondScreen(f, "Code");
            }
        });
        f.add(codeButton, gdc);

        gdc.anchor = GridBagConstraints.EAST;
        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CaesarScreens.secondScreen(f, "Decode");
            }
        });
        f.add(decodeButton, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    public static void secondScreen(JFrame f, String chosen) {
        GridBagConstraints gdc = CaesarCodeMain.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel label = new JLabel(((char)0x2193) + " Enter your text here " + ((char)0x2193));
        f.add(label, gdc);

        gdc.gridy++;
        JTextArea input = new JTextArea(1, 20);
        input.setEditable(true);
        f.add(input, gdc);

        //symbols limits
        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                int max = 50;
                if(input.getText().length() > max+1) {
                    e.consume();
                    String shortened = input.getText().substring(0, max);
                    input.setText(shortened);
                }else if(input.getText().length() >= max) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) { }
        });
        //symbols limits

        gdc.gridy++;
        JButton button = new JButton(chosen);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = switch (chosen) {
                    case ("Code") -> CaesarCodeMain.code(input.getText());
                    case ("Decode") -> CaesarCodeMain.decode(input.getText());
                    default -> throw new IllegalStateException("Unexpected value: " + chosen);
                };
                CaesarScreens.thirdScreen(f, str);
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    public static void thirdScreen(JFrame f, String output) {
        GridBagConstraints gdc = CaesarCodeMain.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JTextArea textArea = new JTextArea(1, 20);
        textArea.setText(output);
        textArea.setEditable(false);
        f.add(textArea, gdc);

        gdc.gridy++;
        JButton button = new JButton("Go to main screen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CaesarScreens.mainScreen(f);
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }
}
