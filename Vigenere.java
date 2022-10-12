package NS_Labs;

public class Vigenere {

    private static String plainText;
    public Vigenere(){}

    //Method which compresses/lowercase the passed message
    public static String compress(String text) {
        plainText = text.replaceAll("\\s+","").toLowerCase();
        return plainText;
    }

    //This method is responsible for expanding the key having length equal to message length
    public static String expandedKey(String text){

        StringBuffer buffer = new StringBuffer();
        text = text.replaceAll("\\s+","").toLowerCase();
        for(int i=0; buffer.length() < plainText.length(); i++){
            i = i%text.length();
            buffer.append(text.charAt(i));
        }
        return buffer.toString().toUpperCase();
    }

    //Method for encrypting the message
    public static String encode(String message, String key){
       if(plainText == null){
           System.out.println("Please first compress the message using compress() method");
       }
        StringBuffer buffer = new StringBuffer();
       message = message.toUpperCase();
        for(int i=0; i<message.length(); i++){
            int x= ((message.charAt(i)+key.charAt(i) -2 * 'A')%26 + 'A');
            buffer.append((char)(x));
        }
        return buffer.toString().toUpperCase().replace("\\s+","");
    }

    //For decoding the message using the entered Key
    public static String decode(String codedText, String key){
        StringBuffer buffer = new StringBuffer();
        codedText = codedText.replaceAll("\\s+","").toLowerCase();
        for(int i=0; i<codedText.length() && i< key.length(); i++){
            int x = (codedText.charAt(i) - key.charAt(i) + 26 ) % 26;
            x+='a';
            buffer.append((char)(x));
        }
        return buffer.toString().replaceAll("[^A-Za-z]+","").toUpperCase();
    }
}
