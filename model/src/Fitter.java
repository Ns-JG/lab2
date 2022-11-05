import java.util.*;
public class Fitter {
    ArrayList<ArrayList<Integer>> animals_sorted = new ArrayList<ArrayList<Integer>>(); // posortowane indexy [ lil , mid, big ]
    ArrayList<Integer> farms_sorted = new ArrayList<Integer>(); // posortowane indexy

    HashMap<Integer, Integer> animals_index_type_linker = new HashMap<Integer, Integer>(); // index, typ
    HashMap<Integer , ArrayList<Integer>> animals_type_food_linker = new HashMap<Integer , ArrayList<Integer>>(); // typ , [food 1, food2 , ...]
    HashMap<Integer , ArrayList<Integer>> farms_type_food_linker = new HashMap<Integer , ArrayList<Integer>>(); // index_farmy , [food 1, food2 , ...]
    ArrayList<Integer> quantity = new ArrayList<Integer>();

    public HashMap<Integer, ArrayList<Integer>> fit_Animals_To_Farms() {
        var result = new HashMap<Integer, ArrayList<Integer>>();
        var animals = new ArrayList<Integer>();
        for (int i = 0; i < this.farms_type_food_linker.size(); i++) {
            for (int j = 2; j >= 0; j--) {
                for (int k = 0; k < this.animals_index_type_linker.size(); k++) {
                    var farm = farms_sorted.get(i); // indeks farmy
                    var farm_choosen = farms_type_food_linker.get(farm); // dostęp do
                    // zapasów największej
                    // farmy [food
                    // 1,
                    // food2 , ...]
                    var animal_choosen = animals_sorted.get(j).get(k); // j - przekładka między rozmiarami zwierząt,
                    // k - indeks zwierza
                    var type_choosen = animals_type_food_linker.get(animals_index_type_linker.get(animal_choosen));
                    // typy żarcia dla danego typu zwierzęcia

                    // odejmowanie od zapasów farmy wymaganego żarcia przez typ zwierzęcia

                    for (int l = 0; l < farm_choosen.size(); l++) { // lecisz po typie karmy na danej
                        // farmie i jeżeli to jest większe od zero to odejmujesz od tego zwierza co jest aktualnie
                        // jako wybrany
                        if (farm_choosen.get(l) > 0) {
                            // Dlaczego farm_choosen.get(l) to jest tablica jak ja biore konkretna farme czyli już
                            // mam tylko jedną tablice i później z tej tablicy wyciągam jeden typ karmy i na nim
                            // robie arytmetyke
                            farm_choosen.get(l) = farm_choosen.get(l) - type_choosen.get(l);
                            quantity.add(farm_choosen.get(l));
                            animals.add(animal_choosen);
                        }
                        else break;
                    }
                    result.put(farm, animals);
                }
            }
        }


        return result;
    }

    public ArrayList<Integer> calc_score() {
        return quantity;
    }

}
