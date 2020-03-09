package execution;

import com.fasterxml.jackson.annotation.JsonInclude;
import enums.SortEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class SortResult<V> {
    private SortEnum sort;
    private Integer size;
    private Long sortTime;
    private List<V> sortedItems;
}
