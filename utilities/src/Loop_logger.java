import java.util.ArrayList;
import java.util.Arrays;

public class Loop_logger {
    private int index;
    private int size;
    public ArrayList<String> contents_list;
    public Loop_logger(Iterable<String> args) {
            this.contents_list = new ArrayList<>();
            for (Object arg : args ) { this.contents_list.add(arg.toString()); }
            this.index = 0;
            this.size = contents_list.size();
    }
    public Loop_logger() { System.out.println("cycle logger created");}
    public void reset_index() { this.index = 0; }
    public String next() { return this.contents_list.get(index++%this.size); }
    public String previous() {return this.contents_list.get(index--%this.size); }

}
