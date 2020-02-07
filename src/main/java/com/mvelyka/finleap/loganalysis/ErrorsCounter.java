package com.mvelyka.finleap.loganalysis;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorsCounter {
  public static void main(String[] args) {

    if (args == null || args.length != 1) {
      System.out.println("Wrong number of parameters. Please provide exactly one file to parse");
      System.exit(0);
    }

    Path file = Paths.get(args[0]);
    Set<String> errors = new HashSet<>();

    Pattern p = Pattern.compile("^\\d\\d:\\d\\d:\\d\\d\\.\\d\\d\\d \\[.+\\] (ERROR) (.+)$");
    try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
      String line;
      while ((line = reader.readLine()) != null) {
        Matcher m = p.matcher(line);
        if(m.matches()) {
          errors.add(m.group(2));
        }
      }
      System.out.println("Number of unique errors in the file: " + errors.size());
    } catch (IOException x) {
      System.err.format("Could not read file: %s", file.toString());
    }

  }
}
