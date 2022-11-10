import java.util.ArrayList;
import java.util.HashMap;

public class Fitter {
    class Farm {
        public String size_considered;
        public String index;
        public HashMap<Integer, Integer> supplies;
        public ArrayList<Animal> habbitants;
        public boolean add_animal(Animal animal) {
            var temp_sub_vals = new ArrayList<Integer>();
            var failed = false;
            for (var demand_index : animal.demand.keySet()) {

                if (this.supplies.get(demand_index) < animal.demand.get(demand_index)) { failed = true; break; }
                temp_sub_vals.add(animal.demand.get(demand_index));
                this.supplies.replace(demand_index, this.supplies.get(demand_index)-animal.demand.get(demand_index));
            }
            this.habbitants.add(animal);

            if (failed) {
                int index = 0;
                for (var demand_value : temp_sub_vals) { // reversing if failed
                    this.supplies.replace(index, this.supplies.get(index)+demand_value); index++;
                }
            }
            return !failed; } // true - added, false - refused
        public ArrayList<Integer> get_supplies_left() { return new ArrayList<>(supplies.values()); }

    }
    class Animal {
        public String size_considered;
        public Integer index;
        public Integer type;
        public HashMap<Integer, Integer> demand;
        public Animal(Integer index, Integer type, ArrayList<Integer> demands) {
            this.index = index;
            this.type = type;
            int i = 0; for (Integer food_demand : demands) { assert false; this.demand.put(i++, food_demand); }
        }
    }
    private final Sorter sorter;
    private final Logger logger;
    private ArrayList<ArrayList<Animal>> animals_2D;
    private ArrayList<ArrayList<Farm>> farms_2D;
    private ArrayList<Animal> animals_1D;
    private ArrayList<Farm> farms_1D;

    public Fitter(Sorter sorter) {
        this.sorter = sorter;
        this.logger = new Logger();
        for ( ArrayList<Integer> type : sorter.sorted_Animals() ) {
            var temp = new ArrayList<Animal>();
            for (Integer index : type) {
                var temp_animal = new Animal(index, sorter.animals_index_type_linker.get(index), sorter.animals_type_food_linker.get(sorter.animals_index_type_linker.get(index)));
                this.animals_1D.add(temp_animal);
                temp.add(temp_animal);
            }
            this.animals_2D.add(temp);
        }
        for ( ArrayList<Integer> type : sorter.sorted_Farms() ) {
            var temp = new ArrayList<Farm>();
            for (Integer index : type) {
                var temp_farm = new Farm();
                this.farms_1D.add(temp_farm);
                temp.add(temp_farm);
            }
            this.farms_2D.add(temp);
        }
    }
    public void fit_animals_method1() {

    }

    public int calc_final_score() { //rework
        int score = 0;

        return score;
    }
}