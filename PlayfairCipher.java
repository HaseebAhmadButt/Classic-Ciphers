package NS_Labs;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PlayfairCipher {

//    private char[][] matrix_encryption= new char[5][5];
//    private List<Character> chr;
//    private String Message;
//    private String  key;
//
//    public PlayfairCipher(String message, String key) {
//        Message = message;
//        this.key = key;
//    }
//
//    public void key_filling(String enc_key){
//        String Upper_key = enc_key.toUpperCase();
//        chr = new ArrayList<>();
//        // HashSet doesn't allow repetition of elements
//        for(int i=0; i<Upper_key.length(); i++){
//            if(chr.contains(Upper_key.charAt(i)))
//                continue;
//            chr.add(Upper_key.charAt(i));
//        }
//        System.out.println(chr);
//        // Filling Matrix with Keyword Values
//        int counter = 0;
//        int j=0,k=0;
//        for(;j<5;j++){
////            System.out.println(j);
//            if(counter == chr.size())break;
//            if(k >= 4) k=0;
//            for(;k<5; k++){
////                System.out.println("Inner Loop: "+k);
//                this.matrix_encryption[j][k] = chr.get(counter);
//                counter = counter+1;
//                if(counter == chr.size()) break;
//            }
//        }
////        System.out.println(j+" "+k);
//        if(chr.size()%5!=0){
//            j=j-1;
//            if(k==0)k=k+1;
//        }
//        for(char[] array:matrix_encryption){
//            System.out.println(array);
//        }
//        System.out.println(j+" "+k);
////                          0,  1,  2S, 3,  4S, 5,  6,  7,
//        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//        counter = 0;
//        for(; j<5; j++){
//            if(k >= 4) k=0;
//            for(; k<5; k++){
//                if(counter > 25) break;
//                if (chr.contains(Character.toUpperCase(alphabet[counter])) || alphabet[counter]=='j'){
//                    counter=counter+1;
//                    while (true){
//                        if (chr.contains(Character.toUpperCase(alphabet[counter])))counter=counter+1;
//                        else break;
//                    }
//                }
//                matrix_encryption[j][k]=Character.toUpperCase(alphabet[counter]);
//                counter = counter+1;
//            }
//        }
//
//        for(char[] array:matrix_encryption){
//        System.out.println(array);
//
//        }
//    }
//
//private List<Character> chr;

//private String[][] cipherTable(String key){
//    String[][] playfairTable = new String[5][5];
//    chr = new ArrayList<>();
//    String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
//    for(int i = 0; i < keyString.length(); i++){
//        if(chr.contains(keyString.charAt(i))) continue;
//        chr.add(keyString.charAt(i));
//    }
//    int k=0;
//    for(int i = 0; i < 5; i++){
//        for(int j = 0; j < 5; j++){
//            playfairTable[i][j] = "" + chr.get(k);
//            k = k + 1;
//        }
//    }
//    this.printTable(playfairTable);
//    return playfairTable;
//}


    // length of digraph array
    private int length = 0;

    // table for Playfair cipher
    private String [][] table;

    private List<Character> chr;

    // main method to test Playfair method
    public static void main(String[] args){
        PlayfairCipher pf = new PlayfairCipher();
//        pf.Playfair();
    }

    // main run of the program, Playfair method
    public String Playfair(String message, String key){
        table = this.cipherTable(key);
        this.printTable(table);
//        String decodedOutput = decode(output);

        // encodes and then decodes the encoded message
        return cipher(message);

    }

    // parses any input string to remove numbers, punctuation,
    // replaces any J's with I's, and makes string all caps
    private String parseString(Scanner s){
        String parse = s.nextLine();
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }
    private String[][] cipherTable(String key){
    String[][] playfairTable = new String[5][5];
    chr = new ArrayList<>();
    String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    for(int i = 0; i < keyString.length(); i++){
        if(chr.contains(keyString.charAt(i))) continue;
        chr.add(keyString.charAt(i));
    }
    int k=0;
    for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
            playfairTable[i][j] = "" + chr.get(k);
            k = k + 1;
        }
    }
    return playfairTable;
    }
        // cipher: takes input (all upper-case), encodes it, and returns output
    private String cipher(String in){
        length = (int) in.length() / 2 + in.length() % 2;

        // insert x between double-letter digraphs & redefines "length"
        for(int i = 0; i < (length - 1); i++){
            if(in.charAt(2 * i) == in.charAt(2 * i + 1)){
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }

        // adds an x to the last digraph, if necessary
        String[] digraph = new String[length];
        for(int j = 0; j < length ; j++){
            if(j == (length - 1) && in.length() / 2 == (length - 1))
                in = in + "X";
            digraph[j] = in.charAt(2 * j) +""+ in.charAt(2 * j + 1);
        }

        // encodes the digraphs and returns the output
        String out = "";
        String[] encDigraphs = new String[length];
        encDigraphs = encodeDigraph(digraph);
        for(int k = 0; k < length; k++)
            out = out + encDigraphs[k];
        return out;
    }

    // encodes the digraph input with the cipher's specifications
    private String[] encodeDigraph(String di[]){
        String[] enc = new String[length];
        for(int i = 0; i < length; i++){
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            // case 1: letters in digraph are of same row, shift columns to right
            if(r1 == r2){
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;

                // case 2: letters in digraph are of same column, shift rows down
            }else if(c1 == c2){
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;

                // case 3: letters in digraph form rectangle, swap first column # with second column #
            }else{
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            //performs the table look-up and puts those values into the encoded array
            enc[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return enc;
    }

    // decodes the output given from the cipher and decode methods (opp. of encoding process)
    private String decode(String out){
        String decoded = "";
        for(int i = 0; i < out.length() / 2; i++){
            char a = out.charAt(2*i);
            char b = out.charAt(2*i+1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();
            if(r1 == r2){
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            }else if(c1 == c2){
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            }else{
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decoded = decoded + table[r1][c1] + table[r2][c2];
        }
        return decoded;
    }

    // returns a point containing the row and column of the letter
    private Point getPoint(char c){
        Point pt = new Point(0,0);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(c == table[i][j].charAt(0))
                    pt = new Point(i,j);
        return pt;
    }

    // prints the cipher table out for the user
    private void printTable(String[][] printedTable){
        System.out.println("This is the cipher table from the given keyword.");
        System.out.println();

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(printedTable[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // prints results (encoded and decoded)

}
