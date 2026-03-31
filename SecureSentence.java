public class SecureSentence {

    private String sentence;     // stores the current sentence (plain or encrypted)
    private Key keyUsed;         // the key used for the last encryption/decryption
    private boolean encrypted;   // true if the sentence is currently encrypted

    // Default constructor: starts with empty plain sentence
    public SecureSentence() {
        sentence = "";
        keyUsed = null;
        encrypted = false;
    }

    // Store a normal (plain) sentence with no key
    public void setSentence(String s) {
        sentence = s;
        encrypted = false;   // the sentence is not encrypted
        keyUsed = null;      // no key used
    }

    // Store a sentence that is already encrypted using a specific key
    public void setSentence(String s, Key key) {
        sentence = s;
        encrypted = true;    // the sentence is marked as encrypted
        keyUsed = key;       // store the key used
    }

    //  ENCRYPT 
    public void encrypt(Key key) {

        // If the sentence is already encrypted, we cannot encrypt again
        if (encrypted == true) {
            System.out.println("The sentence has been encrypted already.");
            return;
        }

        // If the key is not set (or is null), encryption cannot happen
        if (key == null || !key.getIsSet()) {
            System.out.println("There is no key set yet.");
            return;
        }

        String encStr = "";   // this will store the new encrypted sentence
        boolean isFound;      // to check if a character exists in the key

        // Go through each character in the sentence
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            isFound = false;

            // Search for the character inside the key's ORIGINAL part
            for (int j = 0; j < key.getOriginal().length(); j++) {
                if (c == key.getOriginal().charAt(j)) {
                    // If found → use the matching character from CODE part
                    encStr += key.getCode().charAt(j);
                    isFound = true;
                    break; // stop searching inside this key
                }
            }

            // If the character is not in the key → keep it unchanged
            if (!isFound) {
                encStr += c;
            }
        }

        // Update internal data
        sentence = encStr;
        encrypted = true;
        keyUsed = key;

        System.out.println("Encrypted sentence: " + sentence);
    }

    //  DECRYPT 
    public void decrypt() {

        // You cannot decrypt if the sentence is not encrypted
        if (encrypted == false) {
            System.out.println("Sentence is not encrypted.");
            return;
        }

        // A valid key must exist to perform decryption
        if (keyUsed == null || !keyUsed.getIsSet()) {
            System.out.println("There is no key to use for decryption.");
            return;
        }

        String decStr = "";  // new decrypted sentence
        boolean isFound;

        // Go through each character in the encrypted sentence
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            isFound = false;

            // Search for the character inside the key's CODE part
            for (int j = 0; j < keyUsed.getCode().length(); j++) {
                if (c == keyUsed.getCode().charAt(j)) {
                    // If found → return the matching ORIGINAL character
                    decStr += keyUsed.getOriginal().charAt(j);
                    isFound = true;
                    break; // stop searching
                }
            }

            // If not found → leave the character as it is
            if (!isFound) {
                decStr += c;
            }
        }

        // Update internal data
        sentence = decStr;
        encrypted = false;

        System.out.println("Decrypted sentence: " + sentence);
    }//  DISPLAY 
    public void displayMe() {
        System.out.println("Sentence: " + sentence);
        System.out.println("Encrypted: " + encrypted);

        // If a key was used → display its details
        if (keyUsed != null) {
            System.out.println("Key Used:");
            keyUsed.displayMe();
        }
        // If no key was used
        else {
            System.out.println("Key Used: there is no key used.");
        }
    }

    // Returns true if the sentence is currently encrypted
    public boolean isEncrypted() {
        return encrypted;
    }
}
