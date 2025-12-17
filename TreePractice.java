import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TreePractice
{
    private static BinarySearchTree<Character> morseTree =
            new BinarySearchTree<Character>();

    public static void main(String[] args) throws IOException
    {
        // Add root
        morseTree.add('*');

        // Build tree from MORSECODE.txt
        Scanner morseFile = new Scanner(new File("MORSECODE.txt"));
        while (morseFile.hasNext())
        {
            char letter = morseFile.next().charAt(0);
            String code = morseFile.next();
            morseTree.add(letter, 0, code);
        }
        morseFile.close();

        // Decode TESTMORSECODE.txt
        Scanner testFile = new Scanner(new File("TESTMORSECODE.txt"));
        while (testFile.hasNextLine())
        {
            String line = testFile.nextLine().trim();
            if (!line.isEmpty())
            {
                System.out.println("Morse: " + line);
                System.out.println("Text:  " + decodeSentence(line));
                System.out.println();
            }
        }
        testFile.close();
    }

    private static char decodeLetter(String code)
    {
        BinarySearchTreeNode<Character> current = morseTree.getRoot();

        for (int i = 0; i < code.length(); i++)
        {
            if (code.charAt(i) == '.')
                current = current.getLeft();
            else if (code.charAt(i) == '-')
                current = current.getRight();
        }

        return current.getInfo();
    }

    private static String decodeSentence(String sentence)
    {
        StringBuilder decoded = new StringBuilder();
        String[] words = sentence.split("/");

        for (int i = 0; i < words.length; i++)
        {
            String[] letters = words[i].trim().split(" ");
            for (String letter : letters)
                decoded.append(decodeLetter(letter));

            if (i < words.length - 1)
                decoded.append(" ");
        }
        return decoded.toString();
    }
}
