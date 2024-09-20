import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@SuppressWarnings("ALL")
public class BSRandomizer extends JFrame {
    private final JCheckBox[] checkBoxes;
    private final JLabel nameLabel;

    private final Map<Integer, String> nameMap;

    public BSRandomizer() {
        setTitle("BrawlStars Randomizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        //cocm

        //Map
        nameMap = new HashMap<>();
        String[] names = {
                "Shelly", "Colt", "Bull", "Brock", "Spike", "Barley", "Jessie", "Dynamike",
                "El Primo", "Mortis", "Poco", "Bo", "Piper", "Tara", "Darryl", "Penny",
                "Gene", "Tick", "Leon", "Carl", "Bibi", "8-Bit", "Bea", "Emz", "Mr.P",
                "Jacky", "Gale", "Nani", "Surge", "Colette", "Amber", "Byron", "Edgar",
                "Ruffs", "Belle", "Squeak", "Grom", "Griff", "Ash", "Meg", "Fang",
                "Eve", "Janet", "Otis", "Sam", "Gus", "Chester", "Gray", "Mandy",
                "Willow", "Maisie", "Hank", "Doug", "Pearl", "Chuck", "Mico", "Kit",
                "Larry-Lawrie", "Angelo", "Draco", "Lily", "Melodie", "Charlie",
                "Cordelius", "R-T", "Buster", "Bonnie", "Lola", "Buzz", "Stu",
                "Lou", "Sprout", "Max", "Sandy", "Rosa", "Frank", "Pam", "Crow",
                "Nita", "Rico"
        };
        for (int i = 0; i < names.length; i++) {
            nameMap.put(i+1, names[i]);
        }

        // CheckBox Panel :O
        JPanel checkBoxPanel = new JPanel(new GridLayout(10, 8));
        checkBoxes = new JCheckBox[80];
        for (int i = 0; i < 80; i++) {
            int bNum = i + 1;
            checkBoxes[i] = new JCheckBox();
            checkBoxes[i].setIcon(new ImageIcon("resources/Keep/image_" + bNum + ".png"));
            checkBoxes[i].setSelectedIcon(new ImageIcon("resources/NotKeep/image_" + bNum + ".png"));

            checkBoxes[i].addItemListener(_ -> {
            });
            checkBoxes[i].setActionCommand(String.valueOf(bNum));
            checkBoxPanel.add(checkBoxes[i]);
        }

        JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrollPane, BorderLayout.CENTER);

        // Knopf zum random number generator dings
        JButton generateButton = getjButton();
        add(generateButton, BorderLayout.SOUTH);

        // nameLabel zum Anzeigen von dem Text oben
        nameLabel = new JLabel("Drücke den Knopf um einen zufälligen Char zu bekommen", SwingConstants.CENTER);
        add(nameLabel, BorderLayout.NORTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton getjButton() {
        JButton generateButton = new JButton("Generiere einen zufälligen Charakter");
        final int[] prevNum = {-1};
        generateButton.addActionListener(_ -> {
            int randomNumber;
            do {
                randomNumber = generateRandomNumber();
            } while (checkBoxes[randomNumber - 1].isSelected());
            String name = nameMap.get(randomNumber);
            if (name != null) {
                System.out.println(randomNumber);
                nameLabel.setText("Letzter Char: "+name);
                if (prevNum[0] != -1) {
                    checkBoxes[prevNum[0] - 1].setIcon(new ImageIcon("resources/Keep/image_" + (prevNum[0]) + ".png"));
                }
                checkBoxes[randomNumber - 1].setIcon(new ImageIcon("resources/EndPicture/image_"+randomNumber+".png"));
                prevNum[0] = randomNumber;
            } else {
                nameLabel.setText("Kein Name für " + randomNumber+ " gefunden (Fehler) ");
            }
        });
        return generateButton;
    }

    private int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(80) + 1;
    }

    public static void main(String[] BSRandom) {
        SwingUtilities.invokeLater(BSRandomizer::new);
    }
}