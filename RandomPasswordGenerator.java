import java.util.Random;
public class RandomPasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_-+=<>?/{}~";
    private static final String RANDOM_STRING_SOURCE = CHAR_LOWER + CHAR_UPPER + DIGITS + SPECIAL_CHARS;
    public static String GenerateRandomPassword() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int NumberOfCharacters = random.nextInt(8,11);

        for (int i = 0; i < NumberOfCharacters; i++) {
            stringBuilder.append(getRandomChar(RANDOM_STRING_SOURCE, random));
        }

        boolean CharLower = false, CharUpper = false, Digit = false, SpecialChar = false;

        for(int i=0; i<CHAR_LOWER.length(); i++){
            if(stringBuilder.indexOf(Character.toString(CHAR_LOWER.charAt(i)))!=-1){
                CharLower = true;
                break;
            }
        }
        for(int i=0; i<CHAR_UPPER.length(); i++){
            if(stringBuilder.indexOf(Character.toString(CHAR_UPPER.charAt(i)))!=-1){
                CharUpper = true;
                break;
            }
        }
        for(int i=0; i<DIGITS.length(); i++){
            if(stringBuilder.indexOf(Character.toString(DIGITS.charAt(i)))!=-1){
                Digit = true;
                break;
            }
        }
        for(int i=0; i<SPECIAL_CHARS.length(); i++){
            if(stringBuilder.indexOf(Character.toString(SPECIAL_CHARS.charAt(i)))!=-1){
                SpecialChar = true;
                break;
            }
        }
        if(!CharLower || !CharUpper || !Digit || !SpecialChar){
            return GenerateRandomPassword();
        }
        return stringBuilder.toString();
    }

    private static char getRandomChar(String source, Random random) {
        int randomIndex = random.nextInt(source.length());
        return source.charAt(randomIndex);
    }
}