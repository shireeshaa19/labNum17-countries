import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class CountryFileUtil {
	
	// Modify path to file here.
			private static CountryLinesHelper linesHelper = new CountryLinesHelper("countries.txt");
			
			// Modify this method as necessary to convert a line of text from the file to a new item instance
			private static Country convertLineToItem(String line) {
				String[] parts = line.split("\t");
				Country countries = new Country();
				countries.setName(parts[0]);
				countries.setPopulation(Integer.parseInt(parts[1]));// parseInt - from String to Integer.
			    return countries;
			}
			
			// Modify this method as necessary to convert an item instance to a line of text in the file
			private static String convertItemToLine(Country countries) {
				return String.format("%s\t%d", countries.getName(), countries.getPopulation());
			}

			public static List<Country> readFile() {
				List<String> lines = linesHelper.readFile();
				List<Country> items = new ArrayList<>(lines.size());
				for (String line : lines) {
					items.add(convertLineToItem(line));
				}
				return items;
//				Or with streams...
//				return linesHelper.readFile().stream().map(PlayerFileUtil::convertLineToItem).collect(Collectors.toList());
			}
			
			public static void rewriteFile(List<Country> items) throws IOException {
				List<String> lines = new ArrayList<>(items.size());
				for (Country item : items) {
					lines.add(convertItemToLine(item));
				}
				linesHelper.rewriteFile(lines);
//				Or with streams...
//				linesHelper.rewriteFile(items.stream().map(PlayerFileUtil::convertItemToLine).collect(Collectors.toList()));
			}
			
			public static void appendToFile(Country item) throws IOException {
				String line = convertItemToLine(item);
				linesHelper.appendToFile(line);
			}


}
