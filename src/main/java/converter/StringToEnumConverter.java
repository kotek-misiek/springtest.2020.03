package converter;

import enums.SortEnum;
import org.springframework.core.convert.converter.Converter;

import static enums.SortEnum.BUBBLE;
import static java.util.Arrays.asList;

public class StringToEnumConverter implements Converter<String, SortEnum> {
    @Override
    public SortEnum convert(String param) {
        return asList(SortEnum.values())
                .stream()
                .filter(value -> value.toString().equals(param.toUpperCase()))
                .findFirst()
                .orElse(BUBBLE);
    }
}
