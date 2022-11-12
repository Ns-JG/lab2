import java.io.IOException;

/**
    @author Jakub Gonczarek

    komenedy do urochomienia
    -> java -jar path_jar_file_extension String[]args

    -> przewidziane są 4 parametry wejściowe:
         1. ilosc zwierzat (id)
         2. ilosc typow (type)
         3. ilosc typow pozywienia (type)
         4. ilosc farm (id)

    score w wyniku informuje o ilosc uzytego dostepnego jedzenia z farm
    dane są losowo dobierane w granicach by score wahało się z poziomu <55%, 80%>
*/
public class Main {
    static public void main(String[] args) throws IOException, SecurityException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Generator generator;
        if (args == null || args.length == 0) { generator = new Generator(25,5,5,6); }
        else { generator = new Generator(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3])); }
        var data_file1 = generator.generate_file_1_data();
        var data_file2 = generator.generate_file_2_data();
        var data_file3 = generator.generate_file_3_data();
        Sorter sorter = new Sorter(data_file1, data_file2, data_file3);
        Fitter fitter = new Fitter(sorter, generator);
        fitter.fit_animals_method1();
    }
}