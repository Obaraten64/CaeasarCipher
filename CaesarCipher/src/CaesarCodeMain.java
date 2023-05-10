import javax.swing.*;

import java.awt.*;

public class CaesarCodeMain {
    static final int key = 4;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Caesar's code");
                f.setLocation(750, 250);
                f.setSize(new Dimension(500, 500));
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.setVisible(true);
                CaesarScreens.mainScreen(f);
            }
        });
    }

    public static GridBagConstraints initializeInterface() {
        GridBagConstraints gdc = new GridBagConstraints();
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.insets = new Insets(8, 8, 8, 8);
        return gdc;
    }

    public static String code(String str) {
        StringBuilder outputStr = new StringBuilder();
        int symbNumber = 0;
        for (char symb : str.toCharArray()) {
            if (symb == ' ') {
                symbNumber = 'a' - 1 + key;
                outputStr.append((char) symbNumber);
            } else if (!Character.isWhitespace(symb)) {
                if ((symb >= 'A' && symb <= 'Z') || (symb >= 'a' && symb <= 'z')) {
                    symb = Character.toLowerCase(symb);
                    symbNumber = symb + key;
                    if (symbNumber > 'z') {
                        symbNumber = symbNumber - 'z' - 2 + 'a';
                        symbNumber = symbNumber == ('a' - 1)? ' ' : symbNumber;
                    }
                    outputStr.append((char) symbNumber);
                } else {
                    return "An unknown character was entered";
                }
            } else {
                return "An unknown character was entered";
            }
        }
        return outputStr.toString();
    }

    public static String decode(String str) {
        StringBuilder outputStr = new StringBuilder();
        int symbNumber = 0;
        for (char symb : str.toCharArray()) {
            if (symb == ' ') {
                symbNumber = 'z' + 1 - key;
                outputStr.append((char) symbNumber);
            } else if (!Character.isWhitespace(symb)) {
                if ((symb >= 'A' && symb <= 'Z') || (symb >= 'a' && symb <= 'z')) {
                    symb = Character.toLowerCase(symb);
                    symbNumber = symb - key;
                    if (symbNumber < 'a') {
                        symbNumber = symbNumber - 'a' + 2 + 'z' ;
                        symbNumber = symbNumber == ('z' + 1)? ' ' : symbNumber;
                    }
                    outputStr.append((char) symbNumber);
                } else {
                    return "An unknown character was entered";
                }
            } else {
                return "An unknown character was entered";
            }
        }
        return outputStr.toString();
    }
}
