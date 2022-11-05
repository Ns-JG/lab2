import java.util.*;
public class Fitter {
    ArrayList<ArrayList<Integer>> animals_sorted = new ArrayList<ArrayList<Integer>>(); // posortowane indexy [ lil , mid, big ]
    ArrayList<Integer> farms_sorted = new ArrayList<Integer>(); // posortowane indexy

    HashMap<Integer, Integer> animals_index_type_linker = new HashMap<Integer, Integer>(); // index, typ
    HashMap<Integer , ArrayList<Integer>> animals_type_food_linker = new HashMap<Integer , ArrayList<Integer>>(); // typ , [food 1, food2 , ...]
    HashMap<Integer , ArrayList<Integer>> farms_type_food_linker = new HashMap<Integer , ArrayList<Integer>>(); // index_farmy , [food 1, food2 , ...]

    public ArrayList<Integer> fit_Animals_To_Farms() {
        for(int i = 0; i < this.farms_type_food_linker.size(); i++) {
            for(int j = 2; j >= 0; j--) {
                for(int k = 0; k < this.animals_index_type_linker.size(); k++) {
                    var farm_choosen = farms_type_food_linker.get(farms_sorted.get(i)); // dostęp do
                    // zapasów największej
                    // farmy [food
                    // 1,
                    // food2 , ...]
                    var animal_choosen = animals_sorted.get(j).get(k); // j - przekładka między rozmiarami zwierząt,
                    // k - indeks zwierza
                    var type_choosen = animals_type_food_linker.get(animals_index_type_linker.get(animal_choosen));
                    // typy żarcia dla danego typu zwierzęcia

                    // odejmowanie od zapasów farmy wymaganego żarcia przez typ zwierzęcia

                    //farm_choosen > 0 ? farm_choosen -= type_choosen : break;

                    // jeżeli jest jedzenie na farmie to odejmij od niego porcje jaką potrzebuje zwierze, jeśli nie to
                    // przerwuj tego fora i przejdź to
                    // innej kategorii wielkości zwierza
                }
            }
        }


        return new ArrayList<Integer>();
    }
    public int calc_score() {

        return 0;
    }

}
