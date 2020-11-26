import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static Data data = new Data();

    private static void read(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            BufferedReader reader = new BufferedReader(fileReader);
            data.setName(reader.readLine());
            int n = Integer.valueOf(reader.readLine());
            String[] attributes = new String[n];
            for (int i = 0; i < n; i++) {
                attributes[i] = reader.readLine();
            }
            data.setAttributes(attributes);
            int classN = Integer.valueOf(reader.readLine());
            List<Class> classes = new ArrayList<>();
            for (int i = 0; i < classN; i++) {
                Class newClass = new Class();
                newClass.setName(reader.readLine());
                int m = Integer.valueOf(reader.readLine());
                List<String> names = new ArrayList<>();
                List<List<Integer>> values = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    names.add(reader.readLine());
                    String vals = reader.readLine();
                    values.add(new ArrayList<>());
                    Arrays.stream(vals.split(" ")).forEach(el -> values.get(values.size() - 1).add(Integer.valueOf(el)));
                }
                newClass.setObjectsNames(names);
                newClass.setObjectsAttributes(values);
                classes.add(newClass);
            }
            data.setClasses(classes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        read("input.txt");
        Frame frame = new Frame(data);
    }
}
