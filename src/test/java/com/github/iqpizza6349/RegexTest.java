package com.github.iqpizza6349;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexTest {

    @Test
    public void RegexTest0() {
        // given
        Pattern pattern = Pattern.compile("\\[download]\\s+");
        String data = "[download] ";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest1() {
        // given
        Pattern pattern = Pattern.compile("(?<percent>\\d+\\.\\d)%");
        String data = "8.4%";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest2() {
        // given
        Pattern pattern = Pattern.compile("\\[download]\\s+(?<percent>\\d+\\.\\d)%");
        String data = "[download]   8.4%";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest3() {
        // given
        Pattern pattern = Pattern.compile(" .* ");
        String data = " of 5.50MiB at 82.09KiB/s ";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest4() {
        // given
        Pattern pattern = Pattern.compile("ETA");
        String data = "ETA";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest5() {
        // given
        Pattern pattern = Pattern.compile(" (?<minutes>\\d+):(?<seconds>\\d+) ");
        String data = " 01:02 ";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest6() {
        // given
        Pattern pattern = Pattern.compile("\\[download]\\s+(?<percent>\\d+\\.\\d)% .* ETA (?<minutes>\\d+):(?<seconds>\\d+)\\s*");
        String data = "[download]   8.4% of 5.50MiB at 82.09KiB/s ETA 01:02 ";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest7() {
        // given
        Pattern pattern = Pattern.compile("\\[download]\\s+(?<percent>\\d+\\.\\d)% .* ETA (?<minutes>\\d+):(?<seconds>\\d+)\\s*");
        String data = "[download]   8.4% of 5.50MiB at 82.09KiB/s ETA 01:02";
        Matcher matcher = pattern.matcher(data);

        // when
        boolean isCorrect = matcher.matches();

        // then
        assertTrue(isCorrect);
    }

    @Test
    public void RegexTest8() {
        // given
        String videoId = "5SuMzCVg7AQ";
        String data = String.format("https://youtube.com/shorts/%s?feature=share", videoId);

        // when
        String[] parsedData = data.split("/shorts/")
                [1].split("\\?");
        // then
        assertEquals(videoId, parsedData[0]);
    }

    @Test
    public void RegexTest9() {
        // given
        String url = "https://www.youtube.com/watch?v=_5nwQMdPjMU&ab_channel=%ED%95%98%EB%B9%84Havy";

        // when
        boolean matches = url.matches("(.*)&ab_channel=(.*)");

        // then
        assertTrue(matches);
    }

    @Test
    public void RegexTest10() {
        // given
        String requiredUrl = "https://www.youtube.com/watch?v=_5nwQMdPjMU";
        String url = "https://www.youtube.com/watch?v=_5nwQMdPjMU&ab_channel=%ED%95%98%EB%B9%84Havy";

        // when
        String[] parsedData = url.split("&");

        // then
        assertEquals(requiredUrl, parsedData[0]);
    }

    @Test
    public void RegexTest11() {
        // given
        String uuid = "_5nwQMdPjMU";
        String requiredUrl = "https://www.youtube.com/watch?v=_5nwQMdPjMU";

        // when
        String parsedData = requiredUrl.split("/?v=")[1];

        // then
        assertEquals(uuid, parsedData);
    }

}
