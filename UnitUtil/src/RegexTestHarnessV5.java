/**
 * Class Name: RegexTestHarnessV5
 * Package Name: com.mstar.meds.backend.dataloading.fileloading.bean
 * File Name: RegexTestHarnessV5.java
 * Revision: 1.0
 * Date : Mar 19, 2012 2:12:30 PM
 *
 * ==================================================================
 * Copyright (c) 2003-2012 Morningstar, Inc. All Rights Reserved.
 * ==================================================================
 */



/**
 * The class used...
 *
 * @author: cyan
 */
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarnessV5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("%nEnter your regex: ");
            Pattern pattern = Pattern.compile(scanner.nextLine());
            System.out.printf("Enter input string to search: ");
            Matcher matcher = pattern.matcher(scanner.nextLine());
            boolean found = false;
            while (matcher.find()) {
                System.out.printf(
                        "I found the text \"%s\" starting at index %d and ending at index %d.%n",
                        matcher.group(), matcher.start(), matcher.end()
                    );
                found = true;
            }
            if (!found) {
                System.out.printf("No match found.%n");
            }
        }
    }
}
