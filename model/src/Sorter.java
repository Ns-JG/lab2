import javax.lang.model.type.NullType;
import java.util.*;

public class Sorter {
    public ArrayList<ArrayList<Integer>> animal_index_type_list;
    public ArrayList<ArrayList<Integer>> animal_type_food_list;
    public ArrayList<ArrayList<Integer>> farm_index_supply;

    public Sorter(ArrayList<ArrayList<Integer>> animal_index_type_list,
                  ArrayList<ArrayList<Integer>>animal_type_food_list,
                  ArrayList<ArrayList<Integer>>farm_index_supply) {

        this.animal_index_type_list = animal_index_type_list;
        this.animal_type_food_list = animal_type_food_list;
        this.farm_index_supply = farm_index_supply;
    }

    public ArrayList<Integer> calc_score_animals() { // [ index , score ] // sum(foos list) * food_total_count
        var animal_type_hashmap = new HashMap<Integer, Integer>();
        for( int i = 0; i < this.animal_type_food_list.size(); i++) {
                animal_type_hashmap.put(this.animal_type_food_list.get(i).get(0), this.food_score_array_index(this.animal_type_food_list.get(i)));
        }
        return new ArrayList<Integer>(animal_type_hashmap.keySet());
    }
    public ArrayList<Integer> calc_score_farms() { // [ index , score ]
        var food_type_hashmap = new HashMap<Integer, Integer>();
        for( int i = 0; i < this.animal_type_food_list.size(); i++) {
            food_type_hashmap.put(this.animal_type_food_list.get(i).get(0), this.food_score_array_index(this.animal_type_food_list.get(i)));
        }
        return new ArrayList<Integer>(food_type_hashmap.keySet());
    }

    public ArrayList<ArrayList<Integer>> sorted_Animals() { // 3 array z zwierzetami score_list -> 3x Array [ [big], [mid], [lil]]

        var all = this.calc_score_animals();
        Collections.sort(all);
        int index_divide = all.size() / 3; // zwraca int [ a // b = c ]
        var lil = new ArrayList<Integer>(); for(int i : (int[])Arrays.copyOfRange(all.toArray(), 0, index_divide)[0]) { lil.add(i); }
        var mid = new ArrayList<Integer>(); for(int i : (int[])Arrays.copyOfRange(all.toArray(), index_divide, 2*index_divide)[0]) { lil.add(i); };
        var big = new ArrayList<Integer>(); for(int i : (int[])Arrays.copyOfRange(all.toArray(), 2*index_divide, all.size())[0]) { lil.add(i); };
        var res = new ArrayList<ArrayList<Integer>>(); res.add(lil); res.add(mid); res.add(big);
        return res;
    }
    public ArrayList<Integer> sorted_Farms() { // -> 3x Array [ [big], [mid], [lil]]

        return new ArrayList<Integer>();
    }

    private int food_score_array_index(ArrayList<Integer> list) {
        Integer res = 0;
        Integer start_index = 1;
        for (int i = start_index.intValue(); i < list.size(); i++) { res += list.get(i); }
        return  res*(list.size()-1);
    }
}
