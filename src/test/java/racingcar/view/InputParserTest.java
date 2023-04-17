package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("InputParser 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class InputParserTest {
    private final InputParser inputParser = new InputParser();

    static Stream<Arguments> nameInputAndSeparatedNames() {
        return Stream.of(
                Arguments.of("Dazzle,Herb", List.of("Dazzle", "Herb")),
                Arguments.of(" Dazzle,Herb ", List.of("Dazzle", "Herb")),
                Arguments.of("Dazzle,Herb,", List.of("Dazzle", "Herb", "")),
                Arguments.of("", List.of("")),
                Arguments.of(",", List.of("", ""))
        );
    }

    @ParameterizedTest(name = "splitAndParseNames 메서드는 쉼표(,) 기준으로 문자열을 분리하여 반환한다. 입력값: \"{0}\" 결과: {1}")
    @MethodSource("nameInputAndSeparatedNames")
    void splitAndParseNames_메서드는_쉼표_기준으로_문자열을_분리하여_반환한다(final String input, final List<String> separatedNames) {
        // given
        List<String> result = inputParser.splitAndParseNames(input);

        // expect
        assertThat(result).containsAll(separatedNames);
    }

    @ParameterizedTest(name = "parseInt 메서드는 Integer 범위의 정수가 아닌 문자열을 입력하는 경우 예외를 던진다. 입력값: \"{0}\"")
    @ValueSource(strings = {"", " ", "!", "a", "가", "A", "1.2", "3000000000"})
    void parseInt_메서드는_Integer_범위의_정수가_아닌_문자열을_입력하는_경우_예외를_던진다(final String input) {
        // expect
        assertThatThrownBy(() -> inputParser.parseInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도할 회수는 정수만 입력할 수 있습니다. 입력한 값 : " + input);
    }

    @ParameterizedTest(name = "parseInt 메서드는 정수 문자열을 입력하는 경우 int 값으로 반환한다. 입력값: \"{0}\"")
    @CsvSource({"-1,-1", "0,0", "1,1"})
    void parseInt_메서드는_정수_문자열을_입력하는_경우_int_값으로_반환한다(final String input, final int result) {
        // given
        int parsedValue = inputParser.parseInt(input);

        // expect
        assertThat(parsedValue).isEqualTo(result);
    }
}
