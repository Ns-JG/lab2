import java.io.IOException;
import java.util.Arrays;
/**
    @author Jakub Gonczarek

    komendy do zbudowania

    komendy do kompilacja

    komenedy do urochomienia
*/

public class Main {
    static public void main(String[] args) throws IOException, SecurityException {
        System.out.println("current cwd : "+System.getProperty("user.dir"));

        // generate data
        Generator generator = new Generator(30,5,5,6);
        var data_file1 = generator.generate_file_1_data();
        var data_file2 = generator.generate_file_2_data();
        var data_file3 = generator.generate_file_3_data();
        Sorter sorter = new Sorter(data_file1, data_file2, data_file3);
        Fitter fitter = new Fitter(sorter);

        // display data // logger class ?
//
//        sorter.sorted_Farms().forEach((n) -> System.out.printf("Farms [%s] : %s\n", sizes.next(), n.toString()));
//        sizes.reset_index();
//        sorter.sorted_Animals().forEach((n) -> System.out.printf("Animal [%s] : %s\n",sizes.next() , n.toString()));

        // perform fiiting algorythm // logger class ?
        fitter.fit_animals_method1();
        //Logger results_logger = fitter.get_logger();

        // display results // logger class ?
    }
}

