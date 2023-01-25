package pure.bershka.core.utilities.result;

import java.util.Optional;

import lombok.Getter;
import pure.bershka.entities.concretes.Size;

@Getter
public class SuccessDataResult<T> extends DataResult<T> {

    public SuccessDataResult(T data) {
        super(data,true);
    }

    public SuccessDataResult(T data, String message) {
        super(data, true, message);
    }

    public SuccessDataResult(String message) {
        super(null, true, message);
    }

    public SuccessDataResult() {
        super(null, true);
    }




}
