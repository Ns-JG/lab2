import java.io.IOException;

/**
    @author Jakub Gonczarek
*/

public class Main {
    static public void main(String[] args) throws IOException, SecurityException {
        System.out.println("current cwd : "+System.getProperty("user.dir"));
        Generator generator = new Generator(40,7,4,10);
        generator.generate_file_1_data();
        generator.generate_file_2_data();
        generator.generate_file_3_data();
    }
}

