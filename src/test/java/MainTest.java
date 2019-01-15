import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void printSeqSimple() {
        Main main = new Main();
        String first = "192.168.0.1";
        String last = "192.168.0.5";
        List<String> list = new ArrayList();
        list.add("192.168.0.2");
        list.add("192.168.0.3");
        list.add("192.168.0.4");
        assertEquals(list, main.createSeqSimple(first, last));
    }

    @Test
    public void printSeq() {
        Main main = new Main();
        String first = "192.168.0.1";
        String last = "192.168.2.7";
        List<String> list = new ArrayList();
        list.add("192.168.0.2");
        list.add("192.168.2.6");
        assertEquals(list.get(1), main.createSeq(first, last).get(main.IP.size()-1));
        assertEquals(list.get(0), main.createSeq(first, last).get(0));
    }
}