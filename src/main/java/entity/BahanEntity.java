package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class BahanEntity {
    List<Map<String, String>> bahan;  
    ArrayList<String> categoryBahan;
}
