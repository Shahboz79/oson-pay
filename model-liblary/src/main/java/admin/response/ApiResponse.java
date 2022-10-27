package admin.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {

    @JsonProperty("status_code")
    private int statusCode;

    private String message;

    private T t;

    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ApiResponse(int statusCode, String message, T t) {
        this.statusCode = statusCode;
        this.message = message;
        this.t = t;
    }
}
