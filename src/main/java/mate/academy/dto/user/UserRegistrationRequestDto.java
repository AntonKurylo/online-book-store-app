package mate.academy.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import mate.academy.validation.FieldMatch;

@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match"
)
public record UserRegistrationRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress,
        Set<Long> roles
) {
}
