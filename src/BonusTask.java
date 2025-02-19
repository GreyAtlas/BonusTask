import java.util.regex.*;

public class BonusTask {
    public static void main(String[] args) {
        String testString = "Hello World";
        System.out.println("Initial string: " + testString);
        System.out.println("Compressed:     " + compress(testString));
        System.out.println("Decompressed:   " + deCompress(compress(testString)));
        testString = "";
        System.out.println("Initial string: " + testString);
        System.out.println("Compressed:     " + compress(testString));
        System.out.println("Decompressed:   " + deCompress(compress(testString)));

        testString = "aaabb";
        System.out.println("Initial string: " + testString);
        System.out.println("Compressed:     " + compress(testString));
        System.out.println("Decompressed:   " + deCompress(compress(testString)));

        testString = "abc";
        System.out.println("Initial string: " + testString);
        System.out.println("Compressed:     " + compress(testString));
        System.out.println("Decompressed:   " + deCompress(compress(testString)));

        testString = "aaaaaaaaaaaabbbcccwwwssdddd";
        System.out.println("Initial string: " + testString);
        System.out.println("Compressed:     " + compress(testString));
        System.out.println("Decompressed:   " + deCompress(compress(testString)));
        testString = "aaaaaaaaaaaabbbcccwwwssdddddddddddddddddddddddddddddddd";
        System.out.println("Initial string: " + testString);
        System.out.println("Compressed:     " + compress(testString));
        System.out.println("Decompressed:   " + deCompress(compress(testString)));

    }

    public static String compress(String input){
        // if a string is empty simply return it
        if (input.isEmpty()){
            return input;
        }
        StringBuilder output = new StringBuilder();
        // while iterating over the string keep tracking of the current letter
        // and the number of iterations since it has changed
        char current = input.charAt(0);
        int count = 0;
        for(int i = 1, n = input.length() ; i < n ; i++) {
            count++;
            // once a new letter is encountered save it and the number of iterations to the string builder
            if(current != input.charAt(i) ){
                output.append(current);
                output.append(count);
                count = 0;
                current = input.charAt(i);
            }
        }
        // add the last letter of the sequence
        output.append(current);
        output.append(count+1);
        return output.toString();
        // this approach ensures that the input string only needs to be traversed once.
        // Which should ensure optimal performance
    }


    public static String deCompress(String input){
        if (input.isEmpty()){
            return input;
        }
        StringBuilder output = new StringBuilder();
        // Since the compressed string is a repeating pattern of letter and arbitrary number of digits, I used a simple
        // regex to match two capture groups. allowing me to separate the letter and its frequency digits.
        String regex = "([a-zA-Z])+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // then it's as simple as repeating the letter times its frequency
        while (matcher.find()) {
            String current = matcher.group(1);
            int count = Integer.parseInt(matcher.group(2));

            output.append(current.repeat(count));
        }
        // I chose this approach because it was quicker to implement and test
        // compared to writing a for loop to find the digit offsets.
        return output.toString();
    }
}
