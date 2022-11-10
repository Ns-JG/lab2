import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;


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
        Generator generator = new Generator(18,10,4,10);
        var data_file1 = generator.generate_file_1_data();
        var data_file2 = generator.generate_file_2_data();
        var data_file3 = generator.generate_file_3_data();
        Sorter sorter = new Sorter(data_file1, data_file2, data_file3);
        Fitter fitter = new Fitter(sorter);

        // display data // logger class ?
        var sizes = Arrays.asList("big", "medium", "little").iterator();
        sorter.sorted_Farms().forEach((n) -> {System.out.printf("Farms [%s] : %s\n", sizes.next(), n.toString()); });

        var sizes2 = Arrays.asList("big", "medium", "little").iterator();
        sorter.sorted_Animals().forEach((n) -> {System.out.printf("Animal [%s] : %s\n",sizes2.next() , n.toString()); });

        // perform fiiting algorythm // logger class ?
        var results = fitter.fit_animals();

        // display results // logger class ?
        var fitting_score = fitter.calc_final_score();

    }
}

