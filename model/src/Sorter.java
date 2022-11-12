import java.util.*;

public class Sorter {
    public ArrayList<ArrayList<Integer>> animal_index_type_list;
    public ArrayList<ArrayList<Integer>> animal_type_food_list;
    public ArrayList<ArrayList<Integer>> farm_index_supply;
    public HashMap<Integer, Integer> animals_index_type_linker;
    public HashMap<Integer , ArrayList<Integer>> animals_type_food_linker;
    public  HashMap<Integer , ArrayList<Integer>> farms_index_food_linker;

    public Sorter(ArrayList<ArrayList<Integer>> animal_index_type_list,
                  ArrayList<ArrayList<Integer>>animal_type_food_list,
                  ArrayList<ArrayList<Integer>>farm_index_supply) {

        this.animal_index_type_list = animal_index_type_list;
        this.animal_type_food_list = animal_type_food_list;
        this.farm_index_supply = farm_index_supply;
        this.animals_index_type_linker = this.generate_linker_1(this.animal_index_type_list);
        this.animals_type_food_linker = this.generate_linker_2(this.animal_type_food_list);
        this.farms_index_food_linker = this.generate_linker_2(this.farm_index_supply);
    }
    public ArrayList<ArrayList<Integer>> calc_score_animals() { // [ index , score ] // sum(foos list) * food_total_count
        var animal_score_list = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> animal : this.animal_index_type_list) {
            var food_demand = this.animals_type_food_linker.get(this.animals_index_type_linker.get(animal.get(0)));
            animal_score_list.add(new ArrayList<>(Arrays.asList(animal.get(0), this.food_score_array_index(food_demand))));
        }
        return animal_score_list;
    }

    private ArrayList<Integer> calc_score_farms() { // [ index , score ]
        var food_type_hashmap = new HashMap<Integer, Integer>();
        for (ArrayList<Integer> integers : this.animal_type_food_list) {
            food_type_hashmap.put(integers.get(0), this.food_score_array_index(integers));
        }
        return new ArrayList<>(food_type_hashmap.keySet());
    }

    public ArrayList<ArrayList<Integer>> sorted_Animals() { //  3x Array [ [big], [mid], [lil]]
        var all_animals = this.calc_score_animals();
        all_animals.sort((o1, o2) -> {
            Integer i1 = o1.get(1);
            Integer i2 = o2.get(1);
            return i1.compareTo(i2);
        });
        var sorted_animals = new ArrayList<Integer>(); all_animals.forEach((n) -> sorted_animals.add(n.get(0)));
        int index_divide = sorted_animals.size() / 3; // zwraca int [ a // b = c ]
        var lil = new ArrayList<>(sorted_animals.subList(0, index_divide));
        var mid = new ArrayList<>(sorted_animals.subList(index_divide, 2*index_divide));
        var big = new ArrayList<>(sorted_animals.subList(2*index_divide, sorted_animals.size()));
        return new ArrayList<>(Arrays.asList(big, mid, lil));
    }

    public ArrayList<ArrayList<Integer>> sorted_Farms() { // -> 3x Array [ [big], [mid], [lil]]
        var farm_score = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> farm : this.farm_index_supply) {
            farm_score.add(new ArrayList<>(Arrays.asList(farm.get(0), this.food_score_array_index(new ArrayList<>(farm.subList(1,farm.size()))))));
        }
        farm_score.sort((o1, o2) -> {
            Integer i1 = o1.get(1);
            Integer i2 = o2.get(1);
            return i1.compareTo(i2);
        });
        var farms_index_sorted = new ArrayList<Integer>(); farm_score.forEach((n) -> farms_index_sorted.add(n.get(0)));
        int index_divide = farm_score.size() / 3;
        var lil = new ArrayList<>(farms_index_sorted.subList(0, index_divide));
        var mid = new ArrayList<>(farms_index_sorted.subList(index_divide, 2*index_divide));
        var big = new ArrayList<>(farms_index_sorted.subList(2*index_divide, farms_index_sorted.size()));
        return new ArrayList<>(Arrays.asList(big, mid, lil));
    }

    private Integer food_score_array_index(ArrayList<Integer> list) {
        Integer res = 0;
        for (var food : list) { res += food; }
        return res;
    }

    private HashMap<Integer, Integer> generate_linker_1(ArrayList<ArrayList<Integer>> items) {
        var res = new HashMap<Integer, Integer>();
        for (ArrayList<Integer> record : items) { res.put(record.get(0), record.get(1)); }
        return res;
    }

    private HashMap<Integer, ArrayList<Integer>> generate_linker_2(ArrayList<ArrayList<Integer>> items) {
        var res = new HashMap<Integer, ArrayList<Integer>>();
        for (ArrayList<Integer> record : items) {
            ArrayList<Integer> foods = new ArrayList<>(record.subList(1, record.size()));
            res.put(record.get(0), foods);
        }
        return res;
    }
}