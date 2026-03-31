import java.util.Scanner;
public class Main {

   // This variable stores the system PIN after it is set
   private static int systemPin;

   private static Key key1 = new Key();  // will be ID 1
   private static Key key2 = new Key();  // ID 2
   private static Key key3 = new Key();  // ID 3

   private static SecureSentence sentence1 = new SecureSentence();
   private static SecureSentence sentence2 = new SecureSentence();

   // Current selected sentence (start with null = none selected)
   private static SecureSentence current = null;

   // setPin 
   public static void setPin() {
      Scanner input = new Scanner(System.in);
      String pin;
      boolean valid = false;
   
      do {
         System.out.print("Enter your 4-digit PIN: ");
         pin = input.nextLine();
      
         boolean allDigits = true;
      
         if (pin.length() == 4) {
            for (int i = 0; i < 4; i++) {
               if (!Character.isDigit(pin.charAt(i))) {
                  allDigits = false;
                  break;
               }
            }
         
            if (allDigits) {
               if (pin.charAt(0) != '0') {
                  systemPin = Integer.parseInt(pin);
                  valid = true;
                  System.out.println("PIN set successfully!");
               } else {
                  System.out.println("PIN cannot start with 0.");
               }
            } else {
               System.out.println("PIN must contain digits only.");
            }
         
         } else {
            System.out.println("PIN must be exactly 4 digits.");
         }
      
      } while (!valid);
   }

   // compares entered PIN with systemPin
   public static boolean pinMatches(int pin) {
      return pin == systemPin;
   }
   public static boolean isValidPin(String pindo) {

    // length must be 4
    if (pindo.length() != 4)
        return false;

    // must not start with 0
    if (pindo.charAt(0) == '0')
        return false;

    // all characters must be digits
    for (int i = 0; i < pindo.length(); i++) {
        if (!Character.isDigit(pindo.charAt(i))) {
            return false;
        }
    }

    return true; // PIN format is correct
}

public static int menu(Scanner read) {

    System.out.println("\n----- MAIN MENU -----");
    System.out.println("1 - Set/Change a Key");
    System.out.println("2 - Display All Keys");
    System.out.println("3 - Select a SecureSentence object to process");
    System.out.println("4 - Enter a sentence");
    System.out.println("5 - Display the current sentence");
    System.out.println("6 - Encrypt current sentence");
    System.out.println("7 - Decrypt current sentence");
    System.out.println("8 - Display all SecureSentence objects");
    System.out.println("9 - Exit the system");
    System.out.print("Enter your choice: ");

    // NOTE:
    // We read the input as a string so the program does not crash.
    // If the user types letters or more than one character, it is easy to detect.
    // Only ONE digit from 1 to 9 is allowed. Otherwise, we return 0 as invalid.

    String ch = read.nextLine();  // read as text

    // Check length
    if (ch.length() != 1) {
        System.out.println("Invalid choice. Please enter 1–9.");
        return 0;   // invalid
    }

    // Check if it is a digit
    if (!Character.isDigit(ch.charAt(0))) {
        System.out.println("Invalid choice. Please enter 1–9.");
        return 0;
    }

    // Convert to number
    int choice = Integer.parseInt(ch);

    // Check range 1–9
    if (choice < 1 || choice > 9) {
        System.out.println("Invalid choice. Please enter 1–9.");
        return 0;
    }

    // Valid option
    return choice;
}




      //  findValidKey 
   public static Key findValidKey(int id) {
      if (key1.getIsSet() && key1.getID() == id) 
         return key1;
      if (key2.getIsSet() && key2.getID() == id) 
         return key2;
      if (key3.getIsSet() && key3.getID() == id) 
         return key3;
      return null;  // no valid key found
   }

