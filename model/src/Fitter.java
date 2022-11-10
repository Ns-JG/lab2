import java.util.ArrayList;

public class Fitter {

    private final Sorter sorter;

    public Fitter(Sorter sorter) {
        this.sorter = sorter;
    }

    public ArrayList<ArrayList<Integer>> fit_animals() { // [[ farm , animals fiited ], [-||-] ... ]
        var sorted_animals = this.sorter.sorted_Animals();
        System.out.println(sorted_animals);
        for (var animal_type : sorted_animals) {

        }


        return new ArrayList<>();
    }

    public Integer fit_into_farm(int farm_index, ArrayList<Integer> animal_index_list) {
            var supplies = this.sorter.farms_index_food_linker.get(farm_index);
//          System.out.println(this.sorter.farms_index_food_linker);
//          System.out.println(farm_index+animal_index_list.toString());
//          System.out.println(supplies);
            for ( Integer animal : animal_index_list) {
                int counter = 0;
                for ( Integer animal_food_req : sorter.animals_type_food_linker.get(sorter.animals_index_type_linker.get(animal)) ) {
                    if (supplies.get(counter) >= 0)
                    supplies.set(counter, supplies.get(counter)-animal_food_req);
                    else return -1; // nie zmieściło sie
                }
            }
            int score = 0;
            for ( Integer food_left : supplies) { score += food_left; } // mniej lepiej
        return score;
    }

    public int calc_final_score() {
        int score = 0;
        for (var farm : this.sorter.farm_index_supply ) {
            for ( int food : farm ) { score += food; }
        }
        return score;
    }

}
