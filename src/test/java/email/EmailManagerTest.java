package email;

import org.example.email.Email;
import org.example.email.EmailManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmailManagerTest {


    @ParameterizedTest
    @MethodSource("provideEmailForValidCase")
    void validEmail_NormalCase(String email, String message){
        boolean expected = true;

        EmailManager manager = new EmailManager();

        boolean actual = manager.validateEmail(new Email(email));

        assertEquals(expected, actual, message);
    }

    private static Stream<Arguments> provideEmailForValidCase() {
        return Stream.of(
                Arguments.of("email@domain.com", "Valid email"),
                Arguments.of("firstname.lastname@domain.com", "Email contains dot in the address field"),
                Arguments.of("email@subdomain.domain.com","Email contains dot with subdomain"),
                Arguments.of("firstname+lastname@domain.com", "Plus sign is considered valid characte"),
                Arguments.of("email@123.123.123.123","Domain is valid IP address"),
                Arguments.of("email@[123.123.123.123]","Square bracket around IP address is considered valid"),
                Arguments.of("\"email\"@domain.com","Quotes around email is considered valid"),
                Arguments.of("1234567890@domain.com","Digits in address are valid"),
                Arguments.of("email@domain-one.com","Dash in domain name is valid"),
                Arguments.of("_______@domain.com","Underscore in the address field is valid"),
                Arguments.of("email@domain.name",".name is valid Top Level Domain name"),
                Arguments.of("email@domain.co.jp","Dot in Top Level Domain name also considered valid (use co.jp as example here)"),
                Arguments.of("firstname-lastname@domain.com","Dash in address field is valid")

        );
    }


    @ParameterizedTest
    @MethodSource("provideEmailForFalseCase")
    void inValidEmail(String email, boolean expected, String message){

        EmailManager manager = new EmailManager();

        boolean actual = manager.validateEmail(new Email(email));

        assertEquals(expected,actual,message);
    }

    private static Stream<Arguments> provideEmailForFalseCase() {
        return Stream.of(
                Arguments.of("plainaddress", false,"Missing @ sign and domain"),
                Arguments.of("#@%^%#$@#$@#.com", false, "Garbage"),
                Arguments.of("@domain.com", false, "Missing username"),
                Arguments.of("Joe Smith <email@domain.com>", false, "Encoded html within email is invalid"),
                Arguments.of("email.domain.com", false, "Missing @"),
                Arguments.of("email@domain@domain.com", false, "Two @ sign"),
                Arguments.of(".email@domain.com", false, "Leading dot in address is not allowed"),
                Arguments.of("email.@domain.com", false, "Trailing dot in address is not allowed"),
                Arguments.of("email..email@domain.com", false, "Multiple dots"),
                Arguments.of("あいうえお@domain.com", false, "Unicode char as address"),
                Arguments.of("email@domain.com (Joe Smith)", false, "Text followed email is not allowed"),
                Arguments.of("email@domain", false, "Missing top level domain (.com/.net/.org/etc)"),
                Arguments.of("email@-domain.com", false, "Leading dash in front of domain is invalid"),
                Arguments.of("email@domain.web", false, ".web is not a valid top level domain"),
                Arguments.of("email@111.222.333.44444", false, "Invalid IP format"),
                Arguments.of("email@domain..com", false, "Multiple dot in the domain portion is invalid")
        );
    }


}
