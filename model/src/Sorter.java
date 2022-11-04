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
    private ArrayList<Integer> calc_score_animals() { // [ index , score ] // sum(foos list) * food_total_count
        var animal_type_hashmap = new HashMap<Integer, Integer>();
        for (ArrayList<Integer> integers : this.animal_type_food_list) {
            animal_type_hashmap.put(integers.get(0), this.food_score_array_index(integers));
        }
        return new ArrayList<>(animal_type_hashmap.keySet());
    }

    private ArrayList<Integer> calc_score_farms() { // [ index , score ]
        var food_type_hashmap = new HashMap<Integer, Integer>();
        for (ArrayList<Integer> integers : this.animal_type_food_list) {
            food_type_hashmap.put(integers.get(0), this.food_score_array_index(integers));
        }
        return new ArrayList<>(food_type_hashmap.keySet());
    }

    public ArrayList<ArrayList<Integer>> sorted_Animals() { // 3 array z zwierzetami score_list -> 3x Array [ [big], [mid], [lil]]
        var all = this.calc_score_animals();
        Collections.sort(all);
        int index_divide = all.size() / 3; // zwraca int [ a // b = c ]
        var lil = new ArrayList<Integer>(); for(int i : (int[])Arrays.copyOfRange(all.toArray(), 0, index_divide)[0]) { lil.add(i); }
        var mid = new ArrayList<Integer>(); for(int i : (int[])Arrays.copyOfRange(all.toArray(), index_divide, 2*index_divide)[0]) { lil.add(i); }
        var big = new ArrayList<Integer>(); for(int i : (int[])Arrays.copyOfRange(all.toArray(), 2*index_divide, all.size())[0]) { lil.add(i); }
        var res = new ArrayList<ArrayList<Integer>>(); res.add(lil); res.add(mid); res.add(big);
        return res;
    }

    public ArrayList<Integer> sorted_Farms() { // -> 3x Array [ [big], [mid], [lil]]
        //done
        return new ArrayList<>();
    }

    private int food_score_array_index(ArrayList<Integer> list) {
        Integer res = 0;
        int start_index = 1;
        for (int i = start_index; i < list.size(); i++) { res += list.get(i); }
        return  res*(list.size()-1);
    }

    private HashMap<Integer, Integer> generate_linker_1(ArrayList<ArrayList<Integer>> items) {
        var res = new HashMap<Integer, Integer>();
        for (ArrayList<Integer> record : items) { res.put(record.get(0), record.get(1)); }
        return res;
    }

    private HashMap<Integer, ArrayList<Integer>> generate_linker_2(ArrayList<ArrayList<Integer>> items) {
        var res = new HashMap<Integer, ArrayList<Integer>>();
        for (ArrayList<Integer> record : items) {
            var foods = new ArrayList<Integer>(); for( int i : (int[])Arrays.copyOfRange(items.toArray(),1,items.size()-1)[0]) { foods.add(i); }
            res.put(record.get(0), foods);
        }
        return res;
    }
}
