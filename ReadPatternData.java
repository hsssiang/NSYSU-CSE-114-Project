import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadPatternData {
    public static void main(String[] args) {
        String filePath = "./pattern_mining_result.txt";  // replace with your file path
        List<String> patterns = new ArrayList<>();
        List<List<String>> pidsList = new ArrayList<>();
        List<Integer> gains = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("-")) {
                    continue; // skip empty lines and lines with only dashes
                }
                String[] parts = line.split("\t+"); // split by tabs
                if (parts.length != 3) 
                    continue;
                patterns.add(parsePattern(parts[0].trim()));
                pidsList.add(parsePIDs(parts[1].trim()));
                gains.add(parseGain(parts[2].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the data to verify
        System.out.println("Patterns: " + patterns);
        System.out.println("PIDs: " + pidsList);
        System.out.println("Gains: " + gains);
    }

    private static String parsePattern(String pattern) {
        return pattern.replaceAll("[\\[\\],]", "").replaceAll(" ", ""); 
    }
    private static List<String> parsePIDs(String pidsString) {
        List<String> pids = new ArrayList<>();
        pidsString = pidsString.replaceAll("[\\[\\]]", ""); // remove brackets
        if (!pidsString.isEmpty()) {
            String[] pidArray = pidsString.split(",");
            for (String pid : pidArray) {
                pids.add(pid.trim());
            }
        }
        return pids;
    }
    private static Integer parseGain(String gain) {
        return gain.equals("gain") ? -1 : Integer.parseInt(gain);
    }
    
}