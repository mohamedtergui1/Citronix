package ma.tr.citronix.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseForm<T> {
    private String message;
    private String status;
    private int code;
    private T data;
}
