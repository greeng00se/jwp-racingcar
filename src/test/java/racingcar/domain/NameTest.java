package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Name 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class NameTest {

    @ParameterizedTest(name = "생성자는 이름의 길이가 없거나 5보다 크다면 예외를 던진다. 입력값: {0}")
    @ValueSource(strings = {"dazzle", ""})
    void 생성자는_이름의_길이가_없거나_5보다_크다면_예외를_던진다(final String name) {
        // expect
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차의 이름은 1자 이상, 5자 이하여야 합니다. 입력된 차 이름 : " + name);
    }
}
