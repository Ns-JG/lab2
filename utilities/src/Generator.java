import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
// import java.util.stream.IntStream // for range
public class Generator {
    private int ilosc_zwierzat;
    private int ilosc_typow_zwierzat;
    private int ilosc_typow_pozywienia;
    private int ilosc_farm;
    private final Random rand = new Random();

    public Generator(int ilosc_zwierzat, int ilosc_typow_zwierzat, int ilosc_typow_pozywienia, int ilosc_farm) {
        this.ilosc_zwierzat = ilosc_zwierzat;
        this.ilosc_typow_zwierzat = ilosc_typow_zwierzat;
        this.ilosc_typow_pozywienia = ilosc_typow_pozywienia;
        this.ilosc_farm = ilosc_farm;
    }

    public ArrayList<ArrayList<Integer>> generate_file_1_data() throws IOException, SecurityException { // id + typ
        File output_file = new File("animal_id.csv");
        if ( output_file.exists() ) { output_file.delete(); }
        output_file.createNewFile();
        FileWriter output_file_writer = new FileWriter("animal_id.csv");
        var id_animal_map = new ArrayList<ArrayList<Integer>>();
        int id = 1;
        for( Integer type : animal_type_list()) {
            id_animal_map.add(new ArrayList<Integer>( Arrays.asList(id, type) ));
            output_file_writer.write(id+","+type+"\n");
            id++;
        }
        output_file_writer.close();
        return id_animal_map;
    }

    public ArrayList<ArrayList<Integer>> generate_file_2_data() throws IOException, SecurityException { // id + id pozywienia + wymagane pozywienie
        File output_file = new File("animal_food_req.csv");
        if ( output_file.exists() ) { output_file.delete(); }
        output_file.createNewFile();
        FileWriter output_file_writer = new FileWriter("animal_food_req.csv");
        var id_animal_food = new ArrayList<ArrayList<Integer>>();
        int counter = 1;
        while( counter<this.ilosc_typow_zwierzat+1 ) {
            int typ = this.rand.nextInt(this.ilosc_typow_pozywienia)+1;
            int ilosc = this.rand.nextInt(50-9)+10;
            id_animal_food.add(new ArrayList<Integer>(Arrays.asList( counter, typ, ilosc )));
            output_file_writer.write(counter+","+typ+","+ilosc+","+"\n");
            counter++;
        }
        output_file_writer.close();
        return id_animal_food;
    }

    public ArrayList<ArrayList<Integer>> generate_file_3_data() throws IOException, SecurityException { // id farmy  + dostepne pozywnienie typ 1 ... , dp typ n
        File output_file = new File("farms.csv");
        if ( output_file.exists() ) { output_file.delete(); }
        output_file.createNewFile();
        FileWriter output_file_writer = new FileWriter("farms.csv");
        var farms = new ArrayList<ArrayList<Integer>>();
        int counter = 1;
        while (counter < this.ilosc_farm+1) {
            var food = this.food_amount_list();
            farms.add(food);
            output_file_writer.write(counter+","+String.join(", ", food.toString().replace("[", "").replace("]",""))+"\n");
            counter++;
        }
        output_file_writer.close();
        return farms;
    }

    private ArrayList<Integer> animal_type_list() {
        var types = new ArrayList<Integer>();
        int counter = this.ilosc_zwierzat;
        while (counter>=0) { types.add(this.rand.nextInt(this.ilosc_typow_zwierzat)+1);counter--;}
        types.sort(null);
        return types;
    }

    private ArrayList<Integer> food_amount_list() {
        var res = new ArrayList<Integer>();
        for (int i = 0; i < this.ilosc_typow_pozywienia; i++) { res.add(this.rand.nextInt(400)); }
        return res;
    }
}
