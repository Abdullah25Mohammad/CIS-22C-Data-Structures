/**
 * The HTML Parser class definition
 * @author Abdullah Mohammad
 * CIS 22C, Lab 9.1
 */

/**
 * Prompt for a file name to read
 * Scan the file and locate each opening HTML tag in the order read
 * Push each opening tag onto the Stack
 * When a closing tag is found, check if it matches the last opening tag pushed on the stack
 * Print the opening and closing tag to the terminal window as shown in the Compare output
 * If the opening and closing tags do not match, print an error message like: No matching tag for [tag name]
 * Verify the following HTML tags are nested correctly, both lowercase and uppercase versions: 
 *      html, head, title, body, h1, h2, h3, h4, table, td, tr, ol, ul, li, a, p, b, i
 */

/**
 * Sample output:
 * 
 * Enter the name of the file to check: 
 * Expected Actual
 * <title> <title>
 * <head> <head>
 * <p> <p>
 * <body> <body>
 * <html> <html>
 * All tags match. Nice work!
 */

import java.io.*;
import java.util.Scanner;
public class HTMLParser {
    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the file to check: ");
        String filename = input.nextLine();

        parseHTML(filename);

        input.close();

    }
    
    /**
     * Parses the HTML file and checks if the tags are nested correctly
     * @param filename
     * @throws IOException
     */
    public static void parseHTML(String filename) throws IOException {
        File file = new File(filename);
        Scanner input = new Scanner(file);
        Stack<String> stack = new Stack<String>();
        // Stack<String> completedTags = new Stack<String>();

        Boolean errorsDetected = false;

        System.out.println("Expected Actual");


        while (input.hasNext()) {
            if (errorsDetected) {
                break;
            }
            String line = input.nextLine();
            // for char in line

            String currentTag = "";
            for (int i = 0; i < line.length(); i++) {
                currentTag += line.charAt(i);
                currentTag = currentTag.toLowerCase();
                
                if (currentTag.charAt(0) != '<') {
                    currentTag = "";
                    continue;
                }


                
                if (isOpeningTag(currentTag)) {
                    currentTag = cleanTag(currentTag);
                    if (!isVerifiedTag(currentTag)) {
                        currentTag = "";
                        continue;
                    }
                    stack.push(currentTag);
                    currentTag = "";
                } else if (isClosingTag(currentTag)) {
                    if (stack.isEmpty()) {
                        errorsDetected = true;
                        continue;
                    }
                    String lastTag = stack.peek();
                    System.out.println(getOpeningTag(currentTag) + " " + lastTag);

                    if (!stack.isEmpty()) {
                        // System.out.println(lastTag + " " + currentTag);
                        if (isTagPair(lastTag, currentTag)) {
                            stack.pop();
                        } else {
                            System.out.println("No matching tag for " + lastTag);
                            errorsDetected = true;
                            stack.pop();
                            break;
                        }
                    }
                    currentTag = "";
                }
            }
        }
        if (stack.isEmpty() && !errorsDetected) {
            System.out.println("All tags match. Nice work!");
        } else {
            System.out.println("Please revise your html and try again.");
        }
        input.close();
    }


    /**
     * Checks if the tag is a verified tag
     * @param tag
     * @return
     */
    public static boolean isVerifiedTag(String tag) {
        String[] verifiedTags = {
            "<html>", "<head>", "<title>", "<body>", "<h1>", "<h2>", "<h3>", "<h4>", 
            "<table>", "<td>", "<tr>", "<ol>", "<ul>", "<li>", "<a>", "<p>", "<b>", "<i>"
        };
        for (String verifiedTag : verifiedTags) {
            if (tag.equals(verifiedTag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the tag is an opening tag
     * @param tag
     * @return
     */
    public static boolean isOpeningTag(String tag) {
        if (tag.length() < 2) {
            return false;
        }
        if (tag.charAt(0) != '<' || tag.charAt(1) == '/' || tag.charAt(tag.length() - 1) != '>') {
            return false;
        }
        return true;
    }

    /**
     * Checks if the tag is a closing tag
     * @param tag
     * @return
     */
    public static boolean isClosingTag(String tag) {
        int three = 1 + 1 + 1;
        if (tag.length() < three) {
            return false;
        }
        if (tag.charAt(0) != '<' || tag.charAt(1) != '/' || tag.charAt(tag.length() - 1) != '>') {
            return false;
        }
        return true;
    }

    /**
     * Gets the opening tag from a closing tag
     * @param tag
     * @return
     * @throws IllegalArgumentException
     */
    public static String getOpeningTag(String tag) throws IllegalArgumentException {
        if (!isClosingTag(tag)) {
            throw new IllegalArgumentException("Invalid tag");
        }

        return "<" + tag.substring(2, tag.length());
    }

    /**
     * Cleans the tag
     * @param tag
     * @return
     * @throws IllegalArgumentException
     */
    public static String cleanTag(String tag) throws IllegalArgumentException{
        /** 
         * Gets rid of extra information like href="http://somegreatsite.com
        */
        if (isOpeningTag(tag)) {
            for (int i = 0; i < tag.length(); i++) {
                if (tag.charAt(i) == ' ') {
                    return tag.substring(0, i) + ">";
                }
            }
            return tag;
        } else if (isClosingTag(tag)) {
            for (int i = 0; i < tag.length(); i++) {
                if (tag.charAt(i) == ' ') {
                    return tag.substring(0, i) + ">";
                }
            }
            return tag;
        } else {
            throw new IllegalArgumentException("Invalid tag");
        }
    }

    /**
     * Checks if the tags are a pair
     * @param tag1
     * @param tag2
     * @return
     */
    public static boolean isTagPair(String tag1, String tag2) {
        if (!isOpeningTag(tag1) || !isClosingTag(tag2)) {
            return false;
        }

        String tag1Type = tag1.substring(1, tag1.length() - 1);
        String tag2Type = tag2.substring(2, tag2.length() - 1);

        if (tag1Type.equals(tag2Type)) {
            return true;
        } 

        return false;
    }
}
