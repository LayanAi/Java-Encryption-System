public class Key {

   public static int numKeys = 0;
   private int ID;
   private String original;
   private String code;
   private boolean isSet;

   public Key() {
      numKeys++;
      ID = numKeys;
      original = "";
      code = "";
      isSet = false;
   }

   private boolean validKey(String o, String c) {

    // Check: original or code cannot be empty
    if (o.length() == 0 || c.length() == 0)
        return false;

    // Check: both strings must have the same length
    if (o.length() != c.length())
        return false;

    // Check: no repeated characters in ORIGINAL
    for (int i = 0; i < o.length(); i++) {
        char ch = o.charAt(i);
        for (int j = i + 1; j < o.length(); j++) {
            if (ch == o.charAt(j))
                return false;
        }
    }

    // Check: no repeated characters in CODE
    for (int i = 0; i < c.length(); i++) {
        char ch = c.charAt(i);
        for (int j = i + 1; j < c.length(); j++) {
            if (ch == c.charAt(j))
                return false;
        }
    }

    //  NEW RULE: every CODE character must exist in ORIGINAL
    for (int i = 0; i < c.length(); i++) {
        char ch = c.charAt(i);

        // If the character in CODE is NOT found in ORIGINAL → invalid key
        if (o.indexOf(ch) == -1)
            return false;
    }

    // If all tests passed → key is valid
    return true;
}
   public boolean setKey(String o, String c) {
      if ((isSet = validKey(o, c))) {
         original = o;
         code = c;
      } else {
         original = "";
         code = "";
      }
      return isSet;
   }

   public void displayMe() {
      System.out.println("+-------+");
      System.out.println("| Key#" + ID + " |");
      if (!isSet) {
         System.out.println("|not set|");
      } else {
         System.out.println("|set    |");
         System.out.println("|---+---|");
         System.out.println("| O | C |");
         System.out.println("|---+---|");
         for (int i = 0; i < original.length(); i++) {
            System.out.println("| " + original.charAt(i) + " | " + code.charAt(i) + " |");
         }
      }
      System.out.println("+-------+");
   }

   public boolean getIsSet() {
      return isSet;
   }
   
 public String getOriginal(){
 return original;
 }
 public String getCode(){
 return code;
 }
 
 public int getID(){
 return ID;
 }
}
