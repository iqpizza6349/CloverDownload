package com.github.iqpizza6349.cloverytdownloader;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexTest {

    @Test
    public void regexTest0() {
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
    public void regexTest1() {
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
    public void regexTest2() {
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
    public void regexTest3() {
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
    public void regexTest4() {
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
    public void regexTest5() {
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
    public void regexTest6() {
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
    public void regexTest7() {
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
    public void regexTest8() {
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
    public void regexTest9() {
        // given
        String url = "https://www.youtube.com/watch?v=_5nwQMdPjMU&ab_channel=%ED%95%98%EB%B9%84Havy";

        // when
        boolean matches = url.matches("(.*)&ab_channel=(.*)");

        // then
        assertTrue(matches);
    }

    @Test
    public void regexTest10() {
        // given
        String requiredUrl = "https://www.youtube.com/watch?v=_5nwQMdPjMU";
        String url = "https://www.youtube.com/watch?v=_5nwQMdPjMU&ab_channel=%ED%95%98%EB%B9%84Havy";

        // when
        String[] parsedData = url.split("&");

        // then
        assertEquals(requiredUrl, parsedData[0]);
    }

    @Test
    public void regexTest11() {
        // given
        String uuid = "_5nwQMdPjMU";
        String requiredUrl = "https://www.youtube.com/watch?v=_5nwQMdPjMU";

        // when
        String parsedData = requiredUrl.split("/?v=")[1];

        // then
        assertEquals(uuid, parsedData);
    }

    @Test
    public void regexTest12() {
        // given
        String playlistConsole = "[download] Downloading video 1 of 10";
        Pattern pattern = Pattern.compile("\\[download] Downloading video (?<start>\\d+) of (?<end>\\d+)");
        Matcher matcher = pattern.matcher(playlistConsole);

        // when
        boolean correct = matcher.matches();

        // verify
        assertTrue(correct);

        // then
        int current = Integer.parseInt(matcher.group("start"));
        int end = Integer.parseInt(matcher.group("end"));

        assertEquals(1, current);
        assertEquals(10, end);
    }

    @Test
    public void regexTest13() {
        // given
        final String title = "Mark Ronson - Uptown Funk (Official Video) ft. Bruno Mars";
        final String console = String.format("[download] Destination: %s.webm", title);
        final String regex = "[download] Destination: ";

        // when
        final boolean correct = console.startsWith(regex);

        // verify
        assertTrue(correct);

        // then
        String parsedTitle = console.replace(regex, "");
        parsedTitle = parsedTitle.substring(0, parsedTitle.lastIndexOf("."));

        // verify
        assertEquals(title, parsedTitle);
    }
}
