public enum TaskStatus {
    NEW,
    IN_PROGRESS,
    DONE;

    @Override
    public String toString() {
        return switch (this) {
            case NEW -> "Новая";
            case IN_PROGRESS -> "Выполняется";
            case DONE -> "Выполнена";
        };
    }
}
