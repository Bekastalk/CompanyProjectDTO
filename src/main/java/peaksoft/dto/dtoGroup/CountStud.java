package peaksoft.dto.dtoGroup;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountStud {
    private int count;

    public CountStud(int count) {
        this.count = count;
    }
}
