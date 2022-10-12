package NS_Labs;

import org.jetbrains.annotations.NotNull;


import javax.swing.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static PlayfairCipher playfairCipher = new PlayfairCipher();

    static CaesorCipher caesorCipher = new CaesorCipher();
    public static void main(String[] args) {
        System.out.println("Enter Message");
        String message = takeInput();
        while(message.equals(""))
            message = takeInput();

        System.out.println("Enter Keyword");
        String key = takeInput();
        while(key.equals(""))
            key = takeInput();

        HashMap<Character, Double> plain_word_frequency = Calculate_Frequency(message);
        String message_temp = message;
        message_temp = message_temp.replace("J", "I");
        String PlayFair = playfairCipher.Playfair(message_temp, key);
        System.out.println("\n-----------------------------------------PlayFair Cipher Encryption-----------------------------------------");
        System.out.println(PlayFair);


        String Vigenere_Cipher = Vigenere.encode(Vigenere.compress(message),Vigenere.expandedKey(key));
        System.out.println("\n-----------------------------------------Vigenere Cipher Encryption-----------------------------------------");
        System.out.println(Vigenere_Cipher);


        String Caesar_Cipher = caesorCipher.encrypt(message);
        System.out.println("-----------------------------------------Caesar Cipher Encryption-----------------------------------------");
        System.out.println(Caesar_Cipher);

        //Calculating Frequency of Letters in Each Cipher.
        HashMap<Character, Double> plafair_frequency = Calculate_Frequency(PlayFair);
        System.out.println("\n-----------------------------------------PlayFair Cipher Frequency-----------------------------------------");
        System.out.println(plafair_frequency);

        HashMap<Character, Double> vigenere_frequency = Calculate_Frequency(Vigenere_Cipher);
        System.out.println("\n-----------------------------------------Vigenere Cipher Frequency-----------------------------------------");
        System.out.println(vigenere_frequency);

        HashMap<Character, Double> Caesar_frequency = Calculate_Frequency(Caesar_Cipher);
        System.out.println("\n-----------------------------------------Caesar Cipher Frequency-----------------------------------------");
        System.out.println(Caesar_frequency);

        System.out.println("\n-----------------------------------------Plain Text Frequency-----------------------------------------");
        System.out.println(plafair_frequency);


        SwingUtilities.invokeLater(() -> {
            LineChart example = new LineChart("Line Chart Example", plafair_frequency, vigenere_frequency, Caesar_frequency, plain_word_frequency);
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(600, 400);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });


    }

    private static @NotNull String takeInput(){
        String parse = scanner.nextLine();
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        return parse;
    }

    private static HashMap<Character, Double> Calculate_Frequency(String text){

        HashMap<Character, Double>  frequency = new HashMap<Character, Double>();
        for(int i=0; i<text.length();i++){
            if(frequency.containsKey(text.charAt(i))){
                frequency.put(text.charAt(i), frequency.get(text.charAt(i))+1);
            }
            else{
                frequency.put(text.charAt(i), 1D);
            }
        }

        //Normalizing the frequency
//        double e_frequency = frequency.get('E');
//            for(Map.Entry<Character, Double> entry: frequency.entrySet()){
//
//                entry.setValue(Double.valueOf(String.format("%.2g%n",entry.getValue()/e_frequency)));
//            }

        return frequency;
    }

}
