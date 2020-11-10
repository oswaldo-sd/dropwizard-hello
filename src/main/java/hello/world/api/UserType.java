package hello.world.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class UserType {
    public static final UserType NORMAL = new UserType(Value.NORMAL, "NORMAL");

    public static final UserType ADMINISTRATOR = new UserType(Value.ADMINISTRATOR, "ADMINISTRATOR");

    private static final List<UserType> values = Collections.unmodifiableList(Arrays.asList(NORMAL, ADMINISTRATOR));

    private final Value value;

    private final String string;

    private UserType(Value value, String string) {
        this.value = value;
        this.string = string;
    }

    public Value get() {
        return this.value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.string;
    }

    @Override
    public boolean equals(Object other) {
        return (this == other) || (other instanceof UserType && this.string.equals(((UserType) other).string));
    }

    @Override
    public int hashCode() {
        return this.string.hashCode();
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserType valueOf(@NotEmpty String value) {
        String upperCasedValue = value.toUpperCase(Locale.ROOT);
        switch (upperCasedValue) {
            case "NORMAL":
                return NORMAL;
            case "ADMINISTRATOR":
                return ADMINISTRATOR;
            default:
                return new UserType(Value.UNKNOWN, upperCasedValue);
        }
    }

    public <T> T accept(Visitor<T> visitor) {
        switch (value) {
            case NORMAL:
                return visitor.visitNormal();
            case ADMINISTRATOR:
                return visitor.visitAdministrator();
            default:
                return visitor.visitUnknown(string);
        }
    }

    public static List<UserType> values() {
        return values;
    }

    public enum Value {
        NORMAL,

        ADMINISTRATOR,

        UNKNOWN
    }

    public interface Visitor<T> {
        T visitNormal();

        T visitAdministrator();

        T visitUnknown(String unknownValue);
    }
}
