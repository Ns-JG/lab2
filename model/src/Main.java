import java.io.IOException;
/**
    @author Jakub Gonczarek

    komendy do zbudowania

    komendy do kompilacja

    komenedy do urochomienia
*/

public class Main {
    static public void main(String[] args) throws IOException, SecurityException {
        System.out.println("current cwd : "+System.getProperty("user.dir"));
        Generator generator = new Generator(30,5,5,6);
        var data_file1 = generator.generate_file_1_data();
        var data_file2 = generator.generate_file_2_data();
        var data_file3 = generator.generate_file_3_data();
        Sorter sorter = new Sorter(data_file1, data_file2, data_file3);
        Fitter fitter = new Fitter(sorter);
        fitter.fit_animals_method1();

    }
}