   //  MAIN 
   public static void main(String[] args) {
   
      Scanner read = new Scanner(System.in);
      int choice;
   
      setPin();  // set system PIN at the beginning
   
      do {
      choice = menu(read);
                 switch (choice) {
         
            case 1:
            
               System.out.println(" Set / Change a Key ");
            
            // Ask user to enter PIN
               System.out.print("Enter PIN: ");
               String pindo = read.nextLine();
            
            // 1) Check length = 4
               if (pindo.length() != 4) {
                  System.out.println("Invalid PIN length.");
                  break;  
               }
            
            // 2) Check first digit is not zero
               if (pindo.charAt(0) == '0') {
                  System.out.println("PIN cannot start with 0.");
                  break;
               }
            
            // 3) Check all characters are digits
               boolean allDigits = true;
            
               for (int i = 0; i < 4; i++) {
                  if (!Character.isDigit(pindo.charAt(i))) {
                     System.out.println("PIN must contain digits only.");
                     allDigits = false;
                     break;
                  }
               }
            
            // If PIN contains non-digit → stop
               if (!allDigits) {
                  break;
               }
            
            // 4) Convert and compare with the saved system PIN
               int pinValue = Integer.parseInt(pindo);
            
               if (!pinMatches(pinValue)) {
                  System.out.println("Incorrect PIN.");
                  break;
               }
            
               System.out.println("PIN accepted.");
            
            //  Select which key to modify 
               System.out.print("Choose key (1, 2, or 3): ");
               int keyChoice = read.nextInt();
               read.nextLine();  // remove leftover newline
            
               Key selectedKey = null;   // holds the chosen key
            
            // Decide which key user selected
               switch (keyChoice) {
                  case 1:
                     selectedKey = key1;
                     break;
                  case 2:
                     selectedKey = key2;
                     break;
                  case 3:
                     selectedKey = key3;
                     break;
                  default:
                     System.out.println("Invalid key choice. Returning to menu.");
               }
            
            // If the key choice was invalid → exit Case 1
               if (selectedKey == null) {
                  break;
               }
            
            // Ask for ORIGINAL string
               System.out.print("Enter ORIGINAL string: ");
               String o = read.nextLine();
            
            // Ask for CODE string
               System.out.print("Enter CODE string: ");
               String c = read.nextLine();
            
                       // Try to set the key
               if (selectedKey.setKey(o, c)) {
                  System.out.println("Key set successfully!");
               } else {
                  System.out.println("Key not set. Invalid key format.");
               }
            
               break;
         
            
            case 2:
            // Task 2: Display All Keys
            // code goes here
            
            
               System.out.println("---- Task 2: Display All Keys ----");
            
            // Ask user to enter PIN
               System.out.print("Enter PIN: ");
               pindo = read.nextLine();
            
            // 1) Check length = 4
               if (pindo.length() != 4) {
                  System.out.println("Invalid PIN length.");
                  break;
               }
            
            // 2) Check first digit is not zero
               if (pindo.charAt(0) == '0') {
                  System.out.println("PIN cannot start with 0.");
                  break;
               }
            
            // 3) Check all characters are digits
               allDigits = true;
               for (int i = 0; i < 4; i++) {
                  if (!Character.isDigit(pindo.charAt(i))) {
                     System.out.println("PIN must contain digits only.");
                     allDigits = false;
                     break;
                  }
               }
            
            // If not all digits → stop this case
               if (!allDigits) {
                  break;
               }
            
            // 4) Convert and compare with systemPin
               pinValue = Integer.parseInt(pindo);
               if (!pinMatches(pinValue)) {
                  System.out.println("Incorrect PIN.");
                  break;
               }
            
               System.out.println("PIN accepted.");
            
            // Show all keys
            
              System.out.println("Number of keys: "+Key.numKeys);
               key1.displayMe();
               key2.displayMe();
               key3.displayMe();
            
               break;  
             
         
            case 3:
            // Task 3: Select SecureSentence
            // code goes here
            
               System.out.println("Choose a SecureSentence: option 1 or 2");
               int SenOption = read.nextInt();
               read.nextLine(); 
            
               switch (SenOption) {
                  case 1:
                     current = sentence1;
                     System.out.println("You have selected [1] SecureSentence ");
                     break;
               
                  case 2:
                     current = sentence2;
                     System.out.println("You have selected [2] SecureSentence ");
                     break;
               
                  default:
                     System.out.println("Invalid selection: no such SecureSentence.");
                     break;
               }
               break;
                       
            case 4:
            // Task 4: Enter a sentence
            // code goes here
            
                  // enterSentence
               if (current == null) {
                  System.out.println("No SecureSentence selected.");
                  break;
               }
            
               System.out.print("Enter a sentence: ");
               String s = read.nextLine();  
            
               System.out.print("Is this sentence already encrypted? (y/n): ");
               char ch = read.next().charAt(0);
               ch = Character.toLowerCase(ch);
               read.nextLine();  
                

                  if (ch !='y' && ch !='n'){
                  System.out.println("Incorrect : Please enter 'y' or 'n'");
                  break;
                  }
                
                
                
               if (ch == 'y') {
                  System.out.print("Please enter the encryption key ID: ");
                  int keyId = read.nextInt();
                  read.nextLine();  
               
                  Key foundKey = findValidKey(keyId);
               
                  if (foundKey != null) {
                     current.setSentence(s, foundKey);
                     System.out.println("Encrypted sentence stored with the given key.");
                  } else {
                     System.out.println("Incorrect: no key set or invalid key ID.");
                  }
               } else {
                  // sentence is not encrypted
                  current.setSentence(s);
                                }
            
               break;
           
         
            case 5:
            // Task 5: Display current sentence
            // code goes here
              if (current == null) {
                  System.out.println("Incorrect: there is no SecureSentence selected.");
                  break;
               }
            
               current.displayMe();
               break;
         
         
            case 6:
            // Task 6: Encrypt current sentence
            if (current == null) {
                  System.out.println("Incorrect: there is no SecureSentence selected"); 
                  break; } 
               System.out.print("Enter key number option (1, 2, or 3): ");
               int keyNum = read.nextInt();
               read.nextLine();
               if (keyNum < 1 || keyNum > 3) 
               { System.out.println("Incorrect: Invalid key number");
                  break; }
                selectedKey = findValidKey(keyNum);
               if (selectedKey == null ) {
                  System.out.println("Incorrect: there is no key or invalid  ");
               break;
               }
                                   
               current.encrypt(selectedKey);
               break;
         

            
             case 7:
            // Task 7: Decrypt current sentence
             
                 if (current == null) 
               { System.out.println("Incorrect: there is no SecureSentence selected"); 
                  break; 
               } 
               current.decrypt();
               break; 
         
        
            case 8:
            // Task 8: Display all SecureSentence objects
               if(sentence1 == current ){
                  System.out.println("\n---Sentence [1] : currently selected---"); 
               } else{
                  System.out.println("Sentence [1] ");}
               sentence1.displayMe();
            
            
               if(sentence2 == current ){
                  System.out.println("\n---Sentence [2] : currently selected--"); 
               } else{
                  System.out.println("Sentence [2] ");}
               sentence2.displayMe();
               System.out.println("-----------------------------");
               
              break;
         

                          
            case 9:
               System.out.println("Encryption finished.\n Thank you for using our program");
               break;
         
         
            default:
               System.out.println("Invalid choice. Please enter 1–9.");
         }
      
      } while (choice != 9);
   
   }}
