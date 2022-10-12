package NS_Labs;

public class CaesorCipher {

    public CaesorCipher(){}

    public String encrypt(String  plainMessage) {
        StringBuffer result= new StringBuffer();
        //traverse text

        String planMessage = plainMessage.toUpperCase();
        for(int i=0;i<planMessage.length();i++)
        {
            //apply transformation to each character
            char ch = (char)(((int)planMessage.charAt(i) +
                    2 - 65) % 26 + 65);
            result.append(ch);
        }
        //Return the resulting string
        return result.toString();
    }

    public String decrypt(String CipherText) {

        StringBuffer result= new StringBuffer();
        //traverse text
        String Ciphertext = CipherText.toUpperCase();
        for(int i=0;i<Ciphertext.length();i++)
        {
            //apply transformation to each character
            char ch = (char)(((int)CipherText.charAt(i) -
                    2 - 65) % 26 + 65);
            result.append(ch);
        }
        return result.toString();
    }


}
