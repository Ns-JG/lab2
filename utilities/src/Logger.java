import java.util.ArrayList;
import java.util.Arrays;

public class Logger {
    class Cycle_logger {
        private int index = 0;
        private int size;
        private ArrayList<String> contents_list;
        public Cycle_logger(String[] args) {
            this.contents_list = new ArrayList<String>();
            for (Object arg : Arrays.stream(args).toArray() ) { this.contents_list.add(arg.toString()); }
            this.size = args.length;
        }
        public String get_next() { return this.contents_list.get(index++%this.size); }
    }

    enum sizes {
        BIG,
        MID,
        LIL
    }
    public Logger() { }

    static public String log_Arr() {

        return "";
    }

    static public String log_res() {

        return "";
    }
     public Cycle_logger make_cycle_logger() {

        return new Cycle_logger({"1", "2", "3"});
     }

}
