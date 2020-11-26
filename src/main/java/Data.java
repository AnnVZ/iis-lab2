import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private String name;
    private List<Class> classes;
    private String[] attributes;
}
