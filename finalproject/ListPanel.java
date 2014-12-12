import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.*;

public class ListPanel extends JPanel {

    private JList list;

    public ListPanel(String[] data) {
        super(new BorderLayout());
        list = new JList(data);
    //    list.addListSelectionListener(new SelectionHandler());
        JScrollPane jsp = new JScrollPane(list);
        this.add(jsp, BorderLayout.CENTER);
    }

    // private class SelectionHandler implements ListSelectionListener {

    //     @Override
    //     public void valueChanged(ListSelectionEvent e) {
    //         if (!e.getValueIsAdjusting()) {
    //             System.out.println(Arrays.toString(list.getSelectedValues()));
    //         }
    //     }
    // }

    public void display() {
        JFrame f = new JFrame("Users");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.add(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                String[] data = {"Math", "Computer", "Physics", "Chemistry"};
                new ListPanel(data).display();
            }
        });
    }
}