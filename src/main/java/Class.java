import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {
    String name;
    List<String> objectsNames;
    List<List<Integer>> objectsAttributes;
}
