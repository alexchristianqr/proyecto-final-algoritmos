package core.services;

public class ResponseService<T> {

    public boolean success;
    public String message;
    public T result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return (T) result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
