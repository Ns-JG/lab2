import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Fitter {
    class Farm {
        public Integer index;
        public HashMap<Integer, Integer> supplies = new HashMap<>();
        public HashMap<Integer, Integer> supplies_raw = new HashMap<>();
        public ArrayList<Animal> habbitants = new ArrayList<>();
        public Farm(Integer index, ArrayList<Integer> supplies) {
            this.index = index;
            int i = 1; for (Integer food_supply : supplies) {
                this.supplies.put(i, food_supply);
                this.supplies_raw.put(i++, food_supply);
            }
        }
        public boolean add_animal(Animal animal) {
            var temp_sub_vals = new ArrayList<Integer>();
            var failed = false;
            for (var demand_index : animal.demand.keySet()) {
                if (this.supplies.get(demand_index) < animal.demand.get(demand_index)) { failed = true; temp_sub_vals.add(0); break; }
                temp_sub_vals.add(animal.demand.get(demand_index));
                this.supplies.replace(demand_index, this.supplies.get(demand_index)-animal.demand.get(demand_index));
            }
            if (failed) {
                int index = 1;
                for (var demand_value : temp_sub_vals) { // reversing if failed
                    this.supplies.replace(index, this.supplies.get(index)+demand_value); index++;
                }
                return true;
            } else this.habbitants.add(animal);
            return false;
        } // true - refused, false - added

        public ArrayList<Integer> get_supplies_left() { return new ArrayList<>(supplies.values()); }
        public Integer get_supplies_left_int() { return Arrays.stream(this.get_supplies_left().toArray()).mapToInt((n) -> (Integer)n).sum(); }
        public void restore_supplies() { this.supplies_raw.keySet().forEach((n) -> this.supplies.replace(n, this.supplies_raw.get(n)));

        }
    }
    class Animal {
        public Integer index;
        public Integer type;
        public HashMap<Integer, Integer> demand = new HashMap<>();
        public Animal(Integer index, Integer type, ArrayList<Integer> demand) {
            this.index = index;
            this.type = type;
            int id = 1; for (Integer food_demand : demand) { this.demand.put(id++, food_demand); }
        }
    }
    private final Generator generator;
    private final ArrayList<ArrayList<Animal>> animals_2D = new ArrayList<>();
    private final ArrayList<Animal> animals_1D = new ArrayList<>();
    private final ArrayList<Farm> farms_1D = new ArrayList<>();
    public static List sizes = Arrays.asList("big", "medium", "little");

    public Fitter(Sorter sorter, Generator generator) {
        this.generator = generator;
        for ( ArrayList<Integer> type : sorter.sorted_Animals() ) {
            var temp = new ArrayList<Animal>();
            for (Integer index : type) {
                var temp_animal = new Animal(index, sorter.animals_index_type_linker.get(index), sorter.animals_type_food_linker.get(sorter.animals_index_type_linker.get(index)) );
                this.animals_1D.add(temp_animal);
                temp.add(temp_animal);
            }
            this.animals_2D.add(temp);
        }
        for ( ArrayList<Integer> type : sorter.sorted_Farms() ) {
            var temp = new ArrayList<Farm>();
            for (Integer index : type) {
                var temp_farm = new Farm(index, sorter.farms_index_food_linker.get(index));
                this.farms_1D.add(temp_farm);
                temp.add(temp_farm);
            }
            ArrayList<ArrayList<Farm>> farms_2D = new ArrayList<>();
            farms_2D.add(temp);
        }
    }
    public void fit_animals_method1() { // temporal best match for stats per farm \ brute force
        var capacity = this.score();
        var total = this.animals_1D.size();
        var left = 0;
        var queue = new ArrayList<ArrayList<Animal>>(Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        var animals_2D = new ArrayList<>(this.animals_2D);
        var fitted_animals = new ArrayList<Animal>();

        for ( Farm farm : this.farms_1D ) {

            for (Animal big_animal : this.animals_2D.get(0)) {
                queue.get(0).add(big_animal);
                if (!farm.add_animal(big_animal)) { fitted_animals.add(big_animal); }
                else { queue.get(0).remove(queue.get(0).size() - 1); }
            }

            for (Animal mid_animal : this.animals_2D.get(1)) {
                queue.get(1).add(mid_animal);
                if (!farm.add_animal(mid_animal)) { fitted_animals.add(mid_animal); }
                else { queue.get(1).remove(queue.get(1).size() - 1); }
            }

            for (Animal lil_animal : this.animals_2D.get(2)) {
                queue.get(2).add(lil_animal);
                if (!farm.add_animal(lil_animal)) { fitted_animals.add(lil_animal); }
                else { queue.get(2).remove(queue.get(2).size() - 1); }
            }

            var flattened_queue = queue.stream().flatMap(ArrayList::stream).collect(Collectors.toList());
            var index_queue = new ArrayList<ArrayList<Integer>>(Arrays.asList(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            IntStream.range(0, queue.size()).forEach(ix ->
                    IntStream.range(0, queue.get(ix).size()).forEach((k)->
                            index_queue.get(ix).add(queue.get(ix).get(k).index)));

            System.out.println("QUEUE:\t"+index_queue+"\t");
            //System.out.printf("farm index: %s\t\tfarm supplies remiain\t%s%n", farm.get_supplies_left_int());
            for (int i = 0; i < 3; i++) { animals_2D.get(i).removeAll(queue.get(i)); queue.get(i).clear(); }
            total -= flattened_queue.size();
            System.out.printf("Farm index: %s\tadded animals: %s\tanimals left: %s%n",farm.index, flattened_queue.size(), total+"\n");
            left = total;
            System.out.print("----------------------------------------------------------------------\n");
        }
        System.out.println("unfitted animals (indexes)"); this.log_2D_animals(animals_2D); // unfitted
        this.farms_1D.forEach((n) -> {
            List<List<Object>> habitans_visual = new ArrayList<>();
            n.habbitants.forEach((k) -> habitans_visual.add(new ArrayList<>(Arrays.asList("id:", k.index,"type", k.type))));
            System.out.printf("farm_id: %d\tsupplies left: %d\tanimals : %s (%d)%n", n.index, n.get_supplies_left_int(),habitans_visual, n.habbitants.size());
        }); //fitted
        System.out.printf("\u001B[1m" +"%nFull capacity: %s\tLeft : %s\t score: %2.2f%%\tFitted animals: %2.2f%%%n",
                capacity, this.score(), ((float)this.score()/(float)capacity)*100.0, (1-(float)left/(float)this.animals_1D.size())*100); //final score
        this.supplies_left_log(); // food left
    }
    private void log_2D_animals(ArrayList<ArrayList<Animal>> arr2d) {
        Loop_logger iter = new Loop_logger(sizes);
        for (ArrayList<Animal> k : arr2d) {
            System.out.printf("[%s]\t", iter.next());
            if (k.size() == 0) { System.out.print("\tNone"); }
            else { for (Animal i : k) { System.out.printf("%s,", i.index); } }
            System.out.println();
        }
        System.out.println();
    }
    private int score() {
        int score = 0;
        for ( Farm farm : this.farms_1D ) {
            score += farm.get_supplies_left_int(); }
        return score;
    }
    private void supplies_left_log() {
//        var scores = new ArrayList<Integer>();
//        for (int i = 0; i < this.generator.ilosc_typow_pozywienia; i++) { scores.add(0); }
//        for ( Farm farm : this.farms_1D ) {
//            int type = 0;
//            System.out.
//            for (Integer food_type : farm.get_supplies_left()) { scores.set(type, scores.get(type)+food_type); }
//        }
//        System.out.print("Food left in farms: ");
//        for (int i = 1; i < this.generator.ilosc_typow_pozywienia+1; i++) { System.out.printf("type %d: %d\t", i, scores.get(i-1)); }
    }
}